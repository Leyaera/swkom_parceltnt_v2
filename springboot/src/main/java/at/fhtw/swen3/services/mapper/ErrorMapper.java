package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;
import org.mapstruct.factory.Mappers;

public interface ErrorMapper extends BasicMapper<ErrorEntity, Error>{
    ErrorMapper INSTANCE = Mappers.getMapper(ErrorMapper.class);
}