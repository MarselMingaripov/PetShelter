package com.example.petshelter.service.impl;

import com.example.petshelter.entity.*;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatOwnerRepository;
import com.example.petshelter.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatOwnerServiceImpl implements CatOwnerService {

    private final CatOwnerRepository catOwnerRepository;
    private final ValidationService validationService;
    private final UserService userService;
    private final TrialPeriodService trialPeriodService;
    private final CatService catService;
    private final MessageToVolunteerService messageToVolunteerService;

    @Override
    public CatOwner createCatOwner(CatOwner catOwner) {
        if(!validationService.validate(catOwner)) {
            throw new ValidationException(catOwner.toString());
        }
        return catOwnerRepository.save(catOwner);
    }

    @Override
    public CatOwner findById(Long id) {
        Optional<CatOwner> catOwner = catOwnerRepository.findById(id);
        if (catOwner.isPresent()) {
            return catOwner.get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public CatOwner updateById(Long id, CatOwner catOwner) {
        if (catOwnerRepository.findById(id).isPresent()) {
            catOwner.setId(id);
            return catOwnerRepository.save(catOwner);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public CatOwner deleteById(Long id) {
        CatOwner catOwner = findById(id);
        catOwnerRepository.delete(catOwner);
        return catOwner;
    }

    @Override
    public List<CatOwner> findAll() {
        return catOwnerRepository.findAll();
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return catOwnerRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public CatOwner findByPhoneNumber(String phoneNumber){
        Optional<CatOwner> catOwner = catOwnerRepository.findByPhoneNumber(phoneNumber);
        if (catOwner.isPresent()){
            return catOwner.get();
        } else {
            return null;
        }
    }

    @Override
    public CatOwner getAnimalToTrialPeriod(String phoneNumber, String animalName, long trialDays){
        if (!catOwnerRepository.existsByPhoneNumber(phoneNumber)){
            if (userService.findByPhone(phoneNumber) == null){
                User user = userService.createUser(new User(phoneNumber));
                messageToVolunteerService.createMessageToVolunteer(new MessageToVolunteer(
                        phoneNumber, phoneNumber + " получил кошку " + animalName + " на испытательный срок в " + trialDays + " дней"
                ));
                return getCatOwner(phoneNumber, animalName, trialDays);
            } else {
                User user = userService.findByPhone(phoneNumber);
                messageToVolunteerService.createMessageToVolunteer(new MessageToVolunteer(
                        phoneNumber, phoneNumber + "получил кошку " + animalName + " на испытательный срок в " + trialDays + " дней"
                ));
                return getCatOwner(phoneNumber, animalName, trialDays);
            }
        } else {
            CatOwner catOwner = findByPhoneNumber(phoneNumber);
            TrialPeriod currentTrialPeriod = new TrialPeriod(phoneNumber, animalName, LocalDate.now(), LocalDate.now().plusDays(trialDays),
                    new ArrayList<Report>(), TrialPeriodResult.IN_PROCESS);
            trialPeriodService.createTrialPeriod(currentTrialPeriod);
            Cat cat = catService.findByName(animalName);
            catService.changeStatusAnimal(animalName, StatusAnimal.TRIAL_PERIOD);
            catService.createCat(cat);
            catOwner.getTrialPeriodsInActiveStatus().add(currentTrialPeriod);
            catOwner.getCats().add(cat);
            messageToVolunteerService.createMessageToVolunteer(new MessageToVolunteer(
                    phoneNumber, phoneNumber + " получил кошку " + animalName + " на испытательный срок в " + trialDays + " дней"
            ));
            return createCatOwner(catOwner);
        }
    }

    private CatOwner getCatOwner(String phoneNumber, String animalName, long trialDays) {
        TrialPeriod currentTrialPeriod = new TrialPeriod(phoneNumber, animalName, LocalDate.now(), LocalDate.now().plusDays(trialDays),
                new ArrayList<Report>(), TrialPeriodResult.IN_PROCESS);
        trialPeriodService.createTrialPeriod(currentTrialPeriod);
        Cat cat = catService.findByName(animalName);
        catService.changeStatusAnimal(animalName, StatusAnimal.TRIAL_PERIOD);
        catService.createCat(cat);
        CatOwner catOwner = new CatOwner(phoneNumber, new ArrayList<Cat>(), new ArrayList<TrialPeriod>(), new ArrayList<TrialPeriod>());
        catOwner.getTrialPeriodsInActiveStatus().add(currentTrialPeriod);
        catOwner.getCats().add(cat);
        return createCatOwner(catOwner);
    }
}
