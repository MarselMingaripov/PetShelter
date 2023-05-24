package com.example.petshelter.service;

import com.example.petshelter.entity.TrialPeriod;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.TrialPeriodRepository;

import java.util.List;
/**
 * Сервис для работы с БД испытательных сроков
 */
public interface TrialPeriodService {
    /**
     * Внесение испытательных сроков в БД.
     * Используется метод репозитория {@link TrialPeriodRepository#save(Object)}
     * @throws ValidationException при ошибке валидации полей создаваемого испытательного срока
     * @param trialPeriod
     * @return
     */
    TrialPeriod createTrialPeriod(TrialPeriod trialPeriod);
    /**
     * Поиск испытательного срока по его идентификатору в БД.
     * Используется метод репозитория {@link TrialPeriodRepository#findById(Object)}
     * @throws NotFoundInBdException если испытательный срок не найден в БД
     * @param id
     * @return
     */
    TrialPeriod findById(Long id);
    /**
     * Поиск и обновление испытательного срока в БД по его идентификатору.
     * Используются методы репозитория {@link TrialPeriodRepository#findById(Object)} и {@link TrialPeriodRepository#save(Object)}
     * @throws NotFoundInBdException если испытательный срок не найдено в БД
     * @param id - ид обновляемой записи
     * @param trialPeriod - на что обновляем
     * @return
     */
    TrialPeriod updateById(Long id, TrialPeriod trialPeriod);
    /**
     * Поиск и удаление испытательного срока в БД по его идентификатору.
     * Используются методы репозитория {@link TrialPeriodRepository#findById(Object)} и {@link TrialPeriodRepository#deleteById(Object)}
     * @throws NotFoundInBdException если испытательный срок не найден в БД
     * @param id
     * @return
     */
    TrialPeriod deleteById(Long id);
    /**
     * Вывод полного списка испытательных сроков из БД.
     * Используется метод репозитория {@link TrialPeriodRepository#findAll}
     * @return
     */
    List<TrialPeriod> findAll();
}
