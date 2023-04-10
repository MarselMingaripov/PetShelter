package com.example.petshelter.service.impl;

import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.MessageToVolunteerRepository;
import com.example.petshelter.service.MessageToVolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageToVolunteerServiceImpl implements MessageToVolunteerService {

    private final MessageToVolunteerRepository messageToVolunteerRepository;

    @Override
    public MessageToVolunteer createMessageToVolunteer(MessageToVolunteer messageToVolunteer) {
        return messageToVolunteerRepository.save(messageToVolunteer);
    }

    @Override
    public MessageToVolunteer findById(Long id) {
        if (messageToVolunteerRepository.findById(id).isPresent()) {
            return messageToVolunteerRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public MessageToVolunteer updateById(Long id, MessageToVolunteer messageToVolunteer) {
        if (messageToVolunteerRepository.findById(id).isPresent()) {
            messageToVolunteer.setId(id);
            return messageToVolunteerRepository.save(messageToVolunteer);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public MessageToVolunteer deleteById(Long id) {
        if (messageToVolunteerRepository.findById(id).isPresent()) {
            messageToVolunteerRepository.deleteById(id);
            return messageToVolunteerRepository.findById(id).get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public List<MessageToVolunteer> findAll() {
        return messageToVolunteerRepository.findAll();
    }
}
