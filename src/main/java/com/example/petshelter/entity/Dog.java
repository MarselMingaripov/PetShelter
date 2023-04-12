package com.example.petshelter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "dog")
@NoArgsConstructor
@AllArgsConstructor
public class Dog{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    String name;
    int age;
    boolean healthStatus;
    boolean vaccination;
    private StatusAnimal statusAnimal;
}
