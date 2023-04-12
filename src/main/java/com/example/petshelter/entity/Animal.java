package com.example.petshelter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    String name;
    int age;
    boolean healthStatus;
    boolean vaccination;
    Long userId;

    private StatusAnimal statusAnimal;

}
