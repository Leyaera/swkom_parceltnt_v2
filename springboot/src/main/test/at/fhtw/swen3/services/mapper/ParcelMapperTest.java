package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.State;
import at.fhtw.swen3.services.dto.Parcel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

class ParcelMapperTest {

    RecipientEntity r;
    RecipientEntity s;
    @BeforeEach
    public void init() {
        r = new RecipientEntity(
                "Max Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "Wien",
                "Austria"
        );
        s = new RecipientEntity(
                "Maria Musterfrau",
                "Mustergasse 23",
                "D-12893",
                "München",
                "Deutschland"
        );
    }
    @Test
    void parcelEntityToParcelDto() {
        ParcelEntity p = new ParcelEntity(
                12.0f,
                r,
                s,
                "testid",
                State.DELIVERED,
                Collections.emptyList(),
                Collections.emptyList()
        );
        Parcel parcelDto = ParcelMapper.INSTANCE.entityToDto(p);
        assertNotNull(parcelDto);
        assertEquals(p.getWeight(), parcelDto.getWeight());
    }

    @Test
    void parcelDtoToParcelEntity() {
        Parcel parcelDto = new Parcel();
        parcelDto.weight(12.0f);

        ParcelEntity p = ParcelMapper.INSTANCE.dtoToEntity(parcelDto);
        assertNotNull(p);
        assertEquals(parcelDto.getWeight(), p.getWeight());
    }
}