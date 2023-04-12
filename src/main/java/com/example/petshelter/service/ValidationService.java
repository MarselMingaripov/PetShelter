package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.DogOwner;
import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.entity.shelter.DogShelter;

public interface ValidationService{

    public boolean validate(Cat cat);
    public boolean validate(Dog cat);
    public boolean validate(CatShelter catShelter);
    public boolean validate(DogShelter dogShelter);
    public boolean validate(CatOwner catOwner);
    public boolean validate(DogOwner dogOwner);

}
