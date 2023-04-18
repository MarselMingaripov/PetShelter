package com.example.petshelter.service;

import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.MessageToVolunteerRepository;

import java.util.List;
/**
 * Сервис для работы с БД сообщений для волонтеров
 */
public interface MessageToVolunteerService {
    /**
     * Внесение сообщений для волонтеров в БД.
     * Используется метод репозитория {@link MessageToVolunteerRepository#save(Object)}
     * @throws ValidationException при ошибке валидации полей создаваемого сообщения для волонтеров
     * @param messageToVolunteer
     * @return
     */
    MessageToVolunteer createMessageToVolunteer(MessageToVolunteer messageToVolunteer);
    /**
     * Поиск сообщения для волонтеров по его идентификатору в БД.
     * Используется метод репозитория {@link MessageToVolunteerRepository#findById(Object)}
     * @throws NotFoundInBdException если сообщение для волонтеров не найдено в БД
     * @param id
     * @return
     */
    MessageToVolunteer findById(Long id);
    /**
     * Поиск и обновление сообщения для волонтеров в БД по его идентификатору.
     * Используются методы репозитория {@link MessageToVolunteerRepository#findById(Object)} и {@link MessageToVolunteerRepository#save(Object)}
     * @throws NotFoundInBdException если сообщение для волонтеров не найдено в БД
     * @param id - ид обновляемой записи
     * @param messageToVolunteer - на что обновляем
     * @return
     */
    MessageToVolunteer updateById(Long id, MessageToVolunteer messageToVolunteer);
    /**
     * Поиск и удаление сообщений для волонтеров в БД по его идентификатору.
     * Используются методы репозитория {@link MessageToVolunteerRepository#findById(Object)} и {@link MessageToVolunteerRepository#deleteById(Object)}
     * @throws NotFoundInBdException если сообщение для волонтеров не найдено в БД
     * @param id
     * @return
     */
    MessageToVolunteer deleteById(Long id);
    /**
     * Вывод полного списка сообщений для волонтеров из БД.
     * Используется метод репозитория {@link MessageToVolunteerRepository#findAll}
     * @return
     */
    List<MessageToVolunteer> findAll();

    MessageToVolunteer createMessageFromText(String text);
}
