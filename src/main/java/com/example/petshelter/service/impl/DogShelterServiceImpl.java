package com.example.petshelter.service.impl;

import com.example.petshelter.entity.shelter.DogShelter;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.DogShelterRepository;
import com.example.petshelter.service.DogService;
import com.example.petshelter.service.DogShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DogShelterServiceImpl implements DogShelterService {

    private final DogShelterRepository dogShelterRepository;
    private final DogService dogService;

    @Override
    public DogShelter createCat(DogShelter dogShelter) {
        return dogShelterRepository.save(dogShelter);
    }

    @Override
    public DogShelter findById(Long id) {
        if (dogShelterRepository.findById(id).isPresent()) {
            return dogShelterRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public DogShelter updateById(Long id, DogShelter dogShelter) {
        if (dogShelterRepository.findById(id).isPresent()){
            dogShelter.setId(id);
            return dogShelterRepository.save(dogShelter);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public DogShelter deleteById(Long id) {
        if (dogShelterRepository.findById(id).isPresent()){
            dogShelterRepository.deleteById(id);
            return dogShelterRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public List<DogShelter> findAll() {
        return dogShelterRepository.findAll();
    }

    @Override
    public void addDogToShelter(String name){
        findById(1L).getDogs().add(dogService.findByName(name));
    }
}
