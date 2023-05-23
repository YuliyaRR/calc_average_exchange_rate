package by.fin.module.validator.impl;

import by.fin.module.validator.api.AfterDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AfterDateValidator implements ConstraintValidator<AfterDate, LocalDate> {
    private static final LocalDate minDate = LocalDate.of(2022, 12, 1);
    private static final LocalDate maxDate = LocalDate.of(2023, 5, 31);
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return (value.isAfter(minDate) || value.isEqual(minDate)) && value.isBefore(maxDate) ;
    }
}
