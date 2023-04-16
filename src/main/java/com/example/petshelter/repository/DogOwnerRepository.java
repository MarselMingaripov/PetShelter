package com.example.petshelter.repository;

import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.DogOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DogOwnerRepository extends JpaRepository<DogOwner, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<DogOwner> findByPhoneNumber(String number);
}
