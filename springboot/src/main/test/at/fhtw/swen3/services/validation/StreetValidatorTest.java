package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreetValidatorTest {
    private final StreetValidator streetValidator = new StreetValidator();

    @Test
    @DisplayName("Other country than Austria is always true. (\"Musterstraße 123 mit Sonderzeichen % 5\")")
    public void otherCountryIsAlwaysTrue() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Musterstraße 123 mit Sonderzeichen % 5",
                "91234",
                "München",
                "Deutschland"
        );
        boolean result = streetValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Invalid street in Austria is false. (\"Musterstraße 123 mit Sonderzeichen % 5\")")
    public void invalidStreetInAustriaIsAlwaysFalse() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Musterstraße 123 mit Sonderzeichen % 5",
                "A-1110",
                "Wien",
                "Austria"
        );
        boolean result = streetValidator.isValid(r, null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Street in Austria with slashes is valid. (\"Hauptstraße 12/12/12\"")
    public void austrianStreetWithSlashesIsValid() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Hauptstraße 12/12/12",
                "A-1110",
                "Wien",
                "Austria"
        );
        boolean result = streetValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Street in Austria with character in number is valid. (\"Landstraße 27a\"")
    public void austrianStreetWithCharactersInNumberIsValid() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Landstraße 27a",
                "A-1110",
                "Wien",
                "Austria"
        );
        boolean result = streetValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Street in Austria without blank is invalid. (\"Landstraße27a\"")
    public void austrianStreetWithoutBlankIsInValid() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Landstraße27a",
                "A-1110",
                "Wien",
                "Austria"
        );
        boolean result = streetValidator.isValid(r, null);
        assertFalse(result);
    }
}