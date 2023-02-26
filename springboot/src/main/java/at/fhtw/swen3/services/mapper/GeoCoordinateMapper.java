package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.mapstruct.factory.Mappers;

public interface GeoCoordinateMapper extends BasicMapper<GeoCoordinateEntity, GeoCoordinate>{
    GeoCoordinateMapper INSTANCE = Mappers.getMapper(GeoCoordinateMapper.class);
}