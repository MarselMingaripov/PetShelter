package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatOwnerRepository;
import com.example.petshelter.service.CatOwnerService;
import com.example.petshelter.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatOwnerServiceImpl implements CatOwnerService {

    private final CatOwnerRepository catOwnerRepository;
    private final ValidationService validationService;

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
        if (existsByPhoneNumber(phoneNumber)){
            return catOwnerRepository.findByPhoneNumber(phoneNumber).get();
        } else {
            throw new NotFoundInBdException("Not found!");
        }
    }
}
