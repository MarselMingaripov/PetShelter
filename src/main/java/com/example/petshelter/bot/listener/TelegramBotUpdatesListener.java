package com.example.petshelter.bot.listener;

import com.example.petshelter.bot.service.KeyboardService;
import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.entity.Report;
import com.example.petshelter.entity.User;
import com.example.petshelter.service.*;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final UserService userService;
    private final CatShelterService catShelterService;
    private final MessageToVolunteerService message;
    private final KeyboardService keyboardService;
    private final ReportService reportService;
    private final CatOwnerService catOwnerService;
    private final MessageToVolunteerService messageToVolunteerService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> list) {

        try {
            list.forEach(update -> {
                logger.info("Handles update: {}", update);
                try {
                    String number = update.message().contact().phoneNumber();
                    logger.info(number);
                    User user = userService.createUserFromTgB("+" + number + " " + update.message().chat().id());
                    sendMessageInCatShelterMenu(update.message().chat().id(), "Номер отправлен в бд");
                } catch (NullPointerException e) {
                    logger.error(e.getMessage());
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
                            case "/send_report":
                                SendMessage sendMessage = new SendMessage(callbackQueryChatId, "Отправьте фото вашего питомца");
                                telegramBot.execute(sendMessage);
                                break;
                            case "/information":
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, catShelterService.returnInformation(1L));
                                break;
                            case "/address":
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, catShelterService.returnAddressAndWorkSchedule(1L));
                                break;
                            case "/phone_number":
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, catShelterService.returnPhone(1L));
                                break;
                            case "/security_contacts":
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, catShelterService.returnSecurityContacts(1L));
                                break;
                            case "/safety_recommendations":
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, catShelterService.returnSafetyRecommendations(1L));
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
                                sendMessageCatConsult(callbackQueryChatId, "Выберете нужный вариант");
                                break;
                            case "/message":
                                String number = userService.findByTelegramID(callbackQueryChatId).getPhoneNumber();
                                if (number != null) {
                                    MessageToVolunteer messageToVolunteer = message.createMessageFromText(number + " этот пользователь просит помощи");
                                    message.createMessageToVolunteer(messageToVolunteer);
                                    sendMessageInCatShelterMenu(callbackQueryChatId, "Сообщение отправлено, скоро с Вами свяжутся!");
                                } else {
                                    sendMessage(callbackQueryChatId, "Возможно вы не зарегистрированы");
                                }
                                break;
                            case "/dating":
                                sendMessageCatConsult(callbackQueryChatId, catShelterService.returnDating(1L));
                                break;
                            case "/documents":
                                sendMessageCatConsult(callbackQueryChatId, catShelterService.returnDocuments(1L));
                                break;
                            case "/transportation":
                                sendMessageCatConsult(callbackQueryChatId, catShelterService.returnTransportation(1L));
                                break;
                            case "/arrangement_kitten":
                                sendMessageCatConsult(callbackQueryChatId, catShelterService.returnArrangementKitten(1L));
                                break;
                            case "/arrangement_cat":
                                sendMessageCatConsult(callbackQueryChatId, catShelterService.returnArrangementCat(1L));
                                break;
                            case "/arrangement_disabled":
                                sendMessageCatConsult(callbackQueryChatId, catShelterService.returnArrangementDisabled(1L));
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

                if (text != null) {
                    try {
                            String[] arr = text.split(" ");
                            if (arr[0].equals("Отчет.")) {
                                Report report = reportService.findBySenderAndDate(chatId, LocalDate.now());
                                report.setFoodRation(arr[1]);
                                report.setGeneralHealth(arr[2]);
                                report.setBehaviorChanges(arr[3]);
                                reportService.createReport(report);
                                messageToVolunteerService.createMessageToVolunteer(new MessageToVolunteer(chatId + "", userService.findByTelegramID(chatId)
                                        .getPhoneNumber() + " он отправил отчет! Посмотрите, пожалуйста, что он там наприсылал"));
                                sendMessageInCatShelterMenu(chatId, "Спасибо!");
                            }
                        } catch (ArrayIndexOutOfBoundsException e){
                        logger.error(e.getMessage());
                    }
                }

                if (message.photo() != null) {
                    PhotoSize photoSize = message.photo()[message.photo().length - 1];
                    GetFileResponse getFileResponse =
                            telegramBot.execute(new GetFile(photoSize.fileId()));
                    if (getFileResponse.isOk()) {
                        try {
                            String extension = StringUtils.getFilenameExtension(
                                    getFileResponse.file().filePath());
                            byte[] image = telegramBot.getFileContent(getFileResponse.file());
                            Files.write(Paths.get(UUID.randomUUID() + "." + extension), image);
                            reportService.createReport(new Report(image, chatId));
                            SendMessage sendMessage = new SendMessage(chatId, """
                                    Отправьте данные в отчете в следующем формате без кавычек - 
                                    "Отчет. Рацион питания. Состояние здоровья. Изменения в поведении"
                                    Недопустимо отправлять более одного отчета в день. Не нарушайте, пожалуйста, правила!
                                    """);
                            telegramBot.execute(sendMessage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                switch (text) {
                    case "/start":
                        sendMessage(chatId, "Здравствуйте! Вы используете бот для приюта животных. Выберете, пожалуйста, приют!");
                        break;
                    default:

                }
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

    public void sendM(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage(chatId, text);
        //sendMessage.replyMarkup(keyboardService.shareContactKeyboard());
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }

    public void sendMesToVolunteer(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage(chatId, text);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }

    public void sendMessageCatConsult(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(keyboardService.getCatShelterConsultMenu());
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }
}
