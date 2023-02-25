package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTests {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("HopArrivalC code fits correct pattern \"ABCD1234\".")
    public void hopArrivalCodeIsValid() {
        HopArrivalEntity h = new HopArrivalEntity();
        h.setCode("ABCD1234");
        Set<ConstraintViolation<HopArrivalEntity>> violations = validator.validate(h);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("HopArrival code does not fit correct pattern \"ABCD12E4\".")
    public void hopArrivalCodeIsInValid() {
        HopArrivalEntity h = new HopArrivalEntity();
        h.setCode("ABCD12E4");
        Set<ConstraintViolation<HopArrivalEntity>> violations = validator.validate(h);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("HopArrival/Warehouse description fits correct pattern \"Warehouse 27-12\".")
    public void hopArrivalDescriptionIsValid() {
        HopArrivalEntity h = new HopArrivalEntity();
        h.setDescription("Warehouse 27-12");
        Set<ConstraintViolation<HopArrivalEntity>> violations = validator.validate(h);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("HopArrival/Warehouse description does not fit correct pattern \"Warehouse 27+12\".")
    public void hopArrivalDescriptionIsInValid() {
        HopArrivalEntity h = new HopArrivalEntity();
        h.setDescription("Warehouse 27+12");
        Set<ConstraintViolation<HopArrivalEntity>> violations = validator.validate(h);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("ParcelTracking code fits correct pattern \"PYJRB4HZ6\".")
    public void ParcelTrackingCodeIsValid() {
        ParcelEntity p = new ParcelEntity();
        p.setTrackingId("PYJRB4HZ6");
        Set<ConstraintViolation<ParcelEntity>> violations = validator.validate(p);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("ParcelTracking code does not fit correct pattern \"PYJRB4HZ6_P\".")
    public void ParcelTrackingCodeIsInValid() {
        ParcelEntity p = new ParcelEntity();
        p.setTrackingId("PYJRB4HZ6_P");
        Set<ConstraintViolation<ParcelEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Parcel weight is valid (> 0.0).")
    public void parcelWeightIsValid() {
        ParcelEntity p = new ParcelEntity();
        p.setWeight(2.5f);
        Set<ConstraintViolation<ParcelEntity>> violations = validator.validate(p);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Parcel weight is invalid (< 0.0).")
    public void parcelWeightIsInValidNegative() {
        ParcelEntity p = new ParcelEntity();
        p.setWeight(-0.5f);
        Set<ConstraintViolation<ParcelEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Parcel weight is invalid (= 0.0).")
    public void parcelWeightIsInValidZero() {
        ParcelEntity p = new ParcelEntity();
        p.setWeight(0.0f);
        Set<ConstraintViolation<ParcelEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
    }
}
