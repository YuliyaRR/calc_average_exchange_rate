package by.fin.service.impl;

import by.fin.module.core.dto.Currency;
import by.fin.module.core.dto.error.ErrorCode;
import by.fin.module.core.exceptions.NotFoundDataBaseException;
import by.fin.module.entity.CurrencyEntity;
import by.fin.module.repositories.ICurrencyRepository;
import by.fin.service.api.ICurrencyService;
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
