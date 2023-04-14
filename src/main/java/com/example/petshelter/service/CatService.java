package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;

import java.util.List;

/**
 * Сервисы для работы с БД кошек
 */
public interface CatService {
    /**
     * сохраняет в базу данных
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
    /**
     * поиск по имени
     * @param name
     * @return
     */
    Cat findByName(String name);
}
