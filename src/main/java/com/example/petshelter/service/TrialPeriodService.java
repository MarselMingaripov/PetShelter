package com.example.petshelter.service;

import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.TrialPeriod;

import java.util.List;

public interface TrialPeriodService {

    /**
     * сохраняет в базу данных
     * @param trialPeriod
     * @return
     */
    TrialPeriod createTrialPeriod(TrialPeriod trialPeriod);

    /**
     * поиск по ид
     * @param id
     * @return
     */
    TrialPeriod findById(Long id);

    /**
     * обновить запись
     * @param id - ид обновляемой записи
     * @param trialPeriod - на что обновляем
     * @return
     */
    TrialPeriod updateById(Long id, TrialPeriod trialPeriod);

    /**
     * удаление по ид
     * @param id
     * @return
     */
    TrialPeriod deleteById(Long id);

    /**
     * список всех записей
     * @return
     */
    List<TrialPeriod> findAll();
}
