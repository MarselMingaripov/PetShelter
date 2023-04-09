package com.example.petshelter.entity.shelter;

import com.example.petshelter.entity.CatOwner;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Subclass of the base class AnimalShelter
 */

@Data
@NoArgsConstructor
public class СatsShelter extends AnimalShelter{

    /** "Cats" field */
    private List<Cat> cats;

    /** "Cat Owner" field */
    private List<CatOwner> catOwners;

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
     * @param cats
     * @param catOwners
     */
    public СatsShelter(Long id,
                       String information,
                       String address,
                       String phoneNumber,
                       String workSchedule,
                       String securityContacts,
                       String safetyRecommendations,
                       String howTakeAnimal,
                       List<Cat> cats,
                       List<CatOwner> catOwners) {

        super(id, information, address, phoneNumber, workSchedule, securityContacts, safetyRecommendations, howTakeAnimal);

        this.cats = cats;
        this.catOwners = catOwners;
    }
}
