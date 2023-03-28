package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Truck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TruckMapperTest {
    @Test
    @DisplayName("Test TruckEntity to TruckDto.")
    void testEntityToDto() {
        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setHopType("truck");
        truckEntity.setDescription("Truck description");
        truckEntity.setCode("TWKSI2");
        truckEntity.setLocationName("Wien");
        truckEntity.setProcessingDelayMins(235);
        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();
        geoCoordinateEntity.setLat(11.22);
        geoCoordinateEntity.setLon(33.44);
        truckEntity.setLocationCoordinates(geoCoordinateEntity);
        truckEntity.setNumberPlate("W-12345");
        truckEntity.setRegionGeoJson("RegionGeoJsonString");


        Truck truck = TruckMapper.INSTANCE.entityToDto(truckEntity);
        assertEquals(truckEntity.getHopType(), truck.getHopType());
        assertEquals(truckEntity.getDescription(), truck.getDescription());
        assertEquals(truckEntity.getCode(), truck.getCode());
        assertEquals(truckEntity.getLocationName(), truck.getLocationName());
        assertEquals(truckEntity.getProcessingDelayMins(), truck.getProcessingDelayMins());
        assertEquals(truckEntity.getLocationCoordinates().getLat(), truck.getLocationCoordinates().getLat());
        assertEquals(truckEntity.getLocationCoordinates().getLon(), truck.getLocationCoordinates().getLon());
        assertEquals(truckEntity.getNumberPlate(), truck.getNumberPlate());
        assertEquals(truckEntity.getRegionGeoJson(), truck.getRegionGeoJson());
    }

    @Test
    @DisplayName("Test TruckDto to TruckEntity.")
    void testDtoToEntity() {
        Truck truck = new Truck();
        truck.setHopType("truck");
        truck.setDescription("Truck description");
        truck.setCode("TWKSI2");
        truck.setLocationName("Wien");
        truck.setProcessingDelayMins(235);
        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLat(11.22);
        geoCoordinate.setLon(33.44);
        truck.setLocationCoordinates(geoCoordinate);
        truck.setNumberPlate("W-12345");
        truck.setRegionGeoJson("RegionGeoJsonString");


        TruckEntity truckEntity = TruckMapper.INSTANCE.dtoToEntity(truck);
        assertEquals(truckEntity.getHopType(), truck.getHopType());
        assertEquals(truckEntity.getDescription(), truck.getDescription());
        assertEquals(truckEntity.getCode(), truck.getCode());
        assertEquals(truckEntity.getLocationName(), truck.getLocationName());
        assertEquals(truckEntity.getProcessingDelayMins(), truck.getProcessingDelayMins());
        assertEquals(truckEntity.getLocationCoordinates().getLat(), truck.getLocationCoordinates().getLat());
        assertEquals(truckEntity.getLocationCoordinates().getLon(), truck.getLocationCoordinates().getLon());
        assertEquals(truckEntity.getNumberPlate(), truck.getNumberPlate());
        assertEquals(truckEntity.getRegionGeoJson(), truck.getRegionGeoJson());
    }
}