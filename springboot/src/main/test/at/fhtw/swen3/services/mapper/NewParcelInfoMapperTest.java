package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.State;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewParcelInfoMapperTest {
    @Test
    void parcelEntityToNewParcelInfoDto() {
        ParcelEntity p = new ParcelEntity(null, null, null, "testid",
                null, null, null);

        NewParcelInfo newParcelInfo = ParcelMapper.INSTANCE.parcelEntityToNewParcelInfoDto(p);
        assertNotNull(newParcelInfo);
        assertEquals("testid", newParcelInfo.getTrackingId());
    }

    @Test
    void newParcelInfoDtoToParcelEntity() {
        NewParcelInfo n = new NewParcelInfo();
        n.trackingId("PYJRB4HZ6");

        ParcelEntity p = NewParcelInfoMapper.INSTANCE.dtoToEntity(n);
        assertNotNull(p);
        assertEquals(n.getTrackingId(), p.getTrackingId());
    }
}