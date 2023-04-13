package com.example.petshelter.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String phoneNumber;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_trial_period",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "trial_period_id"))
    private List<TrialPeriod> trialPeriodsInActiveStatus;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_completed_trial_period",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "trial_period_id"))
    private List<TrialPeriod> trialPeriodsCompleted;

    public User(Long id) {
        this.id = id;
    }
}
