package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.RecipientEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<Name, RecipientEntity> {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Z][ a-zA-Z-/]*");

    @Override
    public void initialize (Name constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    public boolean isValid(RecipientEntity recipient, ConstraintValidatorContext constraintValidatorContext) {
        if("Österreich".equals(recipient.getCountry()) || "Austria".equals(recipient.getCountry())) {
            return NAME_PATTERN.matcher(recipient.getCity()).matches();
        } else {
            return true;
        }
    }
}