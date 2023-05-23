package by.fin.web.controller;

import by.fin.module.core.dto.AverageRate;
import by.fin.module.core.dto.Rate;
import by.fin.module.validator.api.AfterDate;
import by.fin.module.validator.api.BeforeDate;
import by.fin.module.validator.api.ValidString;
import by.fin.service.api.IExchangeRateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Validated
@RestController
@RequestMapping(path = "api/currency")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final IExchangeRateService exchangeRateService;

    @GetMapping(path = "/dynamics/{symbol}")
    public ResponseEntity<List<Rate>> ratesPeriod(@PathVariable("symbol") @ValidString String symbol,
                                                  @RequestParam(name = "start") @AfterDate LocalDate start,
                                                  @RequestParam(name = "end") @BeforeDate LocalDate end){
        return new ResponseEntity<>(exchangeRateService.getRatesPeriod(symbol, start, end), HttpStatus.OK);
    }

    @GetMapping(path = "/{symbol}")
    public ResponseEntity<List<Rate>> allRates(@PathVariable("symbol") @ValidString String symbol){
        return new ResponseEntity<>(exchangeRateService.getAll(symbol), HttpStatus.OK);
    }

    @GetMapping(path = "/average")
    public ResponseEntity<AverageRate> averageRate(@RequestBody @Valid AverageRate averageRate) {
        return new ResponseEntity<>(exchangeRateService.getAverageRatePerMonth(averageRate), HttpStatus.OK);
    }

}
