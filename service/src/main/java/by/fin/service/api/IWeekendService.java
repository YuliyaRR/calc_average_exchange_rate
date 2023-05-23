package by.fin.service.api;

import by.fin.module.core.dto.Weekend;

import java.util.List;

public interface IWeekendService {
    List<Weekend> findAll();

    List<Weekend> findAllWorkDaysInMonth(int month);


}
