package com.example.petshelter.repository;

import com.example.petshelter.entity.shelter.DogShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogShelterRepository extends JpaRepository<DogShelter, Long> {
}
