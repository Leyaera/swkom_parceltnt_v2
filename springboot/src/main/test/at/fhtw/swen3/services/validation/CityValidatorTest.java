package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.RecipientEntity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityValidatorTest {
    private final CityValidator cityValidator = new CityValidator();

    @Test
    @DisplayName("Other country than \"Austria\" or \"Österreich\" is always true.")
    public void otherCountryThanAustriaIsTrue() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Straße mit Sonderzeichen % 5",
                "91234",
                "München",
                "Deutschland"
        );
        boolean result = cityValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("City in Austria with valid pattern \"Wien\".")
    public void austrianCityWithValidPattern() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "Wien",
                "Austria"
        );
        boolean result = cityValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("City in Austria with valid pattern \"Wi-en\". (dash)")
    public void austrianCityWithValidPatternWithDash() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "Wi-en",
                "Austria"
        );
        boolean result = cityValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("City in Austria with valid pattern \"Wi en\". (space)")
    public void austrianCityWithValidPatternWithSpace() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "Wi en",
                "Austria"
        );
        boolean result = cityValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("City in Austria with valid pattern \"Wi/en\". (slash)")
    public void austrianCityWithValidPatternWithSlash() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "Wi/en",
                "Austria"
        );
        boolean result = cityValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("City in Österreich with valid pattern \"Wien\"")
    public void oesterreichCityWithValidPattern() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "Wien",
                "Österreich"
        );
        boolean result = cityValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Austrian city with invalid pattern \"W1en\".")
    public void austrianCityWithInvalidPatternNumber() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "W1en",
                "Austria"
        );
        boolean result = cityValidator.isValid(r, null);
        assertFalse(result);
    }

    @Test
    @DisplayName("City in Oesterreich with invalid pattern \"W1en\".")
    public void oesterreichCityWithInvalidPatternNumber() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "W1en",
                "Österreich"
        );
        boolean result = cityValidator.isValid(r, null);
        assertFalse(result);
    }
}