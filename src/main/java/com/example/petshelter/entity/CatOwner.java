package com.example.petshelter.entity;

import javax.persistence.*;

import com.example.petshelter.entity.shelter.AnimalShelter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  Класс - опекун кошки. Наследник класса {@link User}
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "cat_owner")
public class CatOwner extends User{

    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Список кошек
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cat_owner_cat",
            joinColumns = @JoinColumn(name = "cat_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_id"))
    private List<Cat> cats;

    public CatOwner(Long id, String phoneNumber, List<TrialPeriod> trialPeriodsInActiveStatus, List<TrialPeriod> trialPeriodsCompleted, List<Cat> cats) {
        super(id, phoneNumber, trialPeriodsInActiveStatus, trialPeriodsCompleted);
        this.cats = cats;
    }
}
