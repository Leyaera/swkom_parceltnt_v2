/* This is a bad example of an Integration test
 * Tests have been split but are now dependent on each other in a specific order.
 * This is an anti-pattern in unit tests, but helpful in integration tests.
 * see: https://www.baeldung.com/junit-testinstance-annotation
 */

package at.fhtw.swen3;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(classes = OpenApiGeneratorApplication.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParcelApiControllerTestBad {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParcelRepository parcelRepository;

    static private String trackingId;

    @BeforeAll
    void retrieveTrackingId() throws Exception {
        trackingId = submitParcel();
    }

    @AfterAll
    void deleteParcelFromDB() {
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        parcelRepository.delete(parcelEntity);
    }

    String submitParcel() throws Exception {
        String parcel ="{\"weight\": 2.5,\n" +
                "\"recipient\": {\n" +
                "\"name\": \"Max Mustermann\",\n" +
                "\"street\": \"Musterstraße 123\",\n" +
                "\"postalCode\": \"A-1110\",\n" +
                "\"city\": \"Wien\",\n" +
                "\"country\": \"Austria\"\n" +
                "},\n" +
                "\"sender\": {\n" +
                "\"name\": \"Maria Musterfrau\",\n" +
                "\"street\": \"Mustergasse 1\",\n" +
                "\"postalCode\": \"12235\",\n" +
                "\"city\": \"Berlin\",\n" +
                "\"country\": \"Germany\"\n" +
                "}}" ;

        MvcResult result = mockMvc.perform(post("/parcel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(parcel))
                .andExpect(status().isCreated())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        return jsonObject.get("trackingId").toString();
    }
    @Test
    @Order(1)
    void trackParcelAfterSubmit() throws Exception {
        // track Parcel after submit
        MvcResult result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals("Pickup", jsonObject.get("state"));
    }

    @Test
    @Order(2)
    void reportHopArrivalTruck() throws Exception {
        // reportHopArrival Truck
        mockMvc.perform(post("/parcel/" + trackingId + "/reportHop/WTTA015"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    void trackParcelAfterHopArrivalTruck() throws Exception {
        // track Parcel after HopArrival Truck
        MvcResult result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals("InTruckDelivery", jsonObject.get("state"));
    }

    @Test
    @Order(4)
    void reportHopArrivalWarehouse() throws Exception {
        // reportHopArrival Warehouse
        mockMvc.perform(post("/parcel/" + trackingId + "/reportHop/WENA03"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(5)
    void trackParcelAfterHopArrivalWarehouse() throws Exception {
        // track Parcel after HopArrival Warehouse
        MvcResult result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        log.info("response is: " + result.getResponse().getContentAsString());
        assertEquals("InTransport", jsonObject.get("state"));
    }

    @Test
    @Order(6)
    void reportFinalDelivery() throws Exception {
        // report final delivery
        mockMvc.perform(post("/parcel/" + trackingId + "/reportDelivery/"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(7)
    void trackParcelDelivered() throws Exception {
        // track Parcel after Delivery
        MvcResult result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        log.info("response is: " + result.getResponse().getContentAsString());
        assertEquals("Delivered", jsonObject.get("state"));
    }
}
