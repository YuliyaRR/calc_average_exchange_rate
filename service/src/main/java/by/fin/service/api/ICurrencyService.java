package by.fin.service.api;

import by.fin.module.core.dto.Currency;
import by.fin.module.entity.CurrencyEntity;

public interface ICurrencyService {

    CurrencyEntity get(String symbol);

    CurrencyEntity save(Currency currency);
}
