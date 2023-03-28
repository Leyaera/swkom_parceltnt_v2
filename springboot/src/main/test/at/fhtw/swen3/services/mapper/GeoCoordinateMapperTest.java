package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeoCoordinateMapperTest {
    @Test
    @DisplayName("Test GeoCoordinateEntity to GeoCoordinateDto.")
    void testEntityToDto()
    {
        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();
        geoCoordinateEntity.setLat(11.22);
        geoCoordinateEntity.setLon(333.444);

        GeoCoordinate geoCoordinate = GeoCoordinateMapper.INSTANCE.entityToDto(geoCoordinateEntity);

        assertEquals(geoCoordinateEntity.getLat(), geoCoordinate.getLat());
        assertEquals(geoCoordinateEntity.getLon(), geoCoordinate.getLon());
    }

    @Test
    @DisplayName("Test GeoCoordinateDto to GeoCoordinateEntity.")
    void testDtoToEntity()
    {
        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLat(11.22);
        geoCoordinate.setLon(333.444);

        GeoCoordinateEntity geoCoordinateEntity = GeoCoordinateMapper.INSTANCE.dtoToEntity(geoCoordinate);

        assertEquals(geoCoordinateEntity.getLat(), geoCoordinate.getLat());
        assertEquals(geoCoordinateEntity.getLon(), geoCoordinate.getLon());
    }
}