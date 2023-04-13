package com.example.petshelter.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "report")
public class Report {
    @Id
    @Column(name = "id", nullable = false)
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
