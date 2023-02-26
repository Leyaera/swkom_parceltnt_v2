package at.fhtw.swen3.services.mapper;

import org.mapstruct.Mapper;

public interface BasicMapper <EN, DTO> {
    DTO entityToDto(EN entity);
    EN dtoToEntity(DTO dto);
}
