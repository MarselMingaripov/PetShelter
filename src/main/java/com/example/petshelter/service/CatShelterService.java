package com.example.petshelter.service;

import com.example.petshelter.entity.shelter.СatShelter;

import java.util.List;

public interface CatShelterService {
    /**
     * сохраняет в базу данных
     * @param catShelter
     * @return
     */
    СatShelter createCat(СatShelter catShelter);
    СatShelter findById(Long id);
    СatShelter updateById(Long id, СatShelter catShelter);
    СatShelter deleteById(Long id);
    List<СatShelter> findAll();
}
