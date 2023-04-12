package com.example.petshelter.service;

import com.example.petshelter.entity.Report;

import java.util.List;

public interface ReportService {
    /**
     * сохраняет в базу данных
     * @param report
     * @return
     */
    Report createReport(Report report);
    /**
     * поиск по ид
     * @param id
     * @return
     */
    Report findById(Long id);
    /**
     * обновить запись
     * @param id - ид обновляемой записи
     * @param report - на что обновляем
     * @return
     */
    Report updateById(Long id, Report report);
    /**
     * удаление по ид
     * @param id
     * @return
     */
    Report deleteById(Long id);
    /**
     * список всех записей
     * @return
     */
    List<Report> findAll();
}
