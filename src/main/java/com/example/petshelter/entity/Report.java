package com.example.petshelter.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Report {

    private Long id;
    private byte[] photo;
    private String foodRation;
    private String generalHealth;
    private String behaviorChanges;
    private LocalDate receiveDate;

    public Report(byte[] photo, String foodRation, String generalHealth, String behaviorChanges) {
        this.photo = photo;
        this.foodRation = foodRation;
        this.generalHealth = generalHealth;
        this.behaviorChanges = behaviorChanges;
        this.receiveDate = LocalDate.now();
    }
}
