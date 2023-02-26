package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.State;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Track;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TrackingInformationMapperTest {

    @Test
    void parcelEntityToTrackingInformationDto() {
        ParcelEntity p = new ParcelEntity(null, null, null, "testid",
                State.DELIVERED, Collections.emptyList(), Collections.emptyList());

        TrackingInformation newTrackingInformation = ParcelMapper.INSTANCE.parcelEntityToTrackingInformationDto(p);
        assertNotNull(newTrackingInformation);
        assertEquals(newTrackingInformation.getState().getValue(), TrackingInformation.StateEnum.DELIVERED.getValue());
    }

    @Test
    void trackingInformationDtoToParcelEntity() {
        TrackingInformation t = new TrackingInformation();
        t.state(TrackingInformation.StateEnum.DELIVERED);

        ParcelEntity p = TrackingInformationMapper.INSTANCE.dtoToEntity(t);
        assertNotNull(p);
        assertEquals(t.getState().getValue(), p.getState().getValue());
    }
}