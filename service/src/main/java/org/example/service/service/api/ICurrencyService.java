package org.example.service.service.api;

import org.example.service.core.dto.Currency;
import org.example.dao.entity.CurrencyEntity;

public interface ICurrencyService {

    CurrencyEntity get(String symbol);

    CurrencyEntity save(Currency currency);
}
