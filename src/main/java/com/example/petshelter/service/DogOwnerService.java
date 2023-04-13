package com.example.petshelter.service;

import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.DogOwner;

import java.util.List;

public interface DogOwnerService {
    /**
     * сохраняет в базу данных
     *
     * @param dogOwner
     * @return
     */
    DogOwner createDogOwner(DogOwner dogOwner);

    /**
     * поиск по ид
     *
     * @param id
     * @return
     */
    DogOwner findById(Long id);

    /**
     * обновление по ид
     *
     * @param id       - ид обновляемой записи
     * @param dogOwner - на что обновляем
     * @return
     */
    DogOwner updateById(Long id, DogOwner dogOwner);

    /**
     * удалить по ид
     *
     * @param id
     * @return
     */
    DogOwner deleteById(Long id);

    /**
     * список всех записей
     *
     * @return
     */
    List<DogOwner> findAll();

    Boolean existsByPhoneNumber(String phoneNumber);

    DogOwner findByPhoneNumber(String phoneNumber);
}
