package com.example.petshelter.entity.shelter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Base class of Animal Shelter
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AnimalShelter {
    /** "ID field" */
    private Long id;

    /** "Information" field */
    private String information;

    /** "Address" field */
    private String address;

    /** "Phone Number" field */
    private String phoneNumber;

    /** "Work Schedule" field */
    private String workSchedule;

    /** "Security Contacts" field*/
    private String securityContacts;

    /** "Safety Recommendation" field*/
    private String safetyRecommendations;

    public AnimalShelter(Long id) {
        this.id = id;
    }

}
