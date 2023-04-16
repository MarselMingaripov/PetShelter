package com.example.petshelter.service;

import com.example.petshelter.entity.Dog;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.DogRepository;

import java.util.List;
/**
 * Сервис для работы с БД собак
 */
public interface DogService {
    /**
     * Внесение данных о новом животном в БД.
     * Используется метод репозитория {@link DogRepository#save(Object)}
     * @throws ValidationException при ошибке валидации полей создаваемого животного
     * @param dog
     * @return
     */
    Dog createDog(Dog dog);
    /**
     * Поиск животного по его идентификатору в БД.
     * Используется метод репозитория {@link DogRepository#findById(Object)}
     * @throws NotFoundInBdException если животное не найдено в БД
     * @param id
     * @return
     */
    Dog findById(Long id);
    /**
     * Поиск и обновление данных о животном в БД по его идентификатору.
     * Используются методы репозитория {@link DogRepository#findById(Object)} и {@link DogRepository#save(Object)}
     * @throws NotFoundInBdException если животное не найдено в БД
     * @param id - ид обновляемой записи
     * @param dog - на что обновляем
     * @return
     */
    Dog updateById(Long id, Dog dog);
    /**
     * Поиск и удаление данных о животном в БД по его идентификатору.
     * Используются методы репозитория {@link DogRepository#findById(Object)} и {@link DogRepository#deleteById(Object)}
     * @throws NotFoundInBdException если животное не найдено в БД
     * @param id
     * @return
     */
    Dog deleteById(Long id);
    /**
     * Вывод полного списка животных из БД.
     * Используется метод репозитория {@link DogRepository#findAll}
     * @return
     */
    List<Dog> findAll();
    /**
     * Поиск в БД животного по его имени.
     * Используется метод репозитория {@link DogRepository#findByName(String)}
     * @param name
     * @return
     */
    Dog findByName(String name);

}
