package by.fin.module.validator.api;

import by.fin.module.validator.impl.ValidStringValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= ValidStringValidator.class)
public @interface ValidString {
    String message() default "Unacceptable symbols";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
