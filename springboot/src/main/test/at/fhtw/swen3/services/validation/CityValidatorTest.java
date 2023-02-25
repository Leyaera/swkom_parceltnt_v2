package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.RecipientEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityValidatorTest {
    private final CityValidator citiValidator = new CityValidator();

    @Test
    @DisplayName("Other country than \"Austria\" or \"Österreich\" is always true.")
    public void otherCountryThanAustriaIsTrue() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Deutschland");
        r.setCity("Wien");

        boolean result = citiValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("City in Austria with valid pattern \"Wien\".")
    public void austrianCityWithValidPattern() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setCity("Wien");

        boolean result = citiValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("City in Austria with valid pattern \"Wi-en\". (dash)")
    public void austrianCityWithValidPatternWithDash() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setCity("Wi-en");

        boolean result = citiValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("City in Austria with valid pattern \"Wi en\". (space)")
    public void austrianCityWithValidPatternWithSpace() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setCity("Wi en");

        boolean result = citiValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("City in Austria with valid pattern \"Wi/en\". (slash)")
    public void austrianCityWithValidPatternWithSlash() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setCity("Wi/en");

        boolean result = citiValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("City in Österreich with valid pattern \"Wien\"")
    public void oesterreichCityWithValidPattern() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Österreich");
        r.setCity("Wien");

        boolean result = citiValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Austrian city with invalid pattern \"W1en\".")
    public void austrianCityWithInvalidPatternNumber() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setCity("W1en");

        boolean result = citiValidator.isValid(r, null);
        assertFalse(result);
    }

    @Test
    @DisplayName("City in Oesterreich with invalid pattern \"W1en\".")
    public void oesterreichCityWithInvalidPatternNumber() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Österreich");
        r.setCity("W1en");

        boolean result = citiValidator.isValid(r, null);
        assertFalse(result);
    }
}