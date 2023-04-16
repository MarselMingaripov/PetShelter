package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.TrialPeriod;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.TrialPeriodRepository;
import com.example.petshelter.service.TrialPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<TrialPeriod> trialPeriod = trialPeriodRepository.findById(id);
        if (trialPeriod.isPresent()) {
            return trialPeriod.get();
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
        TrialPeriod trialPeriod = findById(id);
        trialPeriodRepository.delete(trialPeriod);
        return trialPeriod;
    }

    @Override
    public List<TrialPeriod> findAll() {
        return trialPeriodRepository.findAll();
    }
}
