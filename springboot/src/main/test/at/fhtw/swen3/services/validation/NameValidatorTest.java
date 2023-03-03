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
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann mit Sonderzeichen % 5",
                "Musterstraße 123",
                "91234",
                "München",
                "Deutschland"
        );
        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Austria with valid pattern \"Max\".")
    public void austrianNameWithValidPattern() {
        RecipientEntity r = new RecipientEntity(
                "Max",
                "Landstraße 12/12",
                "A-1110",
                "Wien",
                "Austria"
        );
        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Austria with valid pattern \"Mustermann-Meier\". (dash)")
    public void austrianNameWithValidPatternWithDash() {
        RecipientEntity r = new RecipientEntity(
                "Mustermann-Meier",
                "Landstraße 12/12",
                "A-1110",
                "Wien",
                "Austria"
        );
        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Austria with valid pattern \"Max Mustermann\". (space)")
    public void austrianNameWithValidPatternWithSpace() {
        RecipientEntity r = new RecipientEntity(
                "Max Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "Wien",
                "Austria"
        );
        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Austria with valid pattern \"Maria/Max Mustermann\". (slash)")
    public void austrianNameWithValidPatternWithSlash() {
        RecipientEntity r = new RecipientEntity(
                "Maria/Max Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "Wien",
                "Austria"
        );
        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Österreich with valid pattern \"Maria\"")
    public void oesterreichNameWithValidPattern() {
        RecipientEntity r = new RecipientEntity(
                "Maria",
                "Landstraße 12/12",
                "A-1110",
                "Wien",
                "Österreich"
        );
        boolean result = nameValidator.isValid(r, null);
        assertTrue(result);
    }

    @Test
    @DisplayName("Name in Austria with invalid pattern \"Mar1a Mustermann\".")
    public void austrianNameWithInvalidPatternNumber() {
        RecipientEntity r = new RecipientEntity(
                "Mar1a Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "Wien",
                "Austria"
        );
        boolean result = nameValidator.isValid(r, null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Name in Österreich with invalid pattern \"Mar1a Mustermann\".")
    public void oesterreichNameWithInvalidPatternNumber() {
        RecipientEntity r = new RecipientEntity(
                "Mar1a Mustermann",
                "Landstraße 12/12",
                "A-1110",
                "Wien",
                "Österreich"
        );
        boolean result = nameValidator.isValid(r, null);
        assertFalse(result);
    }
}