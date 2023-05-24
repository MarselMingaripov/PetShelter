package com.example.petshelter.bot.listener;

import com.example.petshelter.bot.service.KeyboardService;
import com.example.petshelter.entity.*;
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

    public static final String DOGS = "/dogs";
    public static final String CATS = "/cats";
    public static final String COMMON_INFORMATION = "/common_information";
    public static final String SEND_REPORT = "/send_report";
    public static final String SEND_REPORT_DOGS = "/send_reportDogs";
    public static final String INFORMATION = "/information";
    public static final String ADDRESS = "/address";
    public static final String PHONE_NUMBER = "/phone_number";
    public static final String SECURITY_CONTACTS = "/security_contacts";
    public static final String SAFETY_RECOMMENDATIONS = "/safety_recommendations";
    public static final String REGISTER = "/register";
    public static final String REGISTER_DOGS = "/registerDogs";
    public static final String PREVIOUS = "/previous";
    public static final String MAIN = "/main";
    public static final String COMMON_INFORMATION_DOGS = "/common_informationDogs";
    public static final String INFORMATION_DOGS = "/informationDogs";
    public static final String ADDRESS_DOGS = "/addressDogs";
    public static final String PHONE_NUMBER_DOGS = "/phone_numberDogs";
    public static final String SECURITY_CONTACTS_DOGS = "/security_contactsDogs";
    public static final String SAFETY_RECOMMENDATIONS_DOGS = "/safety_recommendationsDogs";
    public static final String PREVIOUS_DOGS = "/previousDogs";
    public static final String PREPARING = "/preparing";
    public static final String PREPARING_DOGS = "/preparingDogs";
    public static final String DATING_DOGS = "/datingDogs";
    public static final String DOCUMENTS_DOGS = "/documentsDog";
    public static final String TRANSPORTATION_DOGS = "/transportationDog";
    public static final String ARRANGEMENT_PUPPY = "/arrangement_puppy";
    public static final String ARRANGEMENT_DOG = "/arrangement_dog";
    public static final String ARRANGEMENT_DISABLED_DOG = "/arrangement_disabledDog";
    public static final String DOG_HANDLER_RECOMMENDATIONS = "/dogHandlerRecommendations";
    public static final String RECOMMENDED_DOG_HANDLERS = "/recommendedDogHandlers";
    public static final String CANCEL_DOG_TAKER = "/cancelDogTaker";
    public static final String MESSAGE = "/message";
    public static final String MESSAGE_DOGS = "/messageDogs";
    public static final String DATING = "/dating";
    public static final String DOCUMENTS = "/documents";
    public static final String TRANSPORTATION = "/transportation";
    public static final String ARRANGEMENT_KITTEN = "/arrangement_kitten";
    public static final String ARRANGEMENT_CAT = "/arrangement_cat";
    public static final String ARRANGEMENT_DISABLED = "/arrangement_disabled";

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final UserService userService;
    private final CatShelterService catShelterService;
    private final MessageToVolunteerService message;
    private final KeyboardService keyboardService;
    private final ReportService reportService;
    private final CatOwnerService catOwnerService;
    private final DogOwnerService dogOwnerService;
    private final DogShelterService dogShelterService;
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
                            case DOGS:
                                sendMessageInDogShelterMenu(callbackQueryChatId, "Выберете нужный вариант");
                                break;
                            case CATS:
                                sendMessageInCatShelterMenu(callbackQueryChatId, "Выберете нужный вариант");
                                break;
                            case COMMON_INFORMATION:
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, "Выберете нужный вариант");
                                break;
                            case SEND_REPORT:
                            case SEND_REPORT_DOGS:
                                SendMessage sendMessage = new SendMessage(callbackQueryChatId, "Отправьте фото вашего питомца");
                                telegramBot.execute(sendMessage);
                                break;
                            case INFORMATION:
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, catShelterService.returnInformation(1L));
                                break;
                            case ADDRESS:
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, catShelterService.returnAddressAndWorkSchedule(1L));
                                break;
                            case PHONE_NUMBER:
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, catShelterService.returnPhone(1L));
                                break;
                            case SECURITY_CONTACTS:
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, catShelterService.returnSecurityContacts(1L));
                                break;
                            case SAFETY_RECOMMENDATIONS:
                                sendMessageInCatShelterCommonInfoMenu(callbackQueryChatId, catShelterService.returnSafetyRecommendations(1L));
                                break;
                            case REGISTER:
                            case REGISTER_DOGS:
                                sendMessageShareContact(callbackQueryChatId, "Нажмите, чтобы отправить свой номер в базу");
                                break;
                            case PREVIOUS:
                                sendMessageInCatShelterMenu(callbackQueryChatId, "Выберите нужный вариант");
                                break;
                            case MAIN:
                                sendMessage(callbackQueryChatId, "Выберете приют!");
                                break;
                            case COMMON_INFORMATION_DOGS:
                                sendMessageInDogShelterCommonInfoMenu(callbackQueryChatId, "Выберете нужный вариант");
                                break;
                            case INFORMATION_DOGS:
                                sendMessageInDogShelterCommonInfoMenu(callbackQueryChatId, dogShelterService.returnInformation(1L));
                                break;
                            case ADDRESS_DOGS:
                                sendMessageInDogShelterCommonInfoMenu(callbackQueryChatId, dogShelterService.returnAddressAndWorkSchedule(1L));
                                break;
                            case PHONE_NUMBER_DOGS:
                                sendMessageInDogShelterCommonInfoMenu(callbackQueryChatId, dogShelterService.returnPhone(1L));
                                break;
                            case SECURITY_CONTACTS_DOGS:
                                sendMessageInDogShelterCommonInfoMenu(callbackQueryChatId, dogShelterService.returnSecurityContacts(1L));
                                break;
                            case SAFETY_RECOMMENDATIONS_DOGS:
                                sendMessageInDogShelterCommonInfoMenu(callbackQueryChatId, dogShelterService.returnSafetyRecommendations(1L));
                                break;
                            case PREVIOUS_DOGS:
                                sendMessageInDogShelterMenu(callbackQueryChatId, "Выберите нужный вариант");
                                break;
                            case PREPARING:
                                sendMessageCatConsult(callbackQueryChatId, "Выберете нужный вариант");
                                break;
                            case PREPARING_DOGS:
                                sendMessageDogConsult(callbackQueryChatId, "Выберете нужный вариант");
                                break;
                            case DATING_DOGS:
                                sendMessageDogConsult(callbackQueryChatId, dogShelterService.returnDating(1L));
                                break;
                            case DOCUMENTS_DOGS:
                                sendMessageDogConsult(callbackQueryChatId, dogShelterService.returnDocuments(1L));
                                break;
                            case TRANSPORTATION_DOGS:
                                sendMessageDogConsult(callbackQueryChatId, dogShelterService.returnTransportation(1L));
                                break;
                            case ARRANGEMENT_PUPPY:
                                sendMessageDogConsult(callbackQueryChatId, dogShelterService.returnArrangementPuppy(1L));
                                break;
                            case ARRANGEMENT_DOG:
                                sendMessageDogConsult(callbackQueryChatId, dogShelterService.returnArrangementDog(1L));
                                break;
                            case ARRANGEMENT_DISABLED_DOG:
                                sendMessageDogConsult(callbackQueryChatId, dogShelterService.returnArrangementDisabled(1L));
                                break;
                            case DOG_HANDLER_RECOMMENDATIONS:
                                sendMessageDogConsult(callbackQueryChatId, dogShelterService.returnDogHandlerRecommendations(1L));
                                break;
                            case RECOMMENDED_DOG_HANDLERS:
                                sendMessageDogConsult(callbackQueryChatId, dogShelterService.returnRecommendedDogHandlers(1L));
                                break;
                            case CANCEL_DOG_TAKER:
                                sendMessageDogConsult(callbackQueryChatId, dogShelterService.returnCancelDogTaker(1L));
                                break;
                            case MESSAGE:
                            case MESSAGE_DOGS:
                                String number = userService.findByTelegramID(callbackQueryChatId).getPhoneNumber();
                                if (number != null) {
                                    MessageToVolunteer messageToVolunteer = message.createMessageFromText(number + " этот пользователь просит помощи");
                                    message.createMessageToVolunteer(messageToVolunteer);
                                    sendMessageInCatShelterMenu(callbackQueryChatId, "Сообщение отправлено, скоро с Вами свяжутся!");
                                } else {
                                    sendMessage(callbackQueryChatId, "Возможно вы не зарегистрированы");
                                }
                                break;
                            case DATING:
                                sendMessageCatConsult(callbackQueryChatId, catShelterService.returnDating(1L));
                                break;
                            case DOCUMENTS:
                                sendMessageCatConsult(callbackQueryChatId, catShelterService.returnDocuments(1L));
                                break;
                            case TRANSPORTATION:
                                sendMessageCatConsult(callbackQueryChatId, catShelterService.returnTransportation(1L));
                                break;
                            case ARRANGEMENT_KITTEN:
                                sendMessageCatConsult(callbackQueryChatId, catShelterService.returnArrangementKitten(1L));
                                break;
                            case ARRANGEMENT_CAT:
                                sendMessageCatConsult(callbackQueryChatId, catShelterService.returnArrangementCat(1L));
                                break;
                            case ARRANGEMENT_DISABLED:
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
                            String[] arr = text.split("\\.");
                            if (arr[0].equals("Отчет")) {
                                Report report = reportService.findBySenderAndDate(chatId, LocalDate.now());
                                report.setFoodRation(arr[1]);
                                report.setGeneralHealth(arr[2]);
                                report.setBehaviorChanges(arr[3]);
                                reportService.createReport(report);
                                User user = userService.findByTelegramID(chatId);
                                if (catOwnerService.findByPhoneNumber(user.getPhoneNumber()) != null){
                                    CatOwner catOwner = catOwnerService.findByPhoneNumber(user.getPhoneNumber());
                                    if (!catOwner.getTrialPeriodsInActiveStatus().isEmpty()){
                                        catOwner.getTrialPeriodsInActiveStatus().get(0).getReports().add(report);
                                        catOwnerService.createCatOwner(catOwner);
                                    } else {
                                        sendM(chatId, "Проблемы с вашим испытательным периодом, обратитесь к волонтеру, пожалуйста");
                                    }
                                }

                                if (dogOwnerService.findByPhoneNumber(user.getPhoneNumber()) != null){
                                    DogOwner dogOwner = dogOwnerService.findByPhoneNumber(user.getPhoneNumber());
                                    if (!dogOwner.getTrialPeriodsInActiveStatus().isEmpty()){
                                        dogOwner.getTrialPeriodsInActiveStatus().get(0).getReports().add(report);
                                        dogOwnerService.createDogOwner(dogOwner);
                                    } else {
                                        sendM(chatId, "Проблемы с вашим испытательным периодом, обратитесь к волонтеру, пожалуйста");
                                    }
                                }

                                messageToVolunteerService.createMessageToVolunteer(new MessageToVolunteer(userService.findByTelegramID(chatId).getPhoneNumber() + "", userService.findByTelegramID(chatId)
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

    private void sendMessageInDogShelterMenu(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(keyboardService.getDogsShelterMainMenu());
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

    private void sendMessageInDogShelterCommonInfoMenu(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(keyboardService.getDogShelterCommonInfoMenuKeyboard());
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

    public void sendMessageDogConsult(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(keyboardService.getDogShelterConsultMenu());
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }
}
