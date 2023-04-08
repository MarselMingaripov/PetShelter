package com.example.petshelter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String phoneNumber;
    private AnimalTypeOwner animalTypeOwner;
    private List<Animal> animals;
    private List<TrialPeriod> trialPeriodsInActiveStatus;
    private List<TrialPeriod> trialPeriodsCompleted;

    public User(Long id) {
        this.id = id;
    }
}
