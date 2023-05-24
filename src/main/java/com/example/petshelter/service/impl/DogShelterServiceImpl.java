package com.example.petshelter.service.impl;

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
    public String returnInformation(Long id) {
        return findById(id).getInformation();
    }

    @Override
    public String returnPhone(Long id){
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
    public String returnArrangementPuppy(Long id){
        return findById(id).getArrangementPuppy();
    }

    @Override
    public String returnArrangementDog(Long id){
        return findById(id).getArrangementDog();
    }

    @Override
    public String returnArrangementDisabled(Long id){
        return findById(id).getArrangementDisabled();
    }

    @Override
    public String returnDogHandlerRecommendations(Long id){
        return findById(id).getDogHandlerRecommendations();
    }

    @Override
    public String returnRecommendedDogHandlers(Long id){
        return findById(id).getRecommendedDogHandlers();
    }

    @Override
    public String returnCancelDogTaker(Long id){
        return findById(id).getCancelDogTaker();
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
    public void addArrangementPuppy(Long id, String data){
        findById(id).setArrangementPuppy(data);
    }

    @Override
    public void addArrangementDog(Long id, String data){
        findById(id).setArrangementDog(data);
    }

    @Override
    public void addArrangementDisabled(Long id, String data){
        findById(id).setArrangementDisabled(data);
    }

    @Override
    public void addDogHandlerRecommendations(Long id, String data){
        findById(id).setDogHandlerRecommendations(data);
    }

    @Override
    public void addRecommendedDogHandlers(Long id, String data){
        findById(id).setRecommendedDogHandlers(data);
    }

    @Override
    public void addCancelDogTaker(Long id, String data){
        findById(id).setCancelDogTaker(data);
    }
}
