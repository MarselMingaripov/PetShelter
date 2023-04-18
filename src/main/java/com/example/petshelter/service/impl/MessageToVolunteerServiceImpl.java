package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.MessageToVolunteerRepository;
import com.example.petshelter.service.MessageToVolunteerService;
import com.example.petshelter.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageToVolunteerServiceImpl implements MessageToVolunteerService {

    private final MessageToVolunteerRepository messageToVolunteerRepository;
    private final ValidationService validationService;

    @Override
    public MessageToVolunteer createMessageToVolunteer(MessageToVolunteer messageToVolunteer) {
        if (!validationService.validate(messageToVolunteer)) {
            throw new ValidationException(messageToVolunteer.toString());
        }
        return messageToVolunteerRepository.save(messageToVolunteer);
    }

    @Override
    public MessageToVolunteer findById(Long id) {
        Optional<MessageToVolunteer> message = messageToVolunteerRepository.findById(id);
        if (message.isPresent()) {
            return message.get();
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
        MessageToVolunteer message = findById(id);
        messageToVolunteerRepository.delete(message);
        return message;
    }

    @Override
    public List<MessageToVolunteer> findAll() {
        return messageToVolunteerRepository.findAll();
    }
}
