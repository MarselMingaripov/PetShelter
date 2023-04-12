package com.example.petshelter.service;

import com.example.petshelter.entity.MessageToVolunteer;

import java.util.List;

public interface MessageToVolunteerService {
    /**
     * сохраняет в базу данных
     * @param messageToVolunteer
     * @return
     */
    MessageToVolunteer createMessageToVolunteer(MessageToVolunteer messageToVolunteer);
    /**
     * поиск по ид
     * @param id
     * @return
     */
    MessageToVolunteer findById(Long id);
    /**
     * обновить запись
     * @param id - ид обновляемой записи
     * @param messageToVolunteer - на что обновляем
     * @return
     */
    MessageToVolunteer updateById(Long id, MessageToVolunteer messageToVolunteer);
    /**
     * удаление по ид
     * @param id
     * @return
     */
    MessageToVolunteer deleteById(Long id);
    /**
     * список всех записей
     * @return
     */
    List<MessageToVolunteer> findAll();
}
