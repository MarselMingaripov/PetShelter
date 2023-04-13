package com.example.petshelter.service.impl;

import com.example.petshelter.entity.*;
import com.example.petshelter.entity.shelter.DogShelter;
import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.service.ValidationService;
import org.apache.commons.lang3.StringUtils;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Cat cat) {
        return cat != null
                && cat.getAge() >= 0
                && validateString(cat.getName());

    }

    @Override
    public boolean validate(Dog dog) {
        return dog != null
                && dog.getAge() >= 0
                && validateString(dog.getName());
    }

    @Override
    public boolean validate(CatShelter catShelter) {
        return catShelter != null
                && validateString(catShelter.getInformation())
                && validateString(catShelter.getAddress())
                && validateString(catShelter.getPhoneNumber())
                && validateString(catShelter.getWorkSchedule())
                && validateString(catShelter.getSecurityContacts())
                && validateString(catShelter.getSafetyRecommendations());
    }

    @Override
    public boolean validate(DogShelter dogShelter) {
        return dogShelter != null
                && validateString(dogShelter.getInformation())
                && validateString(dogShelter.getAddress())
                && validateString(dogShelter.getPhoneNumber())
                && validateString(dogShelter.getWorkSchedule())
                && validateString(dogShelter.getSecurityContacts())
                && validateString(dogShelter.getSafetyRecommendations());
    }

    @Override
    public boolean validate(CatOwner catOwner) {
        return catOwner != null
                && validateString(catOwner.getPhoneNumber());
    }

    @Override
    public boolean validate(DogOwner dogOwner) {
        return dogOwner != null
                && validateString(dogOwner.getPhoneNumber());
    }
    @Override
    public boolean validateString(String str) {
        return str != null
                && !StringUtils.isEmpty(str)
                && !StringUtils.isBlank(str);
    }
}
