package com.example.petshelter.entity.shelter;

import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.DogOwner;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Subclass of AnimalShelter
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "dog_shelter")
public class DogShelter extends AnimalShelter{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dogShelter_dog",
            joinColumns = @JoinColumn(name = "dog_shelter_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_id"))
    private List<Dog> dogs;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dogOwner_dog",
            joinColumns = @JoinColumn(name = "dog_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_id"))
    private List<DogOwner> dogOwners;

    /**
     * Constructor - creating a new object with certain values.
     * @param id
     * @param information
     * @param address
     * @param phoneNumber
     * @param workSchedule
     * @param securityContacts
     * @param safetyRecommendations
     * @param howTakeAnimal
     * @param dogs
     * @param dogOwners
     */
    public DogShelter(Long id,
                      String information,
                      String address,
                      String phoneNumber,
                      String workSchedule,
                      String securityContacts,
                      String safetyRecommendations,
                      String howTakeAnimal,
                      List<Dog> dogs,
                      List<DogOwner> dogOwners) {

        super(id, information, address, phoneNumber, workSchedule, securityContacts, safetyRecommendations, howTakeAnimal);

        this.dogs = dogs;
        this.dogOwners = dogOwners;
    }
}
