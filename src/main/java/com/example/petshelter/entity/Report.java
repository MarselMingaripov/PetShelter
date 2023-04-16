package com.example.petshelter.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Класс - отчет опекуна животного
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "report")
public class Report {
    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Фото животного
     */
    private byte[] photo;
    /**
     * Рацион питания животного
     */
    private String foodRation;
    /**
     * Состояние здоровья животного
     */
    private String generalHealth;
    /**
     * Изменение в поведении животного
     */
    private String behaviorChanges;
    /**
     * Дата отсылки отчета
     */
    private LocalDate receiveDate;

    public Report(byte[] photo, String foodRation, String generalHealth, String behaviorChanges) {
        this.photo = photo;
        this.foodRation = foodRation;
        this.generalHealth = generalHealth;
        this.behaviorChanges = behaviorChanges;
        this.receiveDate = LocalDate.now();
    }
}
