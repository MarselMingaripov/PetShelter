package com.example.petshelter.entity.shelter;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Класс приюта для кошек - CatShelter. Наследник класса {@link AnimalShelter}
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "cat_shelter")
public class CatShelter extends AnimalShelter {

    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Список кошек
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "catShelter_cat",
            joinColumns = @JoinColumn(name = "cat_shelter_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_id"))
    private List<Cat> cats;

    /**
     * Список опекунов кошек
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "catOwner_cat",
            joinColumns = @JoinColumn(name = "cat_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_id"))
    private List<CatOwner> catOwners;

    /**
     * Список рекомендаций от приюта для кошек
     */
    @Transient
    private Map<CatShelterConsult, String> catConsult;

    public CatShelter(Long id,
                      String information,
                      String address,
                      String phoneNumber,
                      String workSchedule,
                      String securityContacts,
                      String safetyRecommendations) {
        super(id, information, address, phoneNumber, workSchedule, securityContacts, safetyRecommendations);
    }

    public CatShelter(Long id,
                      String information,
                      String address,
                      String phoneNumber,
                      String workSchedule,
                      String securityContacts,
                      String safetyRecommendations,
                      List<Cat> cats,
                      List<CatOwner> catOwners,
                      Map<CatShelterConsult, String> catConsult) {

        super(id, information, address, phoneNumber, workSchedule, securityContacts, safetyRecommendations);

        this.cats = cats;
        this.catOwners = catOwners;
        this.catConsult = catConsult;
    }
}
