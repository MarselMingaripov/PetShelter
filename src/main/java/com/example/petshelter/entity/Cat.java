package com.example.petshelter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cat")
@NoArgsConstructor
public class Cat{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    String name;
    int age;
    boolean healthStatus;
    boolean vaccination;
    private StatusAnimal statusAnimal;

    public Cat(String name, int age, boolean healthStatus, boolean vaccination, StatusAnimal statusAnimal) {
        this.name = name;
        this.age = age;
        this.healthStatus = healthStatus;
        this.vaccination = vaccination;
        this.statusAnimal = statusAnimal;
    }
}
