package com.example.petshelter.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс - испытательный срок для опекуна животного
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "trial_period")
public class TrialPeriod {
    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Дата начала испытательного срока
     */
    private LocalDate startDate;
    /**
     * Дата окончания испытательного срока
     */
    private LocalDate endDate;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "trial_period_report",
            joinColumns = @JoinColumn(name = "trial_period_id"),
            inverseJoinColumns = @JoinColumn(name = "report_id"))
    /**
     * Отчеты опекунов животного
     */
    private List<Report> reports;
    /**
     * Результат по прохождению испытательного срока опекуном животного
     */
    private TrialPeriodResult result;

    public TrialPeriod(LocalDate startDate, LocalDate endDate, List<Report> reports, TrialPeriodResult result) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reports = reports;
        this.result = result;
    }
}
