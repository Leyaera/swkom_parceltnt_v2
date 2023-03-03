package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class BLValidatorTest {
    private final BLValidator blValidator = new BLValidator();
    HopArrivalEntity h;
    private RecipientEntity r;
    private RecipientEntity s;

    @BeforeEach
    public void setUp() {
        h = new HopArrivalEntity(
                "ABCD1234",
                "Warehouse 27-12",
                OffsetDateTime.now()
        );

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

    // DEBUG!!!!
    @Test
    @DisplayName("Testing CityValidator in BLValidatorFactory with wrong city name in Austria (\"W1en\".")
    public void cityValidatorTestInValidatorFactoryInvalid() {
        r.setCity("W1en");
        assertFalse(blValidator.validate(r));
    }

    @Test
    @DisplayName("HopArrivalC code fits correct pattern \"ABCD1234\".")
    public void hopArrivalCodeIsValid() {
        assertTrue(blValidator.validate(h));
    }

    @Test
    @DisplayName("HopArrival code does not fit correct pattern \"ABCD12E4\".")
    public void hopArrivalCodeIsInValid() {
        h.setCode("ABCD12E4");
        assertFalse(blValidator.validate(h));
    }

    @Test
    @DisplayName("HopArrival/Warehouse description fits correct pattern \"Warehouse 27-12\".")
    public void hopArrivalDescriptionIsValid() {
        assertTrue(blValidator.validate(h));
    }

    @Test
    @DisplayName("HopArrival/Warehouse description does not fit correct pattern \"Warehouse 27+12\".")
    public void hopArrivalDescriptionIsInValid() {
        h.setDescription("Warehouse 27+12");
        assertFalse(blValidator.validate(h));
    }

    @Test
    @DisplayName("ParcelTracking code fits correct pattern \"PYJRB4HZ6\".")
    public void ParcelTrackingCodeIsValid() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertTrue(blValidator.validate(p));
    }

    @Test
    @DisplayName("ParcelTracking code does not fit correct pattern \"PYJRB4HZ6_P\".")
    public void ParcelTrackingCodeIsInValid() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                s,
                "PYJRB4HZ6_P",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertFalse(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel weight is valid (> 0.0).")
    public void parcelWeightIsValid() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertTrue(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel weight is invalid (< 0.0).")
    public void parcelWeightIsInValidNegative() {
        ParcelEntity p = new ParcelEntity(
                -0.5f,
                r,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertFalse(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel weight is invalid (= 0.0).")
    public void parcelWeightIsInValidZero() {
        ParcelEntity p = new ParcelEntity(
                0.0f,
                r,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertFalse(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel recipient is not null.")
    public void parcelRecipientNotNull() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertTrue(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel recipient is null. (invalid)")
    public void parcelRecipientIsNull() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                null,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertFalse(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel sender is not null.")
    public void parcelSenderNotNull() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertTrue(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel sender is null. (invalid)")
    public void parcelSenderIsNull() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                null,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertFalse(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel State is not null.")
    public void parcelStateNotNull() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertTrue(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel State is null. (invalid)")
    public void parcelStateIsNull() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                s,
                "PYJRB4HZ6",
                null,
                new ArrayList<>(),
                new ArrayList<>());
        assertFalse(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel visitedHops is not null.")
    public void parcelVisitedHopsNotNull() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertTrue(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel visitedHops is null. (invalid)")
    public void parcelVisitedHopsIsNull() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                null,
                new ArrayList<>());
        assertFalse(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel futureHops is not null.")
    public void parcelFutureHopsNotNull() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                new ArrayList<>());
        assertTrue(blValidator.validate(p));
    }

    @Test
    @DisplayName("Parcel futureHops is null. (invalid)")
    public void parcelFutureHopsIsNull() {
        ParcelEntity p = new ParcelEntity(
                2.5f,
                r,
                s,
                "PYJRB4HZ6",
                State.DELIVERED,
                new ArrayList<>(),
                null);
        assertFalse(blValidator.validate(p));
    }
}