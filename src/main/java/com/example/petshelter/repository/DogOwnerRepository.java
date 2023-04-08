package com.example.petshelter.repository;

import com.example.petshelter.entity.DogOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogOwnerRepository extends JpaRepository<DogOwner, Long> {
}
