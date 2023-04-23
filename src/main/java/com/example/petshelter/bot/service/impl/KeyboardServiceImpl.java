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
}
