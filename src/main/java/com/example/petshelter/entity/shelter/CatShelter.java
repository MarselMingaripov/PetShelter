package com.example.petshelter.entity.shelter;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Subclass of the base class AnimalShelter
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "cat_shelter")
public class CatShelter extends AnimalShelter{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "catShelter_cat",
            joinColumns = @JoinColumn(name = "cat_shelter_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_id"))
    private List<Cat> cats;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "catOwner_cat",
            joinColumns = @JoinColumn(name = "cat_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_id"))
    private List<CatOwner> catOwners;
    @Transient
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
    public CatShelter(Long id,
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
    public CatShelter(Long id,
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
