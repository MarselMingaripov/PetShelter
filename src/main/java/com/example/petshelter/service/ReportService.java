package com.example.petshelter.service;

import com.example.petshelter.entity.Report;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.ReportRepository;

import java.util.List;
/**
 * Сервис для работы с БД отчетов
 */
public interface ReportService {
    /**
     * Внесение отчетов в БД.
     * Используется метод репозитория {@link ReportRepository#save(Object)}
     * @throws ValidationException при ошибке валидации полей создаваемого отчета
     * @param report
     * @return
     */
    Report createReport(Report report);
    /**
     * Поиск отчетов по его идентификатору в БД.
     * Используется метод репозитория {@link ReportRepository#findById(Object)}
     * @throws NotFoundInBdException если отчет не найден в БД
     * @param id
     * @return
     */
    Report findById(Long id);
    /**
     * Поиск и обновление отчета в БД по его идентификатору.
     * Используются методы репозитория {@link ReportRepository#findById(Object)} и {@link ReportRepository#save(Object)}
     * @throws NotFoundInBdException если отчет не найдено в БД
     * @param id - ид обновляемой записи
     * @param report - на что обновляем
     * @return
     */
    Report updateById(Long id, Report report);
    /**
     * Поиск и удаление отчета в БД по его идентификатору.
     * Используются методы репозитория {@link ReportRepository#findById(Object)} и {@link ReportRepository#deleteById(Object)}
     * @throws NotFoundInBdException если отчет не найден в БД
     * @param id
     * @return
     */
    Report deleteById(Long id);
    /**
     * Вывод полного списка отчетов из БД.
     * Используется метод репозитория {@link ReportRepository#findAll}
     * @return
     */
    List<Report> findAll();
}
