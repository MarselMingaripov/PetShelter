package com.example.petshelter.bot.service;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public interface KeyboardService {

    InlineKeyboardMarkup getMenuKeyboard();

    InlineKeyboardMarkup getCatShelterCommonInfoMenuKeyboard();

    InlineKeyboardMarkup getDogShelterCommonInfoMenuKeyboard();

    InlineKeyboardMarkup getCatShelterMainMenu();

    InlineKeyboardMarkup getDogsShelterMainMenu();

    ReplyKeyboardMarkup shareContactKeyboard();

    InlineKeyboardMarkup getCatShelterConsultMenu();

    InlineKeyboardMarkup getDogShelterConsultMenu();
}
