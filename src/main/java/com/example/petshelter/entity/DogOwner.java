package com.example.petshelter.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  Класс - опекун собаки. Наследник класса {@link User}
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "dog_owner")
public class DogOwner extends User {

    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Список собак
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dog_owner_dog",
            joinColumns = @JoinColumn(name = "dog_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_id"))
    private List<Dog> dogs;

    public DogOwner(Long id, String phoneNumber, List<TrialPeriod> trialPeriodsInActiveStatus, List<TrialPeriod> trialPeriodsCompleted, List<Dog> dogs) {
        super(id, phoneNumber, trialPeriodsInActiveStatus, trialPeriodsCompleted);
        this.dogs = dogs;
    }
}
