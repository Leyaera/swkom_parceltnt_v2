package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipientMapper extends BasicMapper<RecipientEntity, Recipient>{
    RecipientMapper INSTANCE = Mappers.getMapper(RecipientMapper.class);
}