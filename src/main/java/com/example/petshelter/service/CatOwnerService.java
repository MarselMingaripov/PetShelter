package com.example.petshelter.service;

import com.example.petshelter.entity.CatOwner;

import java.util.List;

public interface CatOwnerService {
    /**
     * сохраняет в базу данных
     * @param catOwner
     * @return
     */
    CatOwner createCatOwner(CatOwner catOwner);

    /**
     * поиск по ид
     * @param id
     * @return
     */
    CatOwner findById(Long id);

    /**
     * обновление по ид
     * @param id - ид обновляемой записи
     * @param catOwner - на что обновляем
     * @return
     */
    CatOwner updateById(Long id, CatOwner catOwner);

    /**
     * удалить по ид
     * @param id
     * @return
     */
    CatOwner deleteById(Long id);

    /**
     * список всех записей
     * @return
     */
    List<CatOwner> findAll();

    Boolean existsByPhoneNumber(String phoneNumber);

    CatOwner findByPhoneNumber(String phoneNumber);
}
