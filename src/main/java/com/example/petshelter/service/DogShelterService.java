package com.example.petshelter.service;

import com.example.petshelter.entity.shelter.DogShelter;


import java.util.List;

public interface DogShelterService {
    /**
     * сохраняет в базу данных
     * @param dogShelter
     * @return
     */
    DogShelter createDogShelter(DogShelter dogShelter);
    DogShelter findById(Long id);
    DogShelter updateById(Long id, DogShelter dogShelter);
    DogShelter deleteById(Long id);
    List<DogShelter> findAll();

    String returnInformation();

    String returnAddressAndWorkSchedule();

    String returnSecurityContacts();

    String returnSafetyRecommendations();

    void addDogToShelter(String name);

    void addDogOwnerToShelter(String phoneNumber);
}
