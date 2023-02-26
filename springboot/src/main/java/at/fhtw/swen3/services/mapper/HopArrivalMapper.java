package at.fhtw.swen3.services.mapper;


import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;

import org.mapstruct.factory.Mappers;

public interface HopArrivalMapper extends BasicMapper<HopArrivalEntity, HopArrival>{
    HopArrivalMapper INSTANCE = Mappers.getMapper(HopArrivalMapper.class);
}