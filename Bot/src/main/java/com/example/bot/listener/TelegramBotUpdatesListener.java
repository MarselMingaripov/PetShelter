package com.example.bot.listener;

import com.example.bot.controller.BotController;
import com.example.bot.controller.MessageController;
import com.example.bot.controller.UserController;
import com.example.bot.service.KeyboardService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final BotController controller;
    private final KeyboardService keyboardService;
    private final MessageController messageController;
    private final UserController userController;


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> list) {
        Long tgId = 0L;
        try {
            list.forEach(update -> {
                logger.info("Handles update: {}", update);
                try {
                    String number = update.message().contact().phoneNumber();
                    logger.info(number);
                    userController.sendUser("+" + number + " " + update.message().chat().id());
                    sendMessageInCatShelterMenu(update.message().chat().id(), "Номер отправлен в бд");
                } catch (NullPointerException e){
                    logger.error(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Long callbackQueryChatId = update.callbackQuery().message().chat().id();
                    if (update.callbackQuery() != null) {
                        CallbackQuery callbackQuery = update.callbackQuery();
                        String data = callbackQuery.data();
                        switch (data) {
                            case "/dogs":
                                sendMessage(callbackQueryChatId, "Вы вошли в меню приюта для собак!");
                                break;
                            case "/cats":
                                sendMessageInCatShelterMenu(callbackQueryChatId, "Выберете нужный вариант");
                                break;
                            case "/common_information":
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, "Выберете нужный вариант");
                                break;
                            case "/information":
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, controller.getCatShelterInfo());
                                break;
                            case "/address":
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, controller.getCatShelterAddressAndWorkSchedule());
                                break;
                            case "/phone_number":
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, controller.getCatShelterPhoneNumber());
                                break;
                            case "/security_contacts":
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, controller.getCatShelterSecurityContacts());
                                break;
                            case "/safety_recommendations":
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, controller.getCatShelterSafetyRecommendations());
                                break;
                            case "/register":
                                sendMessageShareContact(callbackQueryChatId, "Нажмите, чтобы отправить свой номер в базу");
                                break;
                            case "/previous":
                                sendMessageInCatShelterMenu(callbackQueryChatId, "Выберете приют!");
                                break;
                            case "/main":
                                sendMessage(callbackQueryChatId, "Выберете приют!");
                                break;
                            case "/preparing":
                                sendMessageInCatShelterPreparingMenu(callbackQueryChatId, "Пока не готово");
                                break;
                            case "/message":
                                String number = userController.getCatShelterInfo(callbackQueryChatId);
                                if (number != null) {
                                    try {
                                        messageController.sendMessage(number + " этот пользователь просит помощи");
                                        sendMessageInCatShelterMenu(callbackQueryChatId, "Сообщение отправлено, скоро с Вами свяжутся!");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    sendMessage(callbackQueryChatId, "Возможно вы не зарегистрированы");
                                }
                                break;

                        }
                        logger.info(data);
                    }
                    return;
                } catch (NullPointerException e) {
                    logger.error("Error during callbackQuery id getting", e);
                }


                Message message = update.message();
                Long chatId = message.chat().id();
                String text = message.text();

                switch (text) {
                    case "/start":
                        sendMessage(chatId, "Здравствуйте! Вы используете бот для приюта животных. Выберете, пожалуйста, приют!");
                        break;
                    default:

                }

                /*if ("/start".equals(text)){
                    sendMessage(chatId, "Здравствуйте! Вы используете бот для приюта животных. Выберете, пожалуйста, приют!");
                } else if (text != null){
                    sendMessage(chatId, "Нажмите старт! Другие команды не работают еще!");
                }*/
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(keyboardService.getMenuKeyboard());
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }

    private void sendMessageInCatShelterMenu(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(keyboardService.getCatShelterMainMenu());
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }

    private void sendMessageInCatShelterCommonInfoMenu(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(keyboardService.getCatShelterCommonInfoMenuKeyboard());
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }

    private void sendMessageInCatShelterPreparingMenu(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(keyboardService.getCatShelterCommonInfoMenuKeyboard());
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }

    private void sendMessageShareContact(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(keyboardService.shareContactKeyboard());
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }
    public void sendM(Long chatId, String text){
        SendMessage sendMessage = new SendMessage(chatId, text);
        //sendMessage.replyMarkup(keyboardService.shareContactKeyboard());
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }
}
