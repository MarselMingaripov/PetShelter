package com.example.petshelter.service;

import com.example.petshelter.entity.DogOwner;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatOwnerRepository;
import com.example.petshelter.repository.DogOwnerRepository;

import java.util.List;
/**
 * Сервис для работы с БД опекунов собак
 */
public interface DogOwnerService {
    /**
     * Внесение данных о новом опекуне собаки в БД.
     * Используется метод репозитория {@link DogOwnerRepository#save(Object)}
     * @throws ValidationException при ошибке валидации полей создаваемого опекуна собаки
     * @param dogOwner
     * @return
     */
    DogOwner createDogOwner(DogOwner dogOwner);

    /**
     * Поиск опекуна собаки по его идентификатору в БД.
     * Используется метод репозитория {@link DogOwnerRepository#findById(Object)}
     * @throws NotFoundInBdException если опекун собаки не найден в БД
     * @param id
     * @return
     */
    DogOwner findById(Long id);

    /**
     * Поиск и обновление данных об опекуне собаки в БД по его идентификатору.
     * Используются методы репозитория {@link DogOwnerRepository#findById(Object)} и {@link DogOwnerRepository#save(Object)}
     * @throws NotFoundInBdException если опекун собаки не найдено в БД
     * @param id - ид обновляемой записи
     * @param dogOwner - на что обновляем
     * @return
     */
    DogOwner updateById(Long id, DogOwner dogOwner);

    /**
     * Поиск и удаление данных об опекуне собаки в БД по его идентификатору.
     * Используются методы репозитория {@link DogOwnerRepository#findById(Object)} и {@link DogOwnerRepository#deleteById(Object)}
     * @throws NotFoundInBdException если опекун собаки не найдено в БД
     * @param id
     * @return
     */
    DogOwner deleteById(Long id);

    /**
     * Вывод полного списка опекунов собак из БД.
     * Используется метод репозитория {@link DogOwnerRepository#findAll}
     * @return
     */
    List<DogOwner> findAll();

    Boolean existsByPhoneNumber(String phoneNumber);
    /**
     * Поиск в БД опекуна собаки по его номеру телефона.
     * Используется метод репозитория {@link CatOwnerRepository#findByPhoneNumber(String)}
     * @param phoneNumber
     * @return
     */
    DogOwner findByPhoneNumber(String phoneNumber);
}
