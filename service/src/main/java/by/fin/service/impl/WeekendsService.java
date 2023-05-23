package by.fin.service.impl;

import by.fin.module.core.dto.Weekend;
import by.fin.module.core.dto.error.ErrorCode;
import by.fin.module.core.exceptions.ConversionTimeException;
import by.fin.module.entity.WeekendEntity;
import by.fin.module.repositories.IWeekendsRepository;

import by.fin.service.api.IWeekendService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WeekendsService implements IWeekendService {
   private final IWeekendsRepository weekendsRepository;
   private final ConversionService conversionService;

    @Override
    public List<Weekend> findAll() {
        if(!conversionService.canConvert(WeekendEntity.class, Weekend.class)) {
            throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
        }
       return weekendsRepository.findAll()
               .stream()
               .map(entity -> conversionService.convert(entity, Weekend.class))
               .toList();
    }

    @Override
    public List<Weekend> findAllWorkDaysInMonth(int month) {
        if(!conversionService.canConvert(WeekendEntity.class, Weekend.class)) {
            throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
        }
        return weekendsRepository.findAllWorkDaysInMonth(month)
                .stream()
                .map(entity -> conversionService.convert(entity, Weekend.class))
                .toList();
    }
}
