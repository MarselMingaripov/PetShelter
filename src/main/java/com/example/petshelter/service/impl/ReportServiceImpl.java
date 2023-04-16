package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.Report;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.ReportRepository;
import com.example.petshelter.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public Report createReport(Report report) {
        return reportRepository.save(report);
    }
    @Override
    public Report findById(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isPresent()) {
            return report.get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }
    @Override
    public Report updateById(Long id, Report report) {
        if (reportRepository.findById(id).isPresent()) {
            report.setId(id);
            return reportRepository.save(report);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }
    @Override
    public Report deleteById(Long id) {
        Report report = findById(id);
        reportRepository.delete(report);
        return report;
    }
    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }
}
