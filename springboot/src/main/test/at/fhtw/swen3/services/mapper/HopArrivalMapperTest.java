package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HopArrivalMapperTest {
    @Test
    @DisplayName("Test HopArrivalEntity to HopArrivalDto.")
    void testEntityToDto()
    {
        HopArrivalEntity hopArrivalEntity = new HopArrivalEntity();
        hopArrivalEntity.setCode("Code 1234");
        hopArrivalEntity.setDateTime(OffsetDateTime.now());
        hopArrivalEntity.setDescription("Description 5678");

        HopArrival hopArrival = HopArrivalMapper.INSTANCE.entityToDto(hopArrivalEntity);

        assertEquals(hopArrivalEntity.getCode(), hopArrival.getCode());
        assertEquals(hopArrivalEntity.getDateTime(), hopArrival.getDateTime());
        assertEquals(hopArrivalEntity.getDescription(), hopArrival.getDescription());
    }

    @Test
    @DisplayName("Test HopArrivalDto to HopArrivalEntity.")
    void testDtoToEntity()
    {
        HopArrival hopArrival = new HopArrival();
        hopArrival.setCode("Code 1234");
        hopArrival.setDateTime(OffsetDateTime.now());
        hopArrival.setDescription("Description 5678");

        HopArrivalEntity hopArrivalEntity = HopArrivalMapper.INSTANCE.dtoToEntity(hopArrival);

        assertEquals(hopArrivalEntity.getCode(), hopArrival.getCode());
        assertEquals(hopArrivalEntity.getDateTime(), hopArrival.getDateTime());
        assertEquals(hopArrivalEntity.getDescription(), hopArrival.getDescription());
    }
}