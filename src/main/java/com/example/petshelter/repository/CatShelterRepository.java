package com.example.petshelter.repository;

import com.example.petshelter.entity.shelter.CatShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatShelterRepository extends JpaRepository<CatShelter, Long> {
}
