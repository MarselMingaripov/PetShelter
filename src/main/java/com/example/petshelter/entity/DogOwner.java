package com.example.petshelter.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DogOwner extends User{

    private List<Dog> dogs;

    public DogOwner(Long id, String phoneNumber, List<TrialPeriod> trialPeriodsInActiveStatus, List<TrialPeriod> trialPeriodsCompleted, List<Dog> dogs) {
        super(id, phoneNumber, trialPeriodsInActiveStatus, trialPeriodsCompleted);
        this.dogs = dogs;
    }
}
