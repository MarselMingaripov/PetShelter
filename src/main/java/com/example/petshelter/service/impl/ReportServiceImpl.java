package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Report;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.ReportRepository;
import com.example.petshelter.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (reportRepository.findById(id).isPresent()) {
            return reportRepository.findById(id).get();
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
        if (reportRepository.findById(id).isPresent()) {
            reportRepository.deleteById(id);
            return reportRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }
    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }
}
