package com.example.petshelter.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Класс - собака
 */
@Data
@Entity
@Table(name = "dog")
@NoArgsConstructor
@AllArgsConstructor
public class Dog{

    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Имя животного
     */
    private String name;
    /**
     * Возраст животного
     */
    private int age;
    /**
     * Состояние здоровья животного
     */
    private boolean healthStatus;
    /**
     * Статус о вакцинации животного
     */
    private boolean vaccination;
    /**
     * Статус нахождения животного в приюте
     */
    @Enumerated(value = EnumType.STRING)
    private StatusAnimal statusAnimal;
}
