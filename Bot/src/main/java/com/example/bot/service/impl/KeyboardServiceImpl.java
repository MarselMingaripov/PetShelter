package com.example.bot.service.impl;

import com.example.bot.service.KeyboardService;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardServiceImpl implements KeyboardService {
    @Override
    public InlineKeyboardMarkup getMenuKeyboard() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton catsShelter = new InlineKeyboardButton("Приют котов");
        catsShelter.callbackData("/cats");
        row.add(catsShelter);
        keyboard.addRow(catsShelter);

        InlineKeyboardButton dogsShelter = new InlineKeyboardButton("Приют собак");
        dogsShelter.callbackData("/dogs");
        row.add(dogsShelter);
        keyboard.addRow(dogsShelter);

        //keyboardRows.add(row);

        return keyboard;
    }
}
