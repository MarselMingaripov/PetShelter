package com.example.petshelter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CatOwner extends User{
    private List<Cat> cats;

    public CatOwner(Long id, String phoneNumber, List<TrialPeriod> trialPeriodsInActiveStatus, List<TrialPeriod> trialPeriodsCompleted, List<Cat> cats) {
        super(id, phoneNumber, trialPeriodsInActiveStatus, trialPeriodsCompleted);
        this.cats = cats;
    }
}
