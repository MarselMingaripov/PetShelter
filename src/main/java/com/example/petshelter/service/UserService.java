package com.example.petshelter.service;

import com.example.petshelter.entity.User;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.UserRepository;

import java.util.List;
/**
 * Сервис для работы с БД потенциальных опекунов
 */
public interface UserService {
    /**
     * Внесение потенциальных опекунов в БД.
     * Используется метод репозитория {@link UserRepository#save(Object)}
     * @throws ValidationException при ошибке валидации полей создаваемого потенциального опекуна
     * @param user
     * @return
     */
    User createUser(User user);
    /**
     * Поиск потенциальных опекунов по его идентификатору в БД.
     * Используется метод репозитория {@link UserRepository#findById(Object)}
     * @throws NotFoundInBdException если потенциальный опекун не найден в БД
     * @param id
     * @return
     */
    User findById(Long id);
    /**
     * Поиск и обновление потенциального опекуна в БД по его идентификатору.
     * Используются методы репозитория {@link UserRepository#findById(Object)} и {@link UserRepository#save(Object)}
     * @throws NotFoundInBdException если потенциальный опекун не найдено в БД
     * @param id - ид обновляемой записи
     * @param user - на что обновляем
     * @return
     */
    User updateById(Long id, User user);
    /**
     * Поиск и удаление потенциального опекуна в БД по его идентификатору.
     * Используются методы репозитория {@link UserRepository#findById(Object)} и {@link UserRepository#deleteById(Object)}
     * @throws NotFoundInBdException если потенциальный опекун не найден в БД
     * @param id
     * @return
     */
    User deleteById(Long id);
    /**
     * Вывод полного списка потенциальных опекунов из БД.
     * Используется метод репозитория {@link UserRepository#findAll}
     * @return
     */
    List<User> findAll();

    User findByPhone(String phone);

    User findByTelegramID(Long id);
}
