package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatRepository;

import java.util.List;

/**
 * Сервис для работы с БД кошек
 */
public interface CatService {
    /**
     * Внесение данных о новом животном в БД.
     * Используется метод репозитория {@link CatRepository#save(Object)}
     * @throws ValidationException при ошибке валидации полей создаваемого животного
     * @param cat
     * @return
     */
    Cat createCat(Cat cat);
    /**
     * Поиск животного по его идентификатору в БД.
     * Используется метод репозитория {@link CatRepository#findById(Object)}
     * @throws NotFoundInBdException если животное не найдено в БД
     * @param id
     * @return
     */
    Cat findById(Long id);
    /**
     * Поиск и обновление данных о животном в БД по его идентификатору.
     * Используются методы репозитория {@link CatRepository#findById(Object)} и {@link CatRepository#save(Object)}
     * @throws NotFoundInBdException если животное не найдено в БД
     * @param id - ид обновляемой записи
     * @param cat - на что обновляем
     * @return
     */
    Cat updateById(Long id, Cat cat);
    /**
     * Поиск и удаление данных о животном в БД по его идентификатору.
     * Используются методы репозитория {@link CatRepository#findById(Object)} и {@link CatRepository#deleteById(Object)}
     * @throws NotFoundInBdException если животное не найдено в БД
     * @param id
     * @return
     */

    Cat deleteById(Long id);
    /**
     * Вывод полного списка животных из БД.
     * Используется метод репозитория {@link CatRepository#findAll}
     * @return
     */
    List<Cat> findAll();
    /**
     * Поиск в БД животного по его имени.
     * Используется метод репозитория {@link CatRepository#findByName(String)}
     * @param name
     * @return
     */
    Cat findByName(String name);
}
