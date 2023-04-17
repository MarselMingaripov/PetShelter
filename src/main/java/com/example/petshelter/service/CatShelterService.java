package com.example.petshelter.service;

import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatShelterRepository;

import java.util.List;
/**
 * Сервис для работы с БД приютов для кошек
 */
public interface CatShelterService {
    /**
     * Внесение данных о новом приюте для кошек в БД.
     * Используется метод репозитория {@link CatShelterRepository#save(Object)}
     * @throws ValidationException при ошибке валидации полей создаваемого приюта для кошек
     * @param catShelter
     * @return
     */
    CatShelter createCatShelter(CatShelter catShelter);
    /**
     * Поиск приюта для кошек по его идентификатору в БД.
     * Используется метод репозитория {@link CatShelterRepository#findById(Object)}
     * @throws NotFoundInBdException если приют для кошек не найден в БД
     * @param id
     * @return
     */
    CatShelter findById(Long id);
    /**
     * Поиск и обновление данных о приюте для кошек в БД по его идентификатору.
     * Используются методы репозитория {@link CatShelterRepository#findById(Object)} и {@link CatShelterRepository#save(Object)}
     * @throws NotFoundInBdException если приют для кошек не найден в БД
     * @param id - ид обновляемой записи
     * @param catShelter - на что обновляем
     * @return
     */
    CatShelter updateById(Long id, CatShelter catShelter);
    /**
     * Поиск и удаление данных о приюте для кошек в БД по его идентификатору.
     * Используются методы репозитория {@link CatShelterRepository#findById(Object)} и {@link CatShelterRepository#deleteById(Object)}
     * @throws NotFoundInBdException если приют для кошек не найден в БД
     * @param id
     * @return
     */
    CatShelter deleteById(Long id);
    /**
     * Вывод полного списка приютов для кошек из БД.
     * Используется метод репозитория {@link CatShelterRepository#findAll}
     * @return
     */
    List<CatShelter> findAll();
    /**
     * получение информация о приюте
     * @param id
     * @return
     */
    String returnInformation(Long id);
    /**
     * расписание работы приюта и адрес, схему проезда
     * @param id
     * @return
     */
    String returnAddressAndWorkSchedule(Long id);
    /**
     * контактные данные охраны для оформления пропуска на машину
     * @param id
     * @return
     */
    String returnSecurityContacts(Long id);
    /**
     * общие рекомендации о технике безопасности на территории приюта
     * @param id
     * @return
     */
    String returnSafetyRecommendations(Long id);
    /**
     * добавление кота в БД приюта
     * @param name
     */
    void addCatToShelter(String name);
    /**
     * добавление опекуна кота в БД прюта
     * @param phoneNumber
     */
    void addCatOwnerToShelter(String phoneNumber);
}
