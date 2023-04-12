package com.example.petshelter.repository;

import com.example.petshelter.entity.CatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatOwnerRepository extends JpaRepository<CatOwner, Long> {

    //Boolean existsByPhoneNumber(String phoneNumber);
}
