package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.Address;
import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class OpenStreetEncodingProxy implements GeoEncodingService {
    @Override
    public GeoCoordinateEntity encodeAddress(Address a) {
        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();
        try {
            StringBuilder urlBuilder = new StringBuilder("https://nominatim.openstreetmap.org/search?");
            urlBuilder.append("format=json&");
            urlBuilder.append("street=").append(a.getStreet()).append("&");
            urlBuilder.append("postalcode=").append(a.getPostalCode()).append("&");
            urlBuilder.append("city=").append(a.getCity()).append("&");
            urlBuilder.append("country=").append(a.getCountry()).append("&");
            urlBuilder.append("limit=1");
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String result = in.lines().collect(Collectors.joining());
            in.close();
            JSONArray jsonArray = new JSONArray(result);
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                geoCoordinateEntity.setLat(jsonObject.getDouble("lat"));
                geoCoordinateEntity.setLon(jsonObject.getDouble("lon"));
            }
        } catch (Exception e){
            log.error("The address of sender or receiver was not found.");;
        }
        return geoCoordinateEntity;
    }

}
