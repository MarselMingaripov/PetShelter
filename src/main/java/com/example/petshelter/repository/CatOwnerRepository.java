package com.example.petshelter.repository;

import com.example.petshelter.entity.CatOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatOwnerRepository extends JpaRepository<CatOwner, Long> {
}
