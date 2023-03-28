package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Warehouse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WarehouseMapperTest {
    @Test
    @DisplayName("Test WarehouseEntity to WarehouseDto.")
    void testEntityToDto() {
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

        Warehouse warehouse = WarehouseMapper.INSTANCE.entityToDto(warehouseEntity);
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
    @DisplayName("Test WarehouseDto to WarehouseEntity.")
    void testDtoToEntity() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLevel(5);
        warehouse.setHopType("warehouse");
        warehouse.setNextHops(new ArrayList<>());
        warehouse.setDescription("Warehouse description");
        warehouse.setCode("WJAO47");
        warehouse.setLocationName("Wien");
        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLat(11.22);
        geoCoordinate.setLon(33.44);
        warehouse.setLocationCoordinates(geoCoordinate);
        warehouse.setProcessingDelayMins(123);

        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
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
}