package com.example.petshelter.service;
import com.example.petshelter.entity.shelter.DogShelter;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.DogShelterRepository;
import java.util.List;
/**
 * Сервис для работы с БД приютов для собак
 */
public interface DogShelterService {
    /**
     * Внесение данных о новом приюте для собак в БД.
     * Используется метод репозитория {@link DogShelterRepository#save(Object)}
     * @throws ValidationException при ошибке валидации полей создаваемого приюта для собак
     * @param dogShelter
     * @return
     */
    DogShelter createDogShelter(DogShelter dogShelter);
    /**
     * Поиск приюта для собак по его идентификатору в БД.
     * Используется метод репозитория {@link DogShelterRepository#findById(Object)}
     * @throws NotFoundInBdException если приют для собак не найден в БД
     * @param id
     * @return
     */
    DogShelter findById(Long id);
    /**
     * Поиск и обновление данных о приюте для собак в БД по его идентификатору.
     * Используются методы репозитория {@link DogShelterRepository#findById(Object)} и {@link DogShelterRepository#save(Object)}
     * @throws NotFoundInBdException если приют для собак не найден в БД
     * @param id - ид обновляемой записи
     * @param dogShelter - на что обновляем
     * @return
     */
    DogShelter updateById(Long id, DogShelter dogShelter);
    /**
     * Поиск и удаление данных о приюте для собак в БД по его идентификатору.
     * Используются методы репозитория {@link DogShelterRepository#findById(Object)} и {@link DogShelterRepository#deleteById(Object)}
     * @throws NotFoundInBdException если приют для собак не найден в БД
     * @param id
     * @return
     */
    DogShelter deleteById(Long id);
    /**
     * Вывод полного списка приютов для собак из БД.
     * Используется метод репозитория {@link DogShelterRepository#findAll}
     * @return
     */
    List<DogShelter> findAll();
    /**
     * получение информация о приюте
     * @return
     */
    String returnInformation();
    /**
     * расписание работы приюта и адрес, схему проезда
     * @return
     */
    String returnAddressAndWorkSchedule();
    /**
     * контактные данные охраны для оформления пропуска на машину
     * @return
     */
    String returnSecurityContacts();
    /**
     * общие рекомендации о технике безопасности на территории приюта
     * @return
     */
    String returnSafetyRecommendations();
    /**
     * добавление собаки в БД приюта
     * @param name
     */
    void addDogToShelter(String name);
    /**
     * добавление опекуна собаки в БД прюта
     * @param phoneNumber
     */
    void addDogOwnerToShelter(String phoneNumber);
}
