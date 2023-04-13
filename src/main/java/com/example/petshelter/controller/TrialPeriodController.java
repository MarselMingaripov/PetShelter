package com.example.petshelter.controller;

import com.example.petshelter.entity.TrialPeriod;
import com.example.petshelter.service.TrialPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trial_period")
@RequiredArgsConstructor
public class TrialPeriodController {

    private final TrialPeriodService trialPeriodService;

    @PostMapping
    public ResponseEntity<TrialPeriod> createTrialPeriod(@RequestBody TrialPeriod trialPeriod){
        return ResponseEntity.ok().body(trialPeriodService.createTrialPeriod(trialPeriod));
    }

    @GetMapping("/id")
    public ResponseEntity<TrialPeriod> findTrialPeriod(@PathVariable Long id){
        return ResponseEntity.ok().body(trialPeriodService.findById(id));
    }

    @PostMapping("/id")
    public ResponseEntity<TrialPeriod> updateTrialPeriod(@PathVariable Long id, @RequestBody TrialPeriod trialPeriod){
        return ResponseEntity.ok().body(trialPeriodService.updateById(id, trialPeriod));
    }

    @DeleteMapping("/id")
    public ResponseEntity<TrialPeriod> deleteReport(@PathVariable Long id){
        return ResponseEntity.ok().body(trialPeriodService.deleteById(id));
    }

    @GetMapping
    public ResponseEntity<List<TrialPeriod>> findAll(){
        return ResponseEntity.ok().body(trialPeriodService.findAll());
    }
}
