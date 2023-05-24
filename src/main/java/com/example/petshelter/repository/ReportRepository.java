package com.example.petshelter.repository;

import com.example.petshelter.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findBySenderAndReceiveDate(Long sender, LocalDate localDate);
}
