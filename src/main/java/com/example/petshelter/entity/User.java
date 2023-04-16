package com.example.petshelter.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Базовый класс - опекун животного
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Номер сотового телефона опекуна животного
     */
    private String phoneNumber;
    /**
     * Действующие периоды испытательного срока для опекуна животного
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_trial_period",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "trial_period_id"))
    private List<TrialPeriod> trialPeriodsInActiveStatus;
    /**
     * Завершенные периоды испытательного срока для опекуна животного
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_completed_trial_period",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "trial_period_id"))
    private List<TrialPeriod> trialPeriodsCompleted;

    public User(Long id) {
        this.id = id;
    }

    public User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User(String phoneNumber, List<TrialPeriod> trialPeriodsInActiveStatus, List<TrialPeriod> trialPeriodsCompleted) {
        this.phoneNumber = phoneNumber;
        this.trialPeriodsInActiveStatus = trialPeriodsInActiveStatus;
        this.trialPeriodsCompleted = trialPeriodsCompleted;
    }
}
