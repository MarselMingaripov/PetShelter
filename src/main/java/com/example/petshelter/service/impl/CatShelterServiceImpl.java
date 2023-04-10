package com.example.petshelter.service.impl;



import com.example.petshelter.entity.shelter.СatShelter;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.CatShelterRepository;

import com.example.petshelter.service.CatShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@RequiredArgsConstructor
public class CatShelterServiceImpl implements CatShelterService {

    private final CatShelterRepository catShelterRepository;

    @Override
    public СatShelter createCat(СatShelter catShelter) {
       return catShelterRepository.save(catShelter);
    }

    @Override
    public СatShelter findById(Long id) {
        if (catShelterRepository.findById(id).isPresent()) {
            return catShelterRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public СatShelter updateById(Long id, СatShelter catShelter) {
        if (catShelterRepository.findById(id).isPresent()){
            catShelter.setId(id);
            return catShelterRepository.save(catShelter);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public СatShelter deleteById(Long id) {
        if (catShelterRepository.findById(id).isPresent()){
            catShelterRepository.deleteById(id);
            return catShelterRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public List<СatShelter> findAll() {
        return catShelterRepository.findAll();
    }
}
