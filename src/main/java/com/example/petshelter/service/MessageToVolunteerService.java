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
    MessageToVolunteer findById(Long id);
    MessageToVolunteer updateById(Long id, MessageToVolunteer messageToVolunteer);
    MessageToVolunteer deleteById(Long id);
    List<MessageToVolunteer> findAll();
}
