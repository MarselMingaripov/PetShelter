package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatRepository;
import com.example.petshelter.service.CatService;
import com.example.petshelter.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final ValidationService validationService;


    @Override
    public Cat createCat(Cat cat) {
        if(!validationService.validate(cat)) {
            throw new ValidationException(cat.toString());
        }
        return catRepository.save(cat);
    }

    @Override
    public Cat findById(Long id) {
        if (catRepository.findById(id).isPresent()) {
            return catRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public Cat updateById(Long id, Cat cat) {
        if (catRepository.findById(id).isPresent()){
            cat.setId(id);
            return catRepository.save(cat);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public Cat deleteById(Long id) {
        if (catRepository.findById(id).isPresent()){
            Cat cat = findById(id);
            catRepository.deleteById(id);
            return cat;
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    @Override
    public Cat findByName(String name) {
        return catRepository.findByName(name).get();
    }

}
