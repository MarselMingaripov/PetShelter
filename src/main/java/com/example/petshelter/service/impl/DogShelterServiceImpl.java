package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.shelter.DogShelter;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.DogShelterRepository;
import com.example.petshelter.service.DogOwnerService;
import com.example.petshelter.service.DogService;
import com.example.petshelter.service.DogShelterService;
import com.example.petshelter.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogShelterServiceImpl implements DogShelterService {

    private final DogShelterRepository dogShelterRepository;
    private final DogService dogService;
    private final DogOwnerService dogOwnerService;
    private final ValidationService validationService;

    @Override
    public DogShelter createDogShelter(DogShelter dogShelter) {
        if(!validationService.validate(dogShelter)) {
            throw new ValidationException(dogShelter.toString());
        }
        return dogShelterRepository.save(dogShelter);
    }

    @Override
    public DogShelter findById(Long id) {
        Optional<DogShelter> dogShelter = dogShelterRepository.findById(id);
        if (dogShelter.isPresent()) {
            return dogShelter.get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public DogShelter updateById(Long id, DogShelter dogShelter) {
        if (dogShelterRepository.findById(id).isPresent()) {
            dogShelter.setId(id);
            return dogShelterRepository.save(dogShelter);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public DogShelter deleteById(Long id) {
        DogShelter dogShelter = findById(id);
        dogShelterRepository.delete(dogShelter);
        return dogShelter;
    }

    @Override
    public List<DogShelter> findAll() {
        return dogShelterRepository.findAll();
    }

    @Override
    public String returnInformation() {
        return findById(1L).getInformation();
    }

    @Override
    public String returnAddressAndWorkSchedule() {
        return findById(1L).getAddress() + " " + findById(1L).getWorkSchedule();
    }

    @Override
    public String returnSecurityContacts() {
        return findById(1L).getSecurityContacts();
    }

    @Override
    public String returnSafetyRecommendations() {
        return findById(1L).getSafetyRecommendations();
    }

    @Override
    public void addDogToShelter(String name) {
        if(!validationService.validateString(name)) {
            throw new ValidationException(dogService.toString());
        }
        findById(1L).getDogs().add(dogService.findByName(name));
    }

    @Override
    public void addDogOwnerToShelter(String phoneNumber) {
        if(!validationService.validateString(phoneNumber)) {
            throw new ValidationException(dogOwnerService.toString());
        }
        findById(1L).getDogOwners().add(dogOwnerService.findByPhoneNumber(phoneNumber));
//        dogOwnerService.existsByPhoneNumber(phoneNumber);
    }
}
