package by.fin.service.impl;

import by.fin.module.core.dto.*;
import by.fin.module.core.dto.Currency;
import by.fin.module.core.dto.error.ErrorCode;
import by.fin.module.core.exceptions.ConnectionAPIException;
import by.fin.module.core.exceptions.ConversionTimeException;
import by.fin.module.core.exceptions.NotFoundDataBaseException;
import by.fin.module.entity.CurrencyEntity;
import by.fin.module.entity.RateEntity;
import by.fin.module.repositories.IRatesRepository;
import by.fin.module.validator.api.AfterDate;
import by.fin.module.validator.api.BeforeDate;
import by.fin.service.api.ICurrencyService;
import by.fin.service.api.IExchangeRateService;
import by.fin.service.api.IWeekendService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Validated
@Service
@RequiredArgsConstructor
public class ExchangeRateService implements IExchangeRateService {
    @Value("${nbrb.api.url.rate.period}")
    private String URL_RATES_PERIOD;
    @Value("${nbrb.api.url.rate.type}")
    private String URL_RATES_TYPE;
    private final IRatesRepository ratesRepository;
    private final ICurrencyService currencyService;
    private final IWeekendService weekendService;
    private final ConversionService conversionService;
    private final RestTemplate restTemplate;

    @Override
    public List<Rate> getRatesPeriod(@NotNull String symbol, @AfterDate LocalDate start, @BeforeDate LocalDate end) {
        long daysBetween = ChronoUnit.DAYS.between(start, end) + 1;
        CurrencyEntity currencyEntity;

        try {
            currencyEntity = currencyService.get(symbol);
        } catch (NotFoundDataBaseException e) {
            currencyEntity = getFromApiBySymbol(symbol);
        }

        List<RateEntity> listRates = ratesRepository.findByIdWhereDateBetweenStartAndEnd(currencyEntity.getCurId(), start, end);

        if(!conversionService.canConvert(RateEntity.class, Rate.class)) {
            throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
        }

        if (listRates.size() == daysBetween) {
            return listRates.stream().
                    map(entity -> conversionService.convert(entity, Rate.class))
                    .toList();
        } else {
            Rate[] rates = restTemplate.getForObject(String.format(URL_RATES_PERIOD, currencyEntity.getCurId(), start, end), Rate[].class);
            if (Objects.nonNull(rates)) {
                if(listRates.isEmpty()) {
                    RateEntity.RateEntityBuilder builder = RateEntity.builder();
                    for (Rate rate : rates) {
                        listRates.add(builder
                                .currency(currencyEntity)
                                .rate(rate.getRate())
                                .date(rate.getDate())
                                .build());
                    }
                } else {
                    int startSize = listRates.size();
                    int i = 0;
                    List<RateEntity> newRates = new ArrayList<>();
                    RateEntity.RateEntityBuilder builder = RateEntity.builder();
                    for (int j = 0; j < startSize; j++) {
                        if (!listRates.get(i).getDate().equals(rates[j].getDate())) {
                            newRates.add(builder
                                    .currency(currencyEntity)
                                    .rate(rates[j].getRate())
                                    .date(rates[j].getDate())
                                    .build());
                        } else {
                            i++;
                        }
                    }

                    for (int j = i; j < rates.length; j++) {
                        newRates.add(builder
                                .currency(currencyEntity)
                                .rate(rates[j].getRate())
                                .date(rates[j].getDate())
                                .build());
                    }

                    listRates = newRates;
                }
                ratesRepository.saveAll(listRates);

                return listRates.stream()
                        .map(entity -> conversionService.convert(entity, Rate.class))
                        .toList();
            } else {
                throw new ConnectionAPIException("Problems connecting to an external service", ErrorCode.ERROR);
            }
        }
    }

    @Override
    public List<Rate> getAll(@NotNull String symbol) {
        if(!conversionService.canConvert(RateEntity.class, Rate.class)) {
            throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
        }
        return ratesRepository.findByCurrencyOrderByDate(currencyService.get(symbol))
                .stream()
                .map(entity -> conversionService.convert(entity, Rate.class))
                .toList();

    }

    @Override
    public AverageRate getAverageRatePerMonth(@Valid AverageRate averageRate) {
        List<Weekend> allWorkDaysInMonth = weekendService.findAllWorkDaysInMonth(averageRate.getMonth());
        int degree = allWorkDaysInMonth.size();

        int serialMonth = averageRate.getMonth();
        Month month = Arrays.stream(
                Months.values())
                .filter(mon -> mon
                        .getMonth()
                        .getSerialNumber() == serialMonth)
                .findFirst()
                .get()
                .getMonth();

        int year = (serialMonth == 12) ? 2022 : 2023;
        LocalDate start = LocalDate.of(year, serialMonth, 1);
        LocalDate end = LocalDate.of(year, serialMonth, month.getAmountDays());

        CurrencyEntity currencyEntity;

        try {
            currencyEntity = currencyService.get(averageRate.getSymbol());
        } catch (NotFoundDataBaseException e) {
            currencyEntity = getFromApiBySymbol(averageRate.getSymbol());
        }

        Rate[] rates = restTemplate.getForObject(String.format(URL_RATES_PERIOD, currencyEntity.getCurId(), start, end), Rate[].class);

        double global = 1.0;

        int j = 0;
        if (Objects.nonNull(rates)) {
            for (int i = 0; j < degree; i++) {
                if(allWorkDaysInMonth.get(j).getCalendarDate().equals(rates[i].getDate())) {
                    global = global * rates[i].getRate();
                    j++;
                }
            }
        } else {
            throw new ConnectionAPIException("Problems connecting to an external service", ErrorCode.ERROR);
        }

        double average = Math.pow(global, 1.0 / degree);

        averageRate.setAverageRate(average);

        return averageRate;
    }

    private CurrencyEntity getFromApiBySymbol(String symbol) {
        CurrencyEntity currencyEntity;
        while (true) {
            Rate rate = restTemplate.getForObject(String.format(URL_RATES_TYPE, symbol), Rate.class);
            if (Objects.nonNull(rate)) {
                currencyEntity = currencyService.save(new Currency(rate.getId(), rate.getSymbol()));
                break;
            }
        }
        return currencyEntity;
    }
}
