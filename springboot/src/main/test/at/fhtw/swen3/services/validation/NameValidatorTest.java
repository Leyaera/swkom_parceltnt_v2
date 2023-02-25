package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameValidatorTest {
    private final NameValidator nameValidator = new NameValidator();

    @Test
    @DisplayName("Other country than \"Austria\" or \"Österreich\" is always true.")
    public void otherCountryThanAustriaIsTrue() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Deutschland");
        r.setName("Mustermann");

        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Austria with valid pattern \"Maria\".")
    public void austrianNameWithValidPattern() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setName("Maria");

        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Austria with valid pattern \"Mustermann-Meier\". (dash)")
    public void austrianNameWithValidPatternWithDash() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setName("Mustermann-Meier");

        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Austria with valid pattern \"Maria Mustermann\". (space)")
    public void austrianNameWithValidPatternWithSpace() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setName("Maria Mustermann");

        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Austria with valid pattern \"Maria/Max Mustermann\". (slash)")
    public void austrianNameWithValidPatternWithSlash() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setName("Maria/Max Mustermann");

        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Österreich with valid pattern \"Maria\"")
    public void oesterreichNameWithValidPattern() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Österreich");
        r.setName("Maria");

        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Austria with invalid pattern \"Mar1a Mustermann\".")
    public void austrianNameWithInvalidPatternNumber() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Austria");
        r.setName("Mar1a Mustermann");

        boolean result = nameValidator.isValid(r, null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Name in Oesterreich with invalid pattern \"Mar1a Mustermann\".")
    public void oesterreichNameWithInvalidPatternNumber() {
        RecipientEntity r = new RecipientEntity();
        r.setCountry("Österreich");
        r.setName("Mar1a Mustermann");

        boolean result = nameValidator.isValid(r, null);
        assertFalse(result);
    }
}