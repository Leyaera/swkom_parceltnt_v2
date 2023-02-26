package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import org.mapstruct.factory.Mappers;

public interface TruckMapper extends BasicMapper<TruckEntity, Truck>{
    TruckMapper INSTANCE = Mappers.getMapper(TruckMapper.class);
}