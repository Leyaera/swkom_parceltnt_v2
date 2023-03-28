package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.dto.Warehouse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HopMapperTest {
    @Test
    @DisplayName("Test HopEntity to HopDto.")
    void testEntityToDto()
    {
        HopEntity hopEntity = new HopEntity();
        hopEntity.setHopType("hop type");
        hopEntity.setCode("DJENSU");
        hopEntity.setDescription("Hop description");
        hopEntity.setLocationName("Hop Location");
        hopEntity.setProcessingDelayMins(123);

        Hop hop = HopMapper.INSTANCE.entityToDto(hopEntity);

        assertEquals(hop.getCode(), hopEntity.getCode());
        assertEquals(hop.getHopType(), hopEntity.getHopType());
        assertEquals(hop.getDescription(), hopEntity.getDescription());
        assertEquals(hop.getLocationName(), hopEntity.getLocationName());
        assertEquals(hop.getProcessingDelayMins(), hopEntity.getProcessingDelayMins());
    }

    @Test
    @DisplayName("Test HopDto to HopEntity.")
    void testDtoToEntity()
    {
        Hop hop = new Hop();
        hop.setHopType("hop type");
        hop.setCode("DJENSU");
        hop.setDescription("Hop description");
        hop.setLocationName("Hop Location");
        hop.setProcessingDelayMins(234);

        HopEntity hopEntity = HopMapper.INSTANCE.dtoToEntity(hop);

        assertEquals(hop.getCode(), hopEntity.getCode());
        assertEquals(hop.getHopType(), hopEntity.getHopType());
        assertEquals(hop.getDescription(), hopEntity.getDescription());
        assertEquals(hop.getLocationName(), hopEntity.getLocationName());
        assertEquals(hop.getProcessingDelayMins(), hopEntity.getProcessingDelayMins());
    }

    @Test
    @DisplayName("Test WarehouseEntity to WarehouseDto via HopMapper.")
    void testWarehouseEntityToWarehouseDto() {
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setLevel(5);
        warehouseEntity.setHopType("warehouse");
        warehouseEntity.setNextHops(new ArrayList<>());
        warehouseEntity.setDescription("Warehouse description");
        warehouseEntity.setCode("WJAO47");
        warehouseEntity.setLocationName("Wien");
        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();
        geoCoordinateEntity.setLat(11.22);
        geoCoordinateEntity.setLon(33.44);
        warehouseEntity.setLocationCoordinates(geoCoordinateEntity);
        warehouseEntity.setProcessingDelayMins(123);

        Warehouse warehouse = (Warehouse) HopMapper.INSTANCE.entityToDto(warehouseEntity);
        assertEquals(warehouseEntity.getLevel(), warehouse.getLevel());
        assertEquals(warehouseEntity.getHopType(), warehouse.getHopType());;
        assertEquals(warehouseEntity.getNextHops(), warehouse.getNextHops());
        assertEquals(warehouseEntity.getCode(), warehouse.getCode());
        assertEquals(warehouseEntity.getDescription(), warehouse.getDescription());
        assertEquals(warehouseEntity.getLocationName(), warehouse.getLocationName());
        assertEquals(warehouseEntity.getLocationCoordinates().getLat(), warehouse.getLocationCoordinates().getLat());
        assertEquals(warehouseEntity.getLocationCoordinates().getLon(), warehouse.getLocationCoordinates().getLon());
        assertEquals(warehouseEntity.getProcessingDelayMins(), warehouse.getProcessingDelayMins());
    }

    @Test
    @DisplayName("Test TruckEntity to TruckDto via HopMapper.")
    void testTruckEntityToTruckDto() {
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


        Truck truck = (Truck) HopMapper.INSTANCE.entityToDto(truckEntity);
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
    @DisplayName("Test TransferwarehouseEntity to TransferwarehouseDto via HopMapper.")
    void testTransferwarehouseEntityToTransferwarehouseDto() {
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

        Transferwarehouse transferwarehouse = (Transferwarehouse) HopMapper.INSTANCE.entityToDto(transferwarehouseEntity);
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