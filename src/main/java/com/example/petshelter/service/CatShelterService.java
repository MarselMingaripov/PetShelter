package com.example.petshelter.service;

import com.example.petshelter.entity.shelter.CatShelter;

import java.util.List;

public interface CatShelterService {
    /**
     * сохраняет в базу данных
     * @param catShelter
     * @return
     */
    CatShelter createСatShelter(CatShelter catShelter);
    /**
     * поиск по ид
     * @param id
     * @return
     */
    CatShelter findById(Long id);
    /**
     * обновление по ид
     * @param id - ид обновляемой записи
     * @param catShelter - на что обновляем
     * @return
     */
    CatShelter updateById(Long id, CatShelter catShelter);
    /**
     * удалить по ид
     * @param id
     * @return
     */
    CatShelter deleteById(Long id);
    /**
     * список всех записей
     * @return
     */
    List<CatShelter> findAll();


    String returnInformation();

    String returnAddressAndWorkSchedule();

    String returnSecurityContacts();

    String returnSafetyRecommendations();

    void addCatToShelter(String name);

    void addCatOwnerToShelter(String phoneNumber);
}
