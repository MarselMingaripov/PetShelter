package com.example.petshelter.service;

import com.example.petshelter.entity.*;
import com.example.petshelter.entity.shelter.DogShelter;
import com.example.petshelter.entity.shelter.CatShelter;

public interface ValidationService {
    /**
     * проверка
     * @param cat
     * @return
     */
    public boolean validate(Cat cat);

    /**
     * проверка
     * @param dog
     * @return
     */
    public boolean validate(Dog dog);

    /**
     * проверка
     * @param catShelter
     * @return
     */

    public boolean validate(CatShelter catShelter);

    /**
     * проверка
     * @param dogShelter
     * @return
     */

    public boolean validate(DogShelter dogShelter);

    /**
     * проверка
     * @param catOwner
     * @return
     */

    public boolean validate(CatOwner catOwner);

    /**
     * проверка
     * @param dogOwner
     * @return
     */

    public boolean validate(DogOwner dogOwner);

    /**
     * проверка
     * @param str
     * @return
     */

    public boolean validateString(String str);

    /**
     * проверка
     * @param phoneNumber
     * @return
     */

    public boolean validatePhoneNumber(String phoneNumber);
}
