package com.example.petshelter.repository;

import com.example.petshelter.entity.TrialPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrialPeriodRepository extends JpaRepository<TrialPeriod, Long> {
}
