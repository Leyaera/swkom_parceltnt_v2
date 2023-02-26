package at.fhtw.swen3.services.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StreetValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Street {
    String message() default "In Austria: Street name must match pattern.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}