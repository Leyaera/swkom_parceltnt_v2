package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehouseNextHopsMapper extends BasicMapper<WarehouseNextHopsEntity, WarehouseNextHops>{
    WarehouseNextHopsMapper INSTANCE = Mappers.getMapper(WarehouseNextHopsMapper.class);

    @SubclassMapping(source = Warehouse.class, target = WarehouseEntity.class)
    @SubclassMapping(source = Truck.class, target = TruckEntity.class)
    @SubclassMapping(source = Transferwarehouse.class, target = TransferwarehouseEntity.class)
    HopEntity hopToHopEntity(Hop hop);
}