package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;

import java.util.List;

public interface CatService {
    /**
     * сохраняет в базу данных
     * @param cat
     * @return
     */
    Cat createCat(Cat cat);
    Cat findById(Long id);
    Cat updateById(Long id, Cat cat);
    Cat deleteById(Long id);
    List<Cat> findAll();
}
