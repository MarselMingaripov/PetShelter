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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatShelterServiceImpl implements CatShelterService {

    private final CatShelterRepository catShelterRepository;
    private final CatService catService;
    private final CatOwnerService catOwnerService;
    private final ValidationService validationService;

    @Override
    public CatShelter createCatShelter(CatShelter catShelter) {
        /*if(!validationService.validate(catShelter)) {
            throw new ValidationException(catShelter.toString());
        }*/
        return catShelterRepository.save(catShelter);
    }

    @Override
    public CatShelter findById(Long id) {
        Optional<CatShelter> catShelter = catShelterRepository.findById(id);
        if (catShelter.isPresent()) {
            return catShelter.get();
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
        CatShelter catShelter = findById(id);
        catShelterRepository.delete(catShelter);
        return catShelter;
    }

    @Override
    public List<CatShelter> findAll() {
        return catShelterRepository.findAll();
    }

    @Override
    public String returnInformation(Long id) {
        return findById(id).getInformation();
    }

    @Override
    public String returnPhone(Long id) {
        return findById(id).getPhoneNumber();
    }

    @Override
    public String returnAddressAndWorkSchedule(Long id) {
        return findById(id).getAddress() + " " + findById(id).getWorkSchedule();
    }

    @Override
    public String returnSecurityContacts(Long id) {
        return findById(id).getSecurityContacts();
    }

    @Override
    public String returnSafetyRecommendations(Long id) {
        return findById(id).getSafetyRecommendations();
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

    @Override
    public String returnDating(Long id){
        return findById(id).getDating();
    }

    @Override
    public String returnDocuments(Long id){
        return findById(id).getDocuments();
    }

    @Override
    public String returnTransportation(Long id){
        return findById(id).getTransportation();
    }

    @Override
    public String returnArrangementKitten(Long id){
        return findById(id).getArrangementKitten();
    }

    @Override
    public String returnArrangementCat(Long id){
        return findById(id).getArrangementCat();
    }

    @Override
    public String returnArrangementDisabled(Long id){
        return findById(id).getArrangementDisabled();
    }

    @Override
    public void addDating(Long id, String data){
        findById(id).setDating(data);
    }

    @Override
    public void addDocuments(Long id, String data){
        findById(id).setDocuments(data);
    }

    @Override
    public void addTransportation(Long id, String data){
        findById(id).setTransportation(data);
    }

    @Override
    public void addArrangementKitten(Long id, String data){
        findById(id).setArrangementKitten(data);
    }

    @Override
    public void addArrangementCat(Long id, String data){
        findById(id).setArrangementCat(data);
    }

    @Override
    public void addArrangementDisabled(Long id, String data){
        findById(id).setArrangementDisabled(data);
    }
}
