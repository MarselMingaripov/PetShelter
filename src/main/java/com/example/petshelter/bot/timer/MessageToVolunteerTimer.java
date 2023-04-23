package com.example.petshelter.bot.timer;

import com.example.petshelter.bot.listener.TelegramBotUpdatesListener;
import com.example.petshelter.service.MessageToVolunteerService;
import com.example.petshelter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class MessageToVolunteerTimer {

    private final MessageToVolunteerService messageToVolunteerService;
    private final UserService userService;
    private final TelegramBotUpdatesListener telegramBotUpdatesListener;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void checkMessage(){
        Boolean check = messageToVolunteerService.checker();
        if (check){
            List<String> messages = messageToVolunteerService.findAllUnread();
            List<Long> id = userService.returnVolunteerTgId();
            for (Long aLong : id) {
                for (String message : messages) {
                    if (aLong != null) {
                        telegramBotUpdatesListener.sendM(aLong, message);
                    }
                }
            }
        }
    }
}
