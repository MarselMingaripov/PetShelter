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
    Report findById(Long id);
    Report updateById(Long id, Report report);
    Report deleteById(Long id);
    List<Report> findAll();
}
