package com.example.petshelter.service;

import com.example.petshelter.entity.shelter.DogShelter;


import java.util.List;

public interface DogShelterService {
    /**
     * сохраняет в базу данных
     * @param dogShelter
     * @return
     */
    DogShelter createCat(DogShelter dogShelter);
    DogShelter findById(Long id);
    DogShelter updateById(Long id, DogShelter catShelter);
    DogShelter deleteById(Long id);
    List<DogShelter> findAll();
}
