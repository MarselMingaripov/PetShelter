package com.example.petshelter.service;

import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.StatusAnimal;

import java.util.List;

public interface DogService {
    /**
     * сохраняет в базу данных
     * @param dog
     * @return
     */
    Dog createDog(Dog dog);
    Dog findById(Long id);
    Dog updateById(Long id, Dog dog);
    Dog deleteById(Long id);
    List<Dog> findAll();

    List<Dog> findAllInShelter();

    Dog findByName(String name);

    List<Dog> showAllByStatus(StatusAnimal statusAnimal);

    //404, 405, 409
    Dog reserveDog(String name, String phone);

    Dog changeStatusAnimal(String name, StatusAnimal statusAnimal);
}
