package com.example.petshelter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *  Класс - кошка
 */
@Data
@Entity
@Table(name = "cat")
@NoArgsConstructor
public class Cat{

    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Имя животного
     */
    String name;
    /**
     * Возраст животного
     */
    int age;
    /**
     * Состояние здоровья животного
     */
    boolean healthStatus;
    /**
     * Статус о вакцинации животного
     */
    boolean vaccination;
    /**
     * Статус нахождения животного в приюте
     */
    private StatusAnimal statusAnimal;

    public Cat(String name, int age, boolean healthStatus, boolean vaccination, StatusAnimal statusAnimal) {
        this.name = name;
        this.age = age;
        this.healthStatus = healthStatus;
        this.vaccination = vaccination;
        this.statusAnimal = statusAnimal;
    }
}
