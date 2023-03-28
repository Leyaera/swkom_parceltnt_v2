package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Transferwarehouse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransferwarehouseMapperTest {
    @Test
    @DisplayName("Test TransferwarehouseEntity to TransferwarehouseDto.")
    void testEntityToDto() {
        TransferwarehouseEntity transferwarehouseEntity = new TransferwarehouseEntity();
        transferwarehouseEntity.setCode("TWAMS1");
        transferwarehouseEntity.setDescription("Transferwarehouse description");
        transferwarehouseEntity.setHopType("transferwarehouse");
        transferwarehouseEntity.setLocationName("Wien");
        transferwarehouseEntity.setLogisticsPartner("Logistics Partner Name");
        transferwarehouseEntity.setLogisticsPartnerUrl("http://logistics-partner-url.com");
        transferwarehouseEntity.setRegionGeoJson("RegionGeoJson String");
        transferwarehouseEntity.setProcessingDelayMins(225);
        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();
        geoCoordinateEntity.setLat(11.22);
        geoCoordinateEntity.setLon(33.44);
        transferwarehouseEntity.setLocationCoordinates(geoCoordinateEntity);

        Transferwarehouse transferwarehouse = TransferwarehouseMapper.INSTANCE.entityToDto(transferwarehouseEntity);

        assertEquals(transferwarehouseEntity.getHopType(), transferwarehouse.getHopType());
        assertEquals(transferwarehouseEntity.getDescription(), transferwarehouse.getDescription());
        assertEquals(transferwarehouseEntity.getCode(), transferwarehouse.getCode());
        assertEquals(transferwarehouseEntity.getLocationName(), transferwarehouse.getLocationName());
        assertEquals(transferwarehouseEntity.getProcessingDelayMins(), transferwarehouse.getProcessingDelayMins());
        assertEquals(transferwarehouseEntity.getLocationCoordinates().getLat(), transferwarehouse.getLocationCoordinates().getLat());
        assertEquals(transferwarehouseEntity.getLocationCoordinates().getLon(), transferwarehouse.getLocationCoordinates().getLon());
        assertEquals(transferwarehouseEntity.getLogisticsPartnerUrl(), transferwarehouse.getLogisticsPartnerUrl());
        assertEquals(transferwarehouseEntity.getLogisticsPartner(), transferwarehouse.getLogisticsPartner());
        assertEquals(transferwarehouseEntity.getRegionGeoJson(), transferwarehouse.getRegionGeoJson());
    }

    @Test
    @DisplayName("Test TransferwarehouseDto to TransferwarehouseEntity.")
    void testDtoToEntity() {
        Transferwarehouse transferwarehouse = new Transferwarehouse();
        transferwarehouse.setCode("TWAMS1");
        transferwarehouse.setDescription("Transferwarehouse description");
        transferwarehouse.setHopType("transferwarehouse");
        transferwarehouse.setLocationName("Wien");
        transferwarehouse.setLogisticsPartner("Logistics Partner Name");
        transferwarehouse.setLogisticsPartnerUrl("http://logistics-partner-url.com");
        transferwarehouse.setRegionGeoJson("RegionGeoJson String");
        transferwarehouse.setProcessingDelayMins(225);
        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLat(11.22);
        geoCoordinate.setLon(33.44);
        transferwarehouse.setLocationCoordinates(geoCoordinate);

        TransferwarehouseEntity transferwarehouseEntity = TransferwarehouseMapper.INSTANCE.dtoToEntity(transferwarehouse);

        assertEquals(transferwarehouseEntity.getHopType(), transferwarehouse.getHopType());
        assertEquals(transferwarehouseEntity.getDescription(), transferwarehouse.getDescription());
        assertEquals(transferwarehouseEntity.getCode(), transferwarehouse.getCode());
        assertEquals(transferwarehouseEntity.getLocationName(), transferwarehouse.getLocationName());
        assertEquals(transferwarehouseEntity.getProcessingDelayMins(), transferwarehouse.getProcessingDelayMins());
        assertEquals(transferwarehouseEntity.getLocationCoordinates().getLat(), transferwarehouse.getLocationCoordinates().getLat());
        assertEquals(transferwarehouseEntity.getLocationCoordinates().getLon(), transferwarehouse.getLocationCoordinates().getLon());
        assertEquals(transferwarehouseEntity.getLogisticsPartnerUrl(), transferwarehouse.getLogisticsPartnerUrl());
        assertEquals(transferwarehouseEntity.getLogisticsPartner(), transferwarehouse.getLogisticsPartner());
        assertEquals(transferwarehouseEntity.getRegionGeoJson(), transferwarehouse.getRegionGeoJson());
    }
}