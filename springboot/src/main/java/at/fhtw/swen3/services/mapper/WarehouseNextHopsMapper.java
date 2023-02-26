package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.mapstruct.factory.Mappers;

public interface WarehouseNextHopsMapper extends BasicMapper<WarehouseNextHopsEntity, WarehouseNextHops>{
    WarehouseNextHopsMapper INSTANCE = Mappers.getMapper(WarehouseNextHopsMapper.class);
}