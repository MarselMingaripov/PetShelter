package com.example.petshelter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    Long id;
    String name;
    int age;
    boolean healthStatus;
    boolean vaccination;

    private StatusAnimal statusAnimal;
    private TypeOfAnimal typeOfAnimal;

}
