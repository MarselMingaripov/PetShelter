package com.example.petshelter.service.impl;

import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.CatOwnerRepository;
import com.example.petshelter.service.CatOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatOwnerServiceImpl implements CatOwnerService {

    private final CatOwnerRepository catOwnerRepository;

    @Override
    public CatOwner createCatOwner(CatOwner catOwner) {
        return catOwnerRepository.save(catOwner);
    }

    @Override
    public CatOwner findById(Long id) {
        if (catOwnerRepository.findById(id).isPresent()) {
            return catOwnerRepository.findById(id).get();
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
        if (catOwnerRepository.findById(id).isPresent()) {
            catOwnerRepository.deleteById(id);
            return catOwnerRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
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
