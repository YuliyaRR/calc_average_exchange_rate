package by.fin.module.validator.api;

import by.fin.module.validator.impl.AfterDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= AfterDateValidator.class)
public @interface AfterDate {
    String message() default "The date is outside the lower limit of the analyzed period";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
