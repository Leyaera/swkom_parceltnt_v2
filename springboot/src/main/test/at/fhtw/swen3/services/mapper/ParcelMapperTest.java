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
    void parcelEntityToNewParcelInfoDto() {
        ParcelEntity p = new ParcelEntity("testid", null, null, null,
                null, null, null);

        NewParcelInfo newParcelInfo = ParcelMapper.INSTANCE.parcelEntityToNewParcelInfoDto(p);
        assertNotNull(newParcelInfo);
        assertEquals("testid", newParcelInfo.getTrackingId());
    }

    @Test
    void parcelEntityToTrackingInformationDto() {
        ParcelEntity p = new ParcelEntity("testid", null, null, null,
                State.DELIVERED, Collections.emptyList(), Collections.emptyList());

        TrackingInformation newTrackingInformation = ParcelMapper.INSTANCE.parcelEntityToTrackingInformationDto(p);
        assertNotNull(newTrackingInformation);
        assertEquals(newTrackingInformation.getState().getValue(), TrackingInformation.StateEnum.DELIVERED.getValue());
    }

    @Test
    void parcelEntityToParcelDto() {
        ParcelEntity p = new ParcelEntity("testid", 12.0f, new RecipientEntity(), new RecipientEntity(),
                State.DELIVERED, Collections.emptyList(), Collections.emptyList());

        Parcel parcelDto = ParcelMapper.INSTANCE.parcelEntityToParcelDto(p);
        assertNotNull(parcelDto);
        assertEquals(p.getWeight(), parcelDto.getWeight());
    }

    @Test
    void parcelDtoToParcelEntity() {
        Parcel parcelDto = new Parcel();
        parcelDto.weight(12.0f);

        ParcelEntity p = ParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcelDto);
        assertNotNull(p);
        assertEquals(p.getWeight(), p.getWeight());
    }
}