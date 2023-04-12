package com.example.petshelter.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "trial_period")
public class TrialPeriod {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "trial_period_report",
            joinColumns = @JoinColumn(name = "trial_period_id"),
            inverseJoinColumns = @JoinColumn(name = "report_id"))
    private List<Report> reports;
    private TrialPeriodResult result;

    public TrialPeriod(LocalDate startDate, LocalDate endDate, List<Report> reports, TrialPeriodResult result) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reports = reports;
        this.result = result;
    }
}
