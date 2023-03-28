package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WarehouseNextHopsMapperTest {
    @Test
    @DisplayName("Test WarehouseNextHopsEntity to WarehouseNextHopsDto.")
    void testEntityToDto()
    {
        WarehouseNextHopsEntity warehouseNextHopsEntity = new WarehouseNextHopsEntity();
        warehouseNextHopsEntity.setHop(null);
        warehouseNextHopsEntity.setTraveltimeMins(325);

        WarehouseNextHops warehouseNextHops = WarehouseNextHopsMapper.INSTANCE.entityToDto(warehouseNextHopsEntity);

        assertEquals(warehouseNextHops.getHop(), warehouseNextHopsEntity.getHop());
        assertEquals(warehouseNextHops.getTraveltimeMins(), warehouseNextHopsEntity.getTraveltimeMins());
    }

    @Test
    @DisplayName("Test WarehouseNextHopsDto to WarehouseNextHopsEntity.")
    void testDtoToEntity()
    {
        WarehouseNextHops warehouseNextHops = new WarehouseNextHops();
        warehouseNextHops.setHop(null);
        warehouseNextHops.setTraveltimeMins(123);

        WarehouseNextHopsEntity warehouseNextHopsEntity = WarehouseNextHopsMapper.INSTANCE.dtoToEntity(warehouseNextHops);

        assertEquals(warehouseNextHops.getHop(), warehouseNextHopsEntity.getHop());
        assertEquals(warehouseNextHops.getTraveltimeMins(), warehouseNextHopsEntity.getTraveltimeMins());
    }
}