package com.example.petshelter.entity.shelter;

import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.DogOwner;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Subclass of AnimalShelter
 */

@Data
@NoArgsConstructor
public class DogShelter extends AnimalShelter{

    /** "Dogs" field */
    private List<Dog> dogs;

    /** "Dog Owner" field */
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
