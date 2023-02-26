package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.State;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ParcelMapperTest {

    @Test
    void parcelEntityToParcelDto() {
        ParcelEntity p = new ParcelEntity(12.0f, new RecipientEntity(), new RecipientEntity(), "testid",
                State.DELIVERED, Collections.emptyList(), Collections.emptyList());

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