package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.Report;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.ReportRepository;
import com.example.petshelter.service.ReportService;
import com.example.petshelter.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ValidationService validationService;

    @Override
    public Report createReport(Report report) {
        if (!validationService.validate(report)) {
            throw new ValidationException(report.toString());
        }
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

    @Override
    public Report findBySenderAndDate(Long sender, LocalDate localDate){
        Optional<Report> report = reportRepository.findBySenderAndReceiveDate(sender, localDate);
        if (report.isPresent()){
            return report.get();
        } else {
            return null;
        }
    }
}
