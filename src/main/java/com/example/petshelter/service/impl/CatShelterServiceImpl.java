package com.example.petshelter.service.impl;

import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatShelterRepository;

import com.example.petshelter.service.CatOwnerService;
import com.example.petshelter.service.CatService;
import com.example.petshelter.service.CatShelterService;
import com.example.petshelter.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CatShelterServiceImpl implements CatShelterService {

    private final CatShelterRepository catShelterRepository;
    private final CatService catService;
    private final CatOwnerService catOwnerService;
    private final ValidationService validationService;

    @Override
    public CatShelter createCatShelter(CatShelter catShelter) {
        if(!validationService.validate(catShelter)) {
            throw new ValidationException(catShelter.toString());
        }
        return catShelterRepository.save(catShelter);
    }

    @Override
    public CatShelter findById(Long id) {
        if (catShelterRepository.findById(id).isPresent()) {
            return catShelterRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public CatShelter updateById(Long id, CatShelter catShelter) {
        if (catShelterRepository.findById(id).isPresent()) {
            catShelter.setId(id);
            return catShelterRepository.save(catShelter);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public CatShelter deleteById(Long id) {
        if (catShelterRepository.findById(id).isPresent()) {
            catShelterRepository.deleteById(id);
            return catShelterRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public List<CatShelter> findAll() {
        return catShelterRepository.findAll();
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
    public void addCatToShelter(String name) {
        if(!validationService.validateString(name)) {
            throw new ValidationException(catService.toString());
        }
        findById(1L).getCats().add(catService.findByName(name));
    }

    @Override
    public void addCatOwnerToShelter(String phoneNumber) {
        if(!validationService.validateString(phoneNumber)) {
            throw new ValidationException(catOwnerService.toString());
        }
        findById(1L).getCatOwners().add(catOwnerService.findByPhoneNumber(phoneNumber));
    }
}
