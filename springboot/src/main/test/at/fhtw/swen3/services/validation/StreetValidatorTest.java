package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreetValidatorTest {
    private final StreetValidator streetValidator = new StreetValidator();

    @Test
    @DisplayName("Street not in Austria is always true. (\"invalidStr33tin+Austria 123\")")
    public void nonAustrianStreetIsAlwaysTrue() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Germany");
        r.setStreet("invalidStr33tin+Austria 123");

        boolean result = streetValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Invalid street in Austria is false. (\"invalidStr33tin+Austria 123\")")
    public void invalidStreetInAustriaIsAlwaysFalse() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setStreet("invalidStr33tin+Austria 123");

        boolean result = streetValidator.isValid(r, null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Street in Austria with slashes is valid. (\"Hauptstraße 12/12/12\"")
    public void austrianStreetWithSlashesIsValid() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setStreet("Hauptstraße 12/12/12");

        boolean result = streetValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Street in Austria with character in number is valid. (\"Landstraße 27a\"")
    public void austrianStreetWithCharactersInNumberIsValid() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setStreet("Landstraße 27a");

        boolean result = streetValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Street in Austria without blank is invalid. (\"Landstraße27a\"")
    public void austrianStreetWithoutBlankIsInValid() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setStreet("Landstraße27a");

        boolean result = streetValidator.isValid(r, null);
        assertFalse(result);
    }
}