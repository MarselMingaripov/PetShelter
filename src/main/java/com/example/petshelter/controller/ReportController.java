package com.example.petshelter.controller;

import com.example.petshelter.entity.Report;
import com.example.petshelter.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report){
        return ResponseEntity.ok().body(reportService.createReport(report));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> findReport(@PathVariable Long id){
        return ResponseEntity.ok().body(reportService.findById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report report){
        return ResponseEntity.ok().body(reportService.updateById(id, report));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Report> deleteReport(@PathVariable Long id){
        return ResponseEntity.ok().body(reportService.deleteById(id));
    }

    @GetMapping
    public ResponseEntity<List<Report>> findAll(){
        return ResponseEntity.ok().body(reportService.findAll());
    }
}
