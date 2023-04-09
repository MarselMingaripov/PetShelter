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
    CatOwner findById(Long id);
    CatOwner updateById(Long id, CatOwner catOwner);
    CatOwner deleteById(Long id);
    List<CatOwner> findAll();
}
