package org.example.service.service.impl;

import org.example.service.core.dto.Currency;
import org.example.service.core.dto.error.ErrorCode;
import org.example.service.core.exceptions.NotFoundDataBaseException;
import org.example.dao.entity.CurrencyEntity;
import org.example.dao.repositories.ICurrencyRepository;
import org.example.service.service.api.ICurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService implements ICurrencyService {
    private final ICurrencyRepository currencyRepository;

    @Override
    public CurrencyEntity get(String symbol) {
        return currencyRepository.findBySymbol(symbol).orElseThrow(() -> new NotFoundDataBaseException("Currency not found in database", ErrorCode.ERROR));
    }

    @Override
    public CurrencyEntity save(Currency currency) {
        return currencyRepository.save(new CurrencyEntity(currency.getId(), currency.getSymbol()));
    }
}
