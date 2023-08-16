package org.example.service.service.api;

import org.example.service.core.dto.AverageRate;
import org.example.service.core.dto.Rate;
import org.example.service.validator.api.AfterDate;
import org.example.service.validator.api.BeforeDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import java.util.List;

public interface IExchangeRateService {

    List<Rate> getRatesPeriod(@NotNull String symbol, @AfterDate LocalDate start, @BeforeDate LocalDate end);

    List<Rate> getAll(@NotNull String symbol);

    AverageRate getAverageRatePerMonth(@Valid AverageRate averageRate);

}
