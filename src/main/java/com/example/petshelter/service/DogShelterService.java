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
    String returnInformation(Long id);

    String returnPhone(Long id);

    /**
     * расписание работы приюта и адрес, схему проезда
     * @return
     */
    String returnAddressAndWorkSchedule(Long id);
    /**
     * контактные данные охраны для оформления пропуска на машину
     * @return
     */
    String returnSecurityContacts(Long id);
    /**
     * общие рекомендации о технике безопасности на территории приюта
     * @return
     */
    String returnSafetyRecommendations(Long id);
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

    String returnDating(Long id);

    String returnDocuments(Long id);

    String returnTransportation(Long id);

    String returnArrangementPuppy(Long id);

    String returnArrangementDog(Long id);

    String returnDogHandlerRecommendations(Long id);

    String returnRecommendedDogHandlers(Long id);

    String returnArrangementDisabled(Long id);

    String returnCancelDogTaker(Long id);

    void addDating(Long id, String data);

    void addDocuments(Long id, String data);

    void addTransportation(Long id, String data);

    void addArrangementPuppy(Long id, String data);

    void addArrangementDog(Long id, String data);

    void addDogHandlerRecommendations(Long id, String data);

    void addRecommendedDogHandlers(Long id, String data);

    void addArrangementDisabled(Long id, String data);

    void addCancelDogTaker(Long id, String data);
}
