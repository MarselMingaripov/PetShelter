package com.example.petshelter.repository;

import com.example.petshelter.entity.shelter.СatShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatShelterRepository extends JpaRepository<СatShelter, Long> {
}
