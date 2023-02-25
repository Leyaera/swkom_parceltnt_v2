package at.fhtw.swen3.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PostalCodeValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PostalCode {
    String message() default "Postal code does not match pattern.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
