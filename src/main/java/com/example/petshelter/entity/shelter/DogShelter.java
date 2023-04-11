package com.example.petshelter.entity.shelter;

import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.DogOwner;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

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

    private Map<DogShelterConsult,String> dogConsult;

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
     * @param dogConsult
     */
    public DogShelter(Long id,
                      String information,
                      String address,
                      String phoneNumber,
                      String workSchedule,
                      String securityContacts,
                      String safetyRecommendations,
                      String howTakeAnimal,            // Возможно это лишнее поле ?
                      List<Dog> dogs,
                      List<DogOwner> dogOwners,
                      Map<DogShelterConsult,String> dogConsult) {

        super(id, information, address, phoneNumber, workSchedule, securityContacts, safetyRecommendations, howTakeAnimal);

        this.dogs = dogs;
        this.dogOwners = dogOwners;
        this.dogConsult = dogConsult;
    }
}
