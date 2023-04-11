package com.example.petshelter.service;

import com.example.petshelter.entity.Dog;

import java.util.List;

public interface DogService {
    /**
     * сохраняет в базу данных
     * @param dog
     * @return
     */
    Dog createCat(Dog dog);
    Dog findById(Long id);
    Dog updateById(Long id, Dog dog);
    Dog deleteById(Long id);
    List<Dog> findAll();

    Dog findByName(String name);

}
