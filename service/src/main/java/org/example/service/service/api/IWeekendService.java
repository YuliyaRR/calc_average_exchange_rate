package org.example.service.service.api;

import org.example.service.core.dto.Weekend;

import java.util.List;

public interface IWeekendService {
    List<Weekend> findAll();

    List<Weekend> findAllWorkDaysInMonth(int month);


}
