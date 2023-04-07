package com.example.petshelter.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class TrialPeriod {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Report> reports;
    private TrialPeriodResult result;

    public TrialPeriod(LocalDate startDate, LocalDate endDate, List<Report> reports, TrialPeriodResult result) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reports = reports;
        this.result = result;
    }
}
