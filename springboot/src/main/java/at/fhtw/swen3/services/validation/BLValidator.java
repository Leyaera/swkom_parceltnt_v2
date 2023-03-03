package at.fhtw.swen3.services.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class BLValidator {
    static ValidatorFactory getValidatorFactory() {
        return Validation.buildDefaultValidatorFactory();
    }

    Validator getValidator() {
        return getValidatorFactory().getValidator();
    }

    public <T> boolean validate(T o) {
        Validator validator = getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> v : violations) {
                // TODO log here!
                System.out.println(v);
            }
        }
        return violations.isEmpty();
    }
}
