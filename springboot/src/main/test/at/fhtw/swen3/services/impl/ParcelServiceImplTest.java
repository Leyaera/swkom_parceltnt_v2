package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.persistence.repositories.TransferwarehouseRepository;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.exception.BLValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ParcelServiceImplTest {
    private ParcelServiceImpl parcelServiceImpl;
    private Parcel parcel;
    @Mock
    private ParcelRepository mockedParcelRepository;

    @Mock
    private HopRepository mockedHopRepository;

    @Mock
    private TransferwarehouseRepository mockedTransferwarehouseRepository;

    @BeforeEach
    public void init() {

        MockitoAnnotations.initMocks(this);
        ParcelLogic parcelLogic = new ParcelLogic(mockedParcelRepository, mockedHopRepository, mockedTransferwarehouseRepository);
        parcelServiceImpl = new ParcelServiceImpl(parcelLogic);

        Recipient recipient = new Recipient();
        recipient.setName("Max Mustermann");
        recipient.setStreet("Landstraße 27a");
        recipient.setPostalCode("A-1110");
        recipient.setCity("Wien");
        recipient.setCountry("Austria");

        Recipient sender = new Recipient();
        sender.setName("Maria Musterfrau");
        sender.setStreet("Musterfraustraße 100b");
        sender.setPostalCode("38193");
        sender.setCity("München");
        sender.setCountry("Germany");

        parcel = new Parcel();
        parcel.setWeight(25.f);
        parcel.setRecipient(recipient);
        parcel.setSender(sender);
    }

    @Test
    @DisplayName("Generated alphanumeric tracking id matches the given pattern \"^[A-Z0-9]{9}$\" for 10 times.")
    void randomAlphanumericTrackingId() {
        int testCount = 10;
        for (int i = 0; i < testCount; i++)  {
            String generatedString = parcelServiceImpl.parcelLogic.randomAlphanumericTrackingId();
            assertTrue(generatedString.matches("^[A-Z0-9]{9}$"));
        }
    }

    @Test
    @DisplayName("SubmitParcel returns NewParcel Info (not null) and valid trackingId.")
    public void submitParcel() throws BLValidationException {
        NewParcelInfo newParcelInfo = parcelServiceImpl.submitParcel(parcel);
        assertNotNull(newParcelInfo);
        assertTrue(newParcelInfo.getTrackingId().matches("^[A-Z0-9]{9}$"));
    }
}