package by.fin.module.validator.api;

import by.fin.module.validator.impl.BeforeDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= BeforeDateValidator.class)
public @interface BeforeDate {
    String message() default "The date is outside the upper limit of the analyzed period";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
