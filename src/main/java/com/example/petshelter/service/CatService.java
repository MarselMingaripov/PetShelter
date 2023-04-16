package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatRepository;

import java.util.List;

/**
 * Сервисы для работы с БД кошек
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
     * поиск по ид
     * @param id
     * @return
     */
    Cat findById(Long id);
    /**
     * обновление по ид
     * @param id - ид обновляемой записи
     * @param cat - на что обновляем
     * @return
     */
    Cat updateById(Long id, Cat cat);
    /**
     * удалить по ид
     * @param id
     * @return
     */
    Cat deleteById(Long id);
    /**
     * список всех записей
     * @return
     */
    List<Cat> findAll();

    List<Cat> findAllInShelter();

    /**
     * поиск по имени
     * @param name
     * @return
     */
    Cat findByName(String name);

    /**
     * получение списка котов по их статусу
     * @param statusAnimal
     * @return
     */
    List<Cat> showAllByStatus(StatusAnimal statusAnimal);

    Cat reserveCat(String name, String phone);

    Cat changeStatusAnimal(String name, StatusAnimal statusAnimal);
}
