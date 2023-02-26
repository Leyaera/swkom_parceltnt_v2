package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Hop;

import org.mapstruct.factory.Mappers;

public interface HopMapper extends BasicMapper<HopEntity, Hop>{
    HopMapper INSTANCE = Mappers.getMapper(HopMapper.class);
}