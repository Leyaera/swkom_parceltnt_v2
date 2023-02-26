package at.fhtw.swen3.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    String message() default "In Austria: Name must match pattern.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
