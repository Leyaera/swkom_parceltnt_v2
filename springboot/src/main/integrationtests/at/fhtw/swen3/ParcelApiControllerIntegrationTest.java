package at.fhtw.swen3;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(classes = OpenApiGeneratorApplication.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParcelApiControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParcelRepository parcelRepository;

    private static String trackingId;

    @AfterAll
    void deleteParcelFromDB() {
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        parcelRepository.delete(parcelEntity);
    }

    @Test
    @DisplayName("SubmitParcel fails.")
    void FailSubmitParcel() throws Exception {
        mockMvc.perform(post("/parcel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((byte[]) null))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Successful journey of a Parcel from Submitting to Delivery.")
    void testParcelFromSubmitToDelivered() throws Exception {
        submitParcel();
        setWebhooksAfterSubmit();
        trackParcelAfterSubmit();
        reportHopArrivalTruck();
        trackParcelAfterHopArrivalTruck();
        reportHopArrivalWarehouse();
        trackParcelAfterHopArrivalWarehouse();
        reportFinalDelivery();
        trackParcelDelivered();
        deleteWebhooksAfterDelivery();
    }

    void submitParcel() throws Exception {
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
        trackingId = jsonObject.get("trackingId").toString();
    }

    void setWebhooksAfterSubmit() throws Exception {
        MvcResult result = mockMvc.perform(post("/webhook/" + trackingId))
                .andExpect(status().isCreated())
                .andReturn();
    }

    void trackParcelAfterSubmit() throws Exception {
        // track Parcel after submit
        MvcResult result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals("Pickup", jsonObject.get("state"));
    }

    void reportHopArrivalTruck() throws Exception {
        // reportHopArrival Truck
        mockMvc.perform(post("/parcel/" + trackingId + "/reportHop/WTTA015"))
                .andExpect(status().isOk())
                .andReturn();
    }

    void trackParcelAfterHopArrivalTruck() throws Exception {
        // track Parcel after HopArrival Truck
        MvcResult result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals("InTruckDelivery", jsonObject.get("state"));
    }

    void reportHopArrivalWarehouse() throws Exception {
        // reportHopArrival Warehouse
        mockMvc.perform(post("/parcel/" + trackingId + "/reportHop/WENA03"))
                .andExpect(status().isOk())
                .andReturn();
    }

    void trackParcelAfterHopArrivalWarehouse() throws Exception {
        // track Parcel after HopArrival Warehouse
        MvcResult result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        log.info("response is: " + result.getResponse().getContentAsString());
        assertEquals("InTransport", jsonObject.get("state"));
    }
    void reportFinalDelivery() throws Exception {
        // report final delivery
        mockMvc.perform(post("/parcel/" + trackingId + "/reportDelivery/"))
                .andExpect(status().isOk())
                .andReturn();
    }

    void trackParcelDelivered() throws Exception {
        // track Parcel after Delivery
        MvcResult result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        log.info("response is: " + result.getResponse().getContentAsString());
        assertEquals("Delivered", jsonObject.get("state"));
    }

    void deleteWebhooksAfterDelivery() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/webhook/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
    }
}
