package com.example.petshelter.bot.service.impl;

import com.example.petshelter.bot.service.KeyboardService;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardServiceImpl implements KeyboardService {

    @Override
    public InlineKeyboardMarkup getMenuKeyboard() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton catsShelter = new InlineKeyboardButton("Приют котов");
        catsShelter.callbackData("/cats");
        row.add(catsShelter);
        keyboard.addRow(catsShelter);

        InlineKeyboardButton dogsShelter = new InlineKeyboardButton("Приют собак");
        dogsShelter.callbackData("/dogs");
        row.add(dogsShelter);
        keyboard.addRow(dogsShelter);

        return keyboard;
    }

    @Override
    public InlineKeyboardMarkup getCatShelterCommonInfoMenuKeyboard() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton catsShelterInfo = new InlineKeyboardButton("Информация");
        catsShelterInfo.callbackData("/information");
        row.add(catsShelterInfo);
        keyboard.addRow(catsShelterInfo);

        InlineKeyboardButton catsShelterAddress = new InlineKeyboardButton("Адрес/режим");
        catsShelterAddress.callbackData("/address");
        row.add(catsShelterAddress);
        keyboard.addRow(catsShelterAddress);

        InlineKeyboardButton catsShelterPhoneNumber = new InlineKeyboardButton("Номер телефона");
        catsShelterPhoneNumber.callbackData("/phone_number");
        row.add(catsShelterPhoneNumber);
        keyboard.addRow(catsShelterPhoneNumber);

        InlineKeyboardButton catsShelterSecurityContacts = new InlineKeyboardButton("Телефон охраны");
        catsShelterSecurityContacts.callbackData("/security_contacts");
        row.add(catsShelterSecurityContacts);
        keyboard.addRow(catsShelterSecurityContacts);

        InlineKeyboardButton catsShelterSafetyRecommendations = new InlineKeyboardButton("Рекомендации по безопасности");
        catsShelterSafetyRecommendations.callbackData("/safety_recommendations");
        row.add(catsShelterSafetyRecommendations);
        keyboard.addRow(catsShelterSafetyRecommendations);

        InlineKeyboardButton catsShelterRegister = new InlineKeyboardButton("Зарегистрироваться");
        catsShelterRegister.callbackData("/register");
        row.add(catsShelterRegister);
        keyboard.addRow(catsShelterRegister);

        InlineKeyboardButton catsShelterPreviousMenu = new InlineKeyboardButton("Предыдущее меню");
        catsShelterPreviousMenu.callbackData("/previous");
        row.add(catsShelterPreviousMenu);
        keyboard.addRow(catsShelterPreviousMenu);

        return keyboard;
    }

    @Override
    public InlineKeyboardMarkup getDogShelterCommonInfoMenuKeyboard() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton dogsShelterInfo = new InlineKeyboardButton("Информация");
        dogsShelterInfo.callbackData("/informationDogs");
        row.add(dogsShelterInfo);
        keyboard.addRow(dogsShelterInfo);

        InlineKeyboardButton dogsShelterAddress = new InlineKeyboardButton("Адрес/режим");
        dogsShelterAddress.callbackData("/addressDogs");
        row.add(dogsShelterAddress);
        keyboard.addRow(dogsShelterAddress);

        InlineKeyboardButton dogsShelterPhoneNumber = new InlineKeyboardButton("Номер телефона");
        dogsShelterPhoneNumber.callbackData("/phone_numberDogs");
        row.add(dogsShelterPhoneNumber);
        keyboard.addRow(dogsShelterPhoneNumber);

        InlineKeyboardButton dogsShelterSecurityContacts = new InlineKeyboardButton("Телефон охраны");
        dogsShelterSecurityContacts.callbackData("/security_contactsDogs");
        row.add(dogsShelterSecurityContacts);
        keyboard.addRow(dogsShelterSecurityContacts);

        InlineKeyboardButton dogsShelterSafetyRecommendations = new InlineKeyboardButton("Рекомендации по безопасности");
        dogsShelterSafetyRecommendations.callbackData("/safety_recommendationsDogs");
        row.add(dogsShelterSafetyRecommendations);
        keyboard.addRow(dogsShelterSafetyRecommendations);

        InlineKeyboardButton dogsShelterRegister = new InlineKeyboardButton("Зарегистрироваться");
        dogsShelterRegister.callbackData("/registerDogs");
        row.add(dogsShelterRegister);
        keyboard.addRow(dogsShelterRegister);

        InlineKeyboardButton dogsShelterPreviousMenu = new InlineKeyboardButton("Предыдущее меню");
        dogsShelterPreviousMenu.callbackData("/previousDogs");
        row.add(dogsShelterPreviousMenu);
        keyboard.addRow(dogsShelterPreviousMenu);

        return keyboard;
    }

    @Override
    public InlineKeyboardMarkup getCatShelterMainMenu() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton catsShelterCommonInfo = new InlineKeyboardButton("Общая информация о приюте");
        catsShelterCommonInfo.callbackData("/common_information");
        row.add(catsShelterCommonInfo);
        keyboard.addRow(catsShelterCommonInfo);

        InlineKeyboardButton catsShelterPreparing = new InlineKeyboardButton("Подготовка к усыновлению животного");
        catsShelterPreparing.callbackData("/preparing");
        row.add(catsShelterPreparing);
        keyboard.addRow(catsShelterPreparing);

        InlineKeyboardButton catsShelterSendReport = new InlineKeyboardButton("Отправить отчет");
        catsShelterSendReport.callbackData("/send_report");
        row.add(catsShelterSendReport);
        keyboard.addRow(catsShelterSendReport);

        InlineKeyboardButton catsShelterMessage = new InlineKeyboardButton("Написать сообщение волонтеру");
        catsShelterMessage.callbackData("/message");
        row.add(catsShelterMessage);
        keyboard.addRow(catsShelterMessage);

        InlineKeyboardButton catsShelterMainMenu = new InlineKeyboardButton("Главное меню");
        catsShelterMainMenu.callbackData("/main");
        row.add(catsShelterMainMenu);
        keyboard.addRow(catsShelterMainMenu);

        return keyboard;
    }

    @Override
    public InlineKeyboardMarkup getDogsShelterMainMenu() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton dogsShelterCommonInfo = new InlineKeyboardButton("Общая информация о приюте");
        dogsShelterCommonInfo.callbackData("/common_informationDogs");
        row.add(dogsShelterCommonInfo);
        keyboard.addRow(dogsShelterCommonInfo);

        InlineKeyboardButton dogsShelterPreparing = new InlineKeyboardButton("Подготовка к усыновлению животного");
        dogsShelterPreparing.callbackData("/preparingDogs");
        row.add(dogsShelterPreparing);
        keyboard.addRow(dogsShelterPreparing);

        InlineKeyboardButton dogsShelterSendReport = new InlineKeyboardButton("Отправить отчет");
        dogsShelterSendReport.callbackData("/send_reportDogs");
        row.add(dogsShelterSendReport);
        keyboard.addRow(dogsShelterSendReport);

        InlineKeyboardButton dogsShelterMessage = new InlineKeyboardButton("Написать сообщение волонтеру");
        dogsShelterMessage.callbackData("/messageDogs");
        row.add(dogsShelterMessage);
        keyboard.addRow(dogsShelterMessage);

        InlineKeyboardButton catsShelterMainMenu = new InlineKeyboardButton("Главное меню");
        catsShelterMainMenu.callbackData("/main");
        row.add(catsShelterMainMenu);
        keyboard.addRow(catsShelterMainMenu);

        return keyboard;
    }

    @Override
    public ReplyKeyboardMarkup shareContactKeyboard() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton("отправить свой контакт").requestContact(true));
        KeyboardButton button = new KeyboardButton("Главное меню");
        keyboard.resizeKeyboard(true);
        keyboard.oneTimeKeyboard(false);
        return keyboard;
    }

    @Override
    public InlineKeyboardMarkup getCatShelterConsultMenu() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton catsShelterDating = new InlineKeyboardButton("Правила знакомства с животным");
        catsShelterDating.callbackData("/dating");
        row.add(catsShelterDating);
        keyboard.addRow(catsShelterDating);

        InlineKeyboardButton catsShelterDOCUMENTS = new InlineKeyboardButton("Список документов, необходимых для того, чтобы забрать животное из приюта");
        catsShelterDOCUMENTS.callbackData("/documents");
        row.add(catsShelterDOCUMENTS);
        keyboard.addRow(catsShelterDOCUMENTS);

        InlineKeyboardButton catsShelterTRANSPORTATION = new InlineKeyboardButton("Список рекомендаций про транспортировке");
        catsShelterTRANSPORTATION.callbackData("/transportation");
        row.add(catsShelterTRANSPORTATION);
        keyboard.addRow(catsShelterTRANSPORTATION);

        InlineKeyboardButton catsShelterARRANGEMENT_KITTEN = new InlineKeyboardButton("Рекомендации по обустройству дома котенка");
        catsShelterARRANGEMENT_KITTEN.callbackData("/arrangement_kitten");
        row.add(catsShelterARRANGEMENT_KITTEN);
        keyboard.addRow(catsShelterARRANGEMENT_KITTEN);

        InlineKeyboardButton catsShelterARRANGEMENT_CAT = new InlineKeyboardButton("Рекомендации по обустройству дома взрослого животного");
        catsShelterARRANGEMENT_CAT.callbackData("/arrangement_cat");
        row.add(catsShelterARRANGEMENT_CAT);
        keyboard.addRow(catsShelterARRANGEMENT_CAT);

        InlineKeyboardButton catsShelterARRANGEMENT_DISABLED = new InlineKeyboardButton("Рекомендации по обустройству дома животного с ограничениями");
        catsShelterARRANGEMENT_DISABLED.callbackData("/arrangement_disabled");
        row.add(catsShelterARRANGEMENT_DISABLED);
        keyboard.addRow(catsShelterARRANGEMENT_DISABLED);

        InlineKeyboardButton catsShelterPreviousMenu = new InlineKeyboardButton("Предыдущее меню");
        catsShelterPreviousMenu.callbackData("/previous");
        row.add(catsShelterPreviousMenu);
        keyboard.addRow(catsShelterPreviousMenu);

        return keyboard;
    }

    @Override
    public InlineKeyboardMarkup getDogShelterConsultMenu() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton dogsShelterDating = new InlineKeyboardButton("Правила знакомства с животным");
        dogsShelterDating.callbackData("/datingDogs");
        row.add(dogsShelterDating);
        keyboard.addRow(dogsShelterDating);

        InlineKeyboardButton dogsShelterDOCUMENTS = new InlineKeyboardButton("Список документов, необходимых для того, чтобы забрать животное из приюта");
        dogsShelterDOCUMENTS.callbackData("/documentsDog");
        row.add(dogsShelterDOCUMENTS);
        keyboard.addRow(dogsShelterDOCUMENTS);

        InlineKeyboardButton dogsShelterTRANSPORTATION = new InlineKeyboardButton("Список рекомендаций про транспортировке");
        dogsShelterTRANSPORTATION.callbackData("/transportationDog");
        row.add(dogsShelterTRANSPORTATION);
        keyboard.addRow(dogsShelterTRANSPORTATION);

        InlineKeyboardButton dogsShelterARRANGEMENT_PUPPY = new InlineKeyboardButton("Рекомендации по обустройству дома котенка");
        dogsShelterARRANGEMENT_PUPPY.callbackData("/arrangement_puppy");
        row.add(dogsShelterARRANGEMENT_PUPPY);
        keyboard.addRow(dogsShelterARRANGEMENT_PUPPY);

        InlineKeyboardButton dogsShelterARRANGEMENT_DOG = new InlineKeyboardButton("Рекомендации по обустройству дома взрослого животного");
        dogsShelterARRANGEMENT_DOG.callbackData("/arrangement_dog");
        row.add(dogsShelterARRANGEMENT_DOG);
        keyboard.addRow(dogsShelterARRANGEMENT_DOG);

        InlineKeyboardButton dogsShelterARRANGEMENT_DISABLED = new InlineKeyboardButton("Рекомендации по обустройству дома животного с ограничениями");
        dogsShelterARRANGEMENT_DISABLED.callbackData("/arrangement_disabledDog");
        row.add(dogsShelterARRANGEMENT_DISABLED);
        keyboard.addRow(dogsShelterARRANGEMENT_DISABLED);

        InlineKeyboardButton dogsShelterDogHandlerRecommendations = new InlineKeyboardButton("Советы кинолога по первичному общению с собакой");
        dogsShelterDogHandlerRecommendations.callbackData("/dogHandlerRecommendations");
        row.add(dogsShelterDogHandlerRecommendations);
        keyboard.addRow(dogsShelterDogHandlerRecommendations);

        InlineKeyboardButton dogsShelterRecommendedDogHandlers = new InlineKeyboardButton("Рекомендации по проверенным кинологам для дальнейшего обращения к ним");
        dogsShelterRecommendedDogHandlers.callbackData("/recommendedDogHandlers");
        row.add(dogsShelterRecommendedDogHandlers);
        keyboard.addRow(dogsShelterRecommendedDogHandlers);

        InlineKeyboardButton dogsShelterCancelDogTaker = new InlineKeyboardButton("Cписок причин, почему могут отказать и не дать забрать собаку из приюта");
        dogsShelterCancelDogTaker.callbackData("/cancelDogTaker");
        row.add(dogsShelterCancelDogTaker);
        keyboard.addRow(dogsShelterCancelDogTaker);

        InlineKeyboardButton dogsShelterPreviousMenu = new InlineKeyboardButton("Предыдущее меню");
        dogsShelterPreviousMenu.callbackData("/previousDogs");
        row.add(dogsShelterPreviousMenu);
        keyboard.addRow(dogsShelterPreviousMenu);

        return keyboard;
    }
}
