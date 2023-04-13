package com.example.petshelter.service.impl;

import com.example.petshelter.entity.DogOwner;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.DogOwnerRepository;
import com.example.petshelter.service.DogOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogOwnerServiceImpl implements DogOwnerService {

    private final DogOwnerRepository dogOwnerRepository;

    @Override
    public DogOwner createDogOwner(DogOwner dogOwner) {
        return dogOwnerRepository.save(dogOwner);
    }

    @Override
    public DogOwner findById(Long id) {
        if (dogOwnerRepository.findById(id).isPresent()) {
            return dogOwnerRepository.findById(id).get();
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
        if (dogOwnerRepository.findById(id).isPresent()) {
            dogOwnerRepository.deleteById(id);
            return dogOwnerRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
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
