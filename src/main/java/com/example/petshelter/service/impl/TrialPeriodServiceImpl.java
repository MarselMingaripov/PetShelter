package com.example.petshelter.service.impl;

import com.example.petshelter.entity.TrialPeriod;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.TrialPeriodRepository;
import com.example.petshelter.service.TrialPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrialPeriodServiceImpl implements TrialPeriodService {

    private final TrialPeriodRepository trialPeriodRepository;

    @Override
    public TrialPeriod createTrialPeriod(TrialPeriod trialPeriod) {
        return trialPeriodRepository.save(trialPeriod);
    }

    @Override
    public TrialPeriod findById(Long id) {
        if (trialPeriodRepository.findById(id).isPresent()){
            return trialPeriodRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public TrialPeriod updateById(Long id, TrialPeriod trialPeriod) {
        if (trialPeriodRepository.findById(id).isPresent()){
            trialPeriod.setId(id);
            return trialPeriodRepository.save(trialPeriod);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public TrialPeriod deleteById(Long id) {
        if (trialPeriodRepository.findById(id).isPresent()){
            trialPeriodRepository.deleteById(id);
            return trialPeriodRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public List<TrialPeriod> findAll() {
        return trialPeriodRepository.findAll();
    }
}
