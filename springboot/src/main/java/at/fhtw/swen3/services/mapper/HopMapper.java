package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Hop;

import at.fhtw.swen3.services.dto.Transferwarehouse;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.dto.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HopMapper extends BasicMapper<HopEntity, Hop>{
    HopMapper INSTANCE = Mappers.getMapper(HopMapper.class);

    @SubclassMapping(source = WarehouseEntity.class, target = Warehouse.class)
    @SubclassMapping(source = TruckEntity.class, target = Truck.class)
    @SubclassMapping(source = TransferwarehouseEntity.class, target = Transferwarehouse.class)
    Hop entityToDto(HopEntity entity);
}