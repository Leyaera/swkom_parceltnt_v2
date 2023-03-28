package at.fhtw.swen3;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(classes = OpenApiGeneratorApplication.class)
@AutoConfigureMockMvc
public class ParcelApiControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static String trackingId;

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
    void SuccessParcelJourney() throws Exception {

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

        // track Parcel after submit
        result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        jsonObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals("Pickup", jsonObject.get("state"));

        // reportHopArrival Truck
        mockMvc.perform(post("/parcel/" + trackingId + "/reportHop/WTTA01"))
                .andExpect(status().isOk())
                .andReturn();

        // track Parcel after HopArrival Truck
        result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        jsonObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals("InTruckDelivery", jsonObject.get("state"));

        // reportHopArrival Warehouse
        mockMvc.perform(post("/parcel/" + trackingId + "/reportHop/WENA03"))
                .andExpect(status().isOk())
                .andReturn();

        // track Parcel after HopArrival Truck
        result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        jsonObject = new JSONObject(result.getResponse().getContentAsString());
        log.info("response is: " + result.getResponse().getContentAsString());
        assertEquals("InTransport", jsonObject.get("state"));

        // report final delivery
        mockMvc.perform(post("/parcel/" + trackingId + "/reportDelivery/"))
                .andExpect(status().isOk())
                .andReturn();

        // track Parcel after Delivery
        result = mockMvc.perform(get("/parcel/" + trackingId))
                .andExpect(status().isOk())
                .andReturn();
        jsonObject = new JSONObject(result.getResponse().getContentAsString());
        log.info("response is: " + result.getResponse().getContentAsString());
        assertEquals("Delivered", jsonObject.get("state"));
    }
}
