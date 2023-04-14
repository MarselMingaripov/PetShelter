package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatRepository;
import com.example.petshelter.service.CatService;
import com.example.petshelter.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервисы для работы с БД кошек
 */
@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final ValidationService validationService;

    /**
     * Внесение данных о новом животном в БД.
     * Используется метод репозитория {@link CatRepository#save(Object)}
     * @throws ValidationException при ошибке валидации полей создаваемого животного
     * @param cat
     * @return
     */
    @Override
    public Cat createCat(Cat cat) {
        if(!validationService.validate(cat)) {
            throw new ValidationException(cat.toString());
        }
        return catRepository.save(cat);
    }

    /**
     * Поиск животного по его идентификатору в БД.
     * Используется метод репозитория {@link CatRepository#findById(Object)}
     * @throws NotFoundInBdException если животное не найдено в БД
     * @param id
     * @return
     */
    @Override
    public Cat findById(Long id) {
        if (catRepository.findById(id).isPresent()) {
            return catRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    /**
     * Поиск и обновление данных о животном в БД по его идентификатору.
     * Используются методы репозитория {@link CatRepository#findById(Object)} и {@link CatRepository#save(Object)}
     * @throws NotFoundInBdException если животное не найдено в БД
     * @param id - ид обновляемой записи
     * @param cat - на что обновляем
     * @return
     */
    @Override
    public Cat updateById(Long id, Cat cat) {
        if (catRepository.findById(id).isPresent()){
            cat.setId(id);
            return catRepository.save(cat);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    /**
     * Поиск и удаление данных о животном в БД по его идентификатору.
     * Используются методы репозитория {@link CatRepository#findById(Object)} и {@link CatRepository#deleteById(Object)}
     * @throws NotFoundInBdException если животное не найдено в БД
     * @param id
     * @return
     */

    @Override
    public Cat deleteById(Long id) {
        if (catRepository.findById(id).isPresent()){
            Cat cat = findById(id);
            catRepository.deleteById(id);
            return cat;
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    /**
     * Вывод полного списка животных из БД.
     * Используется метод репозитория {@link CatRepository#findAll}
     * @return
     */
    @Override
    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    /**
     * Поиск в БД животного по его имени.
     * Используется метод репозитория {@link CatRepository#findByName(String)}
     * @param name
     * @return
     */
    @Override
    public Cat findByName(String name) {
        return catRepository.findByName(name).get();
    }

}
