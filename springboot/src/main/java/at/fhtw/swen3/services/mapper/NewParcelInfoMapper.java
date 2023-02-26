package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewParcelInfoMapper extends BasicMapper<ParcelEntity, NewParcelInfo>{
    NewParcelInfoMapper INSTANCE = Mappers.getMapper(NewParcelInfoMapper.class);
}
