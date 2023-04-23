package com.example.bot.service;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public interface KeyboardService {

    InlineKeyboardMarkup getMenuKeyboard();

    InlineKeyboardMarkup getCatShelterCommonInfoMenuKeyboard();

    InlineKeyboardMarkup getCatShelterMainMenu();

    ReplyKeyboardMarkup shareContactKeyboard();
}
