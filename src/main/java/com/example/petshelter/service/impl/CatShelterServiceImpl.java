package com.example.petshelter.service.impl;


import com.example.petshelter.entity.shelter.СatShelter;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.CatShelterRepository;

import com.example.petshelter.service.CatOwnerService;
import com.example.petshelter.service.CatService;
import com.example.petshelter.service.CatShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CatShelterServiceImpl implements CatShelterService {

    private final CatShelterRepository catShelterRepository;
    private final CatService catService;
    private final CatOwnerService catOwnerService;

    @Override
    public СatShelter createСatShelter(СatShelter catShelter) {
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
        if (catShelterRepository.findById(id).isPresent()) {
            catShelter.setId(id);
            return catShelterRepository.save(catShelter);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public СatShelter deleteById(Long id) {
        if (catShelterRepository.findById(id).isPresent()) {
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
        findById(1L).getCats().add(catService.findByName(name));
    }

    @Override
    public void addCatOwnerToShelter(String phoneNumber) {
        findById(1L).getCatOwners().add(catOwnerService.findByPhoneNumber(phoneNumber));
    }
}
