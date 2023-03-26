package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.services.exception.BLValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
@Component
public class BLValidator {

    public static BLValidator INSTANCE = new BLValidator();

    public
    static ValidatorFactory getValidatorFactory() {
        return Validation.buildDefaultValidatorFactory();
    }

    Validator getValidator() {
        return getValidatorFactory().getValidator();
    }

    public <T> void validate(T o) throws BLValidationException {
        Validator validator = getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> v : violations) {
                log.error(v.getMessage());
                throw new BLValidationException(null, v.getMessage());
            }
        }
    }
}
