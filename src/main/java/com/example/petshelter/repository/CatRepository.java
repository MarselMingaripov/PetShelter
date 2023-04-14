package com.example.petshelter.repository;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    Optional<Cat> findByName(String name);
    Boolean existsByName(String name);
}
