package com.example.petshelter.service;

import com.example.petshelter.entity.*;
import com.example.petshelter.entity.shelter.DogShelter;
import com.example.petshelter.entity.shelter.CatShelter;

public interface ValidationService {

    public boolean validate(Cat cat);

    public boolean validate(Dog dog);

    public boolean validate(CatShelter catShelter);

    public boolean validate(DogShelter dogShelter);

    public boolean validate(CatOwner catOwner);

    public boolean validate(DogOwner dogOwner);

    public boolean validateString(String str);
}
