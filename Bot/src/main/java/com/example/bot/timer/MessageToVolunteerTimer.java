package com.example.bot.timer;

import com.example.bot.controller.MessageController;
import com.example.bot.controller.UserController;
import com.example.bot.listener.TelegramBotUpdatesListener;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class MessageToVolunteerTimer {

    private final UserController userController;
    private final MessageController messageController;
    private final TelegramBotUpdatesListener telegramBotUpdatesListener;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void checkMessage(){
        Boolean check = messageController.checkUnread();
        if (check){
            List<String> messages = messageController.getUnread();
            List<Long> id = userController.getVolunteerTgId();
            for (Long aLong : id) {
                for (String message : messages) {
                    telegramBotUpdatesListener.sendM(aLong, message);
                }
            }
        }
    }
}
