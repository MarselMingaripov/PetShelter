package com.example.petshelter.entity.shelter;

import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.DogOwner;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Класс приюта для собак - DogShelter. Наследник класса {@link AnimalShelter}
 */


@Data
@NoArgsConstructor
@Entity
@Table(name = "dog_shelter")
public class DogShelter extends AnimalShelter {

    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String information;

    /**
     * Адрес питомника
     */
    private String address;

    /**
     * Номер телефона питомника
     */
    private String phoneNumber;

    /**
     * Режим работы питомника
     */
    private String workSchedule;

    /**
     * Контакты охраны питомника
     */
    private String securityContacts;

    /**
     * Рекомендации по технике безопасности на территории питомника
     */
    private String safetyRecommendations;

    /**
     * Список собак
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dogShelter_dog",
            joinColumns = @JoinColumn(name = "dog_shelter_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_id"))
    private List<Dog> dogs;

    /**
     * Список опекунов собак
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dogOwner_dog",
            joinColumns = @JoinColumn(name = "dog_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_id"))
    private List<DogOwner> dogOwners;

    /**
     * Список рекомендаций от приюта для собак
     */

    private String dating;
    private String documents;
    private String transportation;
    private String arrangementPuppy;
    private String arrangementDog;
    private String arrangementDisabled;
    private String dogHandlerRecommendations;
    private String recommendedDogHandlers;
    private String cancelDogTaker;

    public DogShelter(Long id,
                      String information,
                      String address,
                      String phoneNumber,
                      String workSchedule,
                      String securityContacts,
                      String safetyRecommendations) {
        super(id, information, address, phoneNumber, workSchedule, securityContacts, safetyRecommendations);
    }

    public DogShelter(Long id,
                      String information,
                      String address,
                      String phoneNumber,
                      String workSchedule,
                      String securityContacts,
                      String safetyRecommendations,
                      List<Dog> dogs,
                      List<DogOwner> dogOwners) {

        super(id, information, address, phoneNumber, workSchedule, securityContacts, safetyRecommendations);

        this.dogs = dogs;
        this.dogOwners = dogOwners;
    }
}
