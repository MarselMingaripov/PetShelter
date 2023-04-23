package com.example.petshelter.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  Класс - опекун собаки. Наследник класса {@link User}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dog_owner")
public class DogOwner{

    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String phoneNumber;

    /**
     * Список собак
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dog_owner_dog",
            joinColumns = @JoinColumn(name = "dog_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_id"))
    private List<Dog> dogs;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dog_owner_trial_period",
            joinColumns = @JoinColumn(name = "dog_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "trial_period_id"))
    private List<TrialPeriod> trialPeriodsInActiveStatus;
    /**
     * Завершенные периоды испытательного срока для опекуна животного
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dog_owner_completed_trial_period",
            joinColumns = @JoinColumn(name = "dog_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "trial_period_id"))
    private List<TrialPeriod> trialPeriodsCompleted;

    public DogOwner(String phoneNumber, List<Dog> dogs, List<TrialPeriod> trialPeriodsInActiveStatus, List<TrialPeriod> trialPeriodsCompleted) {
        this.phoneNumber = phoneNumber;
        this.dogs = dogs;
        this.trialPeriodsInActiveStatus = trialPeriodsInActiveStatus;
        this.trialPeriodsCompleted = trialPeriodsCompleted;
    }
}
