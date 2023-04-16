package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.DogOwner;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.DogOwnerRepository;
import com.example.petshelter.service.DogOwnerService;
import com.example.petshelter.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogOwnerServiceImpl implements DogOwnerService {

    private final DogOwnerRepository dogOwnerRepository;
    private final ValidationService validationService;

    @Override
    public DogOwner createDogOwner(DogOwner dogOwner) {
        if(!validationService.validate(dogOwner)) {
            throw new ValidationException(dogOwner.toString());
        }
        return dogOwnerRepository.save(dogOwner);
    }

    @Override
    public DogOwner findById(Long id) {
        Optional<DogOwner> dogOwner = dogOwnerRepository.findById(id);
        if (dogOwner.isPresent()) {
            return dogOwner.get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public DogOwner updateById(Long id, DogOwner dogOwner) {
        if (dogOwnerRepository.findById(id).isPresent()) {
            dogOwner.setId(id);
            return dogOwnerRepository.save(dogOwner);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public DogOwner deleteById(Long id) {
        DogOwner dogOwner = findById(id);
        dogOwnerRepository.delete(dogOwner);
        return dogOwner;
    }

    @Override
    public List<DogOwner> findAll() {
        return dogOwnerRepository.findAll();
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return dogOwnerRepository.existsByPhoneNumber(phoneNumber);
    }
    @Override
    public DogOwner findByPhoneNumber(String phoneNumber){
        if (existsByPhoneNumber(phoneNumber)){
            return dogOwnerRepository.findByPhoneNumber(phoneNumber).get();
        } else {
            throw new NotFoundInBdException("Not found!");
        }
    }
}
