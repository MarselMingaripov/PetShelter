package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.entity.User;
import com.example.petshelter.exception.AnimalIsReservedException;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatRepository;
import com.example.petshelter.service.CatService;
import com.example.petshelter.service.MessageToVolunteerService;
import com.example.petshelter.service.UserService;
import com.example.petshelter.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервисы для работы с БД кошек
 */
@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final ValidationService validationService;
    private final UserService userService;
    private final MessageToVolunteerService messageToVolunteerService;


    @Override
    public Cat createCat(Cat cat) {
        if (!validationService.validate(cat)) {
            throw new ValidationException(cat.toString());
        }
        return catRepository.save(cat);
    }

    /**
     * Поиск животного по его идентификатору в БД.
     * Используется метод репозитория {@link CatRepository#findById(Object)}
     *
     * @param id
     * @return
     * @throws NotFoundInBdException если животное не найдено в БД
     */
    @Override
    public Cat findById(Long id) {
        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isPresent()) {
            return cat.get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    /**
     * Поиск и обновление данных о животном в БД по его идентификатору.
     * Используются методы репозитория {@link CatRepository#findById(Object)} и {@link CatRepository#save(Object)}
     *
     * @param id  - ид обновляемой записи
     * @param cat - на что обновляем
     * @return
     * @throws NotFoundInBdException если животное не найдено в БД
     */
    @Override
    public Cat updateById(Long id, Cat cat) {
        if (catRepository.findById(id).isPresent()) {
            cat.setId(id);
            return catRepository.save(cat);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    /**
     * Поиск и удаление данных о животном в БД по его идентификатору.
     * Используются методы репозитория {@link CatRepository#findById(Object)} и {@link CatRepository#deleteById(Object)}
     *
     * @param id
     * @return
     * @throws NotFoundInBdException если животное не найдено в БД
     */
    @Override
    public Cat deleteById(Long id) {
        Cat cat = findById(id);
        catRepository.delete(cat);
        return cat;
    }

    /**
     * Вывод полного списка животных из БД.
     * Используется метод репозитория {@link CatRepository#findAll}
     *
     * @return
     */
    @Override
    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    // TODO: 14.04.2023 исправить в контрллере на показать всех вообще + написать метод для показа только тех, кто имеет статус в приюте

    /**
     * Поиск в БД животного по его имени.
     * Используется метод репозитория {@link CatRepository#findByName(String)}
     *
     * @param name
     * @return
     */
    @Override
    public Cat findByName(String name) {
        return catRepository.findByName(name).get();
    }

    // TODO: 14.04.2023 написать метод в контроллере
    @Override
    public List<Cat> showAllByStatus(StatusAnimal statusAnimal) {
        return findAll().stream()
                .filter(x -> x.getStatusAnimal().equals(statusAnimal))
                .collect(Collectors.toList());
    }

    // TODO: 14.04.2023 добавить описание в сваггере
    //404, 405
    @Override
    public Cat reserveCat(String name, String phone) {
        User user;
        if (catRepository.existsByName(name)) {
            if (userService.findByPhone(phone) == null) {
                user = new User(phone);
                userService.createUser(user);
            } else {
                user = userService.findByPhone(phone);
            }
            Cat cat = findByName(name);
            if (cat.getStatusAnimal().equals(StatusAnimal.RESERVED)) {
                throw new AnimalIsReservedException("Кошка уже забронирована");
            }
            cat.setStatusAnimal(StatusAnimal.RESERVED);
            MessageToVolunteer message = new MessageToVolunteer(user.getPhoneNumber(), "забронировал кошку " + cat.getName());
            messageToVolunteerService.createMessageToVolunteer(message);
            return createCat(cat);
        } else {
            throw new NotFoundInBdException("Нет кота с таком именем");
        }
    }

    // TODO: 14.04.2023 написать метод в контроллере
    @Override
    public Cat changeStatusAnimal(String name, StatusAnimal statusAnimal) {
        if (catRepository.existsByName(name)) {
            Cat cat = findByName(name);
            cat.setStatusAnimal(statusAnimal);
            return createCat(cat);
        } else {
            throw new NotFoundInBdException("Кот с таким именем не найден");
        }
    }

}
