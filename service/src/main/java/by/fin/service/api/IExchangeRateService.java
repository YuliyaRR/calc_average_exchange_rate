package by.fin.service.api;

import by.fin.module.core.dto.AverageRate;
import by.fin.module.core.dto.Rate;
import by.fin.module.validator.api.AfterDate;
import by.fin.module.validator.api.BeforeDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import java.util.List;

public interface IExchangeRateService {

    List<Rate> getRatesPeriod(@NotNull String symbol, @AfterDate LocalDate start, @BeforeDate LocalDate end);

    List<Rate> getAll(@NotNull String symbol);

    AverageRate getAverageRatePerMonth(@Valid AverageRate averageRate);

}
