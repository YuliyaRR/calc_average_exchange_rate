package org.example.service.service.impl;

import org.example.service.core.dto.Weekend;
import org.example.service.core.dto.error.ErrorCode;
import org.example.service.core.exceptions.ConversionTimeException;
import org.example.dao.entity.WeekendEntity;
import org.example.dao.repositories.IWeekendsRepository;

import org.example.service.service.api.IWeekendService;
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
