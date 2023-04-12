package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.DogOwner;
import com.example.petshelter.entity.shelter.DogShelter;
import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.service.ValidationService;
import org.apache.commons.lang3.StringUtils;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Cat cat) {
        return cat != null
               && cat.getName() != null
               && !StringUtils.isEmpty(cat.getName())
               && cat.getAge() >=0
               && cat.isVaccination();
    }

    @Override
    public boolean validate(Dog cat) {
        return false;
    }

    @Override
    public boolean validate(CatShelter catShelter) {
        return false;
    }

    @Override
    public boolean validate(DogShelter dogShelter) {
        return false;
    }

    @Override
    public boolean validate(CatOwner catOwner) {
        return false;
    }

    @Override
    public boolean validate(DogOwner dogOwner) {
        return false;
    }
}
