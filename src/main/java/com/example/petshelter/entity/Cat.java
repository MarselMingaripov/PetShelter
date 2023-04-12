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
@Table(name = "cat")
public class Cat extends Animal {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
}
