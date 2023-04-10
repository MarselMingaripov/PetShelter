package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Dog;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.DogRepository;
import com.example.petshelter.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    @Override
    public Dog createCat(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public Dog findById(Long id) {
        if (dogRepository.findById(id).isPresent()) {
            return dogRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public Dog updateById(Long id, Dog dog) {
        if (dogRepository.findById(id).isPresent()){
            dog.setId(id);
            return dogRepository.save(dog);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public Dog deleteById(Long id) {
        if (dogRepository.findById(id).isPresent()){
            dogRepository.deleteById(id);
            return dogRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public List<Dog> findAll() {
        return dogRepository.findAll();
    }
}
