package com.example.petshelter.timer;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.service.CatService;
import com.example.petshelter.service.DogService;
import com.example.petshelter.service.MessageToVolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnimalReserveTimer {

    private final MessageToVolunteerService messageToVolunteerService;
    private final CatService catService;
    private final DogService dogService;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
    public void checkStatus(){
        List<MessageToVolunteer> messages = messageToVolunteerService.findAll();
        List<MessageToVolunteer> filteredList = messages.stream()
                .filter(x -> x.getLocalDateTime().plusDays(1).isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
        String[] textArr = new String[3];
        for (MessageToVolunteer message : filteredList) {
            textArr = message.getText().split(" ");
            if (textArr[1].equals("кошку")){
                catService.changeStatusAnimal(textArr[2], StatusAnimal.IN_THE_SHELTER);
            } else {
                // TODO: 15.04.2023 реализовать метод бронирования собак
                //dogService.changeStatusAnimal(textArr[2], StatusAnimal.IN_THE_SHELTER);
            }
        }
    }
}
