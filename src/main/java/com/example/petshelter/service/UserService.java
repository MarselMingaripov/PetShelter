package com.example.petshelter.service;

import com.example.petshelter.entity.TrialPeriod;
import com.example.petshelter.entity.User;

import java.util.List;

public interface UserService {

    /**
     * сохраняет в базу данных
     * @param user
     * @return
     */
    User createUser(User user);

    /**
     * поиск по ид
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * обновить запись
     * @param id - ид обновляемой записи
     * @param user - на что обновляем
     * @return
     */
    User updateById(Long id, User user);

    /**
     * удаление по ид
     * @param id
     * @return
     */
    User deleteById(Long id);

    /**
     * список всех записей
     * @return
     */
    List<User> findAll();

    User findByPhone(String phone);
}
