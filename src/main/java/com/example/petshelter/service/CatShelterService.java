package com.example.petshelter.service;

import com.example.petshelter.entity.shelter.СatShelter;

import java.util.List;

public interface CatShelterService {
    /**
     * сохраняет в базу данных
     * @param catShelter
     * @return
     */
    СatShelter createСatShelter(СatShelter catShelter);
    /**
     * поиск по ид
     * @param id
     * @return
     */
    СatShelter findById(Long id);
    /**
     * обновление по ид
     * @param id - ид обновляемой записи
     * @param catShelter - на что обновляем
     * @return
     */
    СatShelter updateById(Long id, СatShelter catShelter);
    /**
     * удалить по ид
     * @param id
     * @return
     */
    СatShelter deleteById(Long id);
    /**
     * список всех записей
     * @return
     */
    List<СatShelter> findAll();


    String returnInformation();

    String returnAddressAndWorkSchedule();

    String returnSecurityContacts();

    String returnSafetyRecommendations();

    void addCatToShelter(String name);

    void addCatOwnerToShelter(String phoneNumber);
}
