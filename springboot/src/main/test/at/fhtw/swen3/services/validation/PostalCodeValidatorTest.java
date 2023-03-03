package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalCodeValidatorTest {
    private final PostalCodeValidator postalCodeValidator = new PostalCodeValidator();

    @Test
    @DisplayName("Other country than \"Austria\" is always true.")
    public void otherCountryIsAlwaysTrue() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Musterstraße 123",
                "91234%5",
                "München",
                "Deutschland"
        );
        boolean result = postalCodeValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Austrian postal code with valid pattern \"A-1234\".")
    public void austrianPostalCodeWithValidPattern() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Musterstraße 123",
                "A-1234",
                "Wien",
                "Austria"
        );
        boolean result = postalCodeValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Austrian postal code with invalid pattern \"B-1234\". (starts with B)")
    public void austrianPostalCodeWithInValidPattern1() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Musterstraße 123",
                "B-1234",
                "Wien",
                "Austria"
        );
        boolean result = postalCodeValidator.isValid(r, null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Austrian postal code with invalid pattern \"A-12345\". (five numbers)")
    public void austrianPostalCodeWithInValidPattern2() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Musterstraße 123",
                "A-12345",
                "Wien",
                "Austria"
        );
        boolean result = postalCodeValidator.isValid(r, null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Austrian postal code with invalid pattern \"A-12E4\". (letters instead of numbers)")
    public void austrianPostalCodeWithInValidPattern3() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Musterstraße 123",
                "A-12E4",
                "Wien",
                "Austria"
        );
        boolean result = postalCodeValidator.isValid(r, null);
        assertFalse(result);
    }
}