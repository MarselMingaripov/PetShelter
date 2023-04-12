package com.example.petshelter.entity.shelter;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.shelter.AnimalShelter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Subclass of the base class AnimalShelter
 */

@Data
@NoArgsConstructor
public class СatShelter extends AnimalShelter {

    /** "Cats" field */
    private List<Cat> cats;

    /** "Cat Owner" field */
    private List<CatOwner> catOwners;
    private Map<CatShelterConsult,String> catConsult;

    /**
     * Constructor - creating a new object with certain values.
     * @param id
     * @param information
     * @param address
     * @param phoneNumber
     * @param workSchedule
     * @param securityContacts
     * @param safetyRecommendations
     */
    public СatShelter(Long id,
                      String information,
                      String address,
                      String phoneNumber,
                      String workSchedule,
                      String securityContacts,
                      String safetyRecommendations) {
        super(id, information, address, phoneNumber, workSchedule, securityContacts, safetyRecommendations);
    }
    /**
     * Constructor - creating a new object with certain values.
     * @param id
     * @param information
     * @param address
     * @param phoneNumber
     * @param workSchedule
     * @param securityContacts
     * @param safetyRecommendations
     * @param cats
     * @param catOwners
     * @param catConsult
     */
    public СatShelter(Long id,
                      String information,
                      String address,
                      String phoneNumber,
                      String workSchedule,
                      String securityContacts,
                      String safetyRecommendations,
                      List<Cat> cats,
                      List<CatOwner> catOwners,
                      Map<CatShelterConsult,String> catConsult) {

        super(id, information, address, phoneNumber, workSchedule, securityContacts, safetyRecommendations);

        this.cats = cats;
        this.catOwners = catOwners;
        this.catConsult = catConsult;
    }
}
