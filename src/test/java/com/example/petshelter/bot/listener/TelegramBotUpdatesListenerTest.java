package com.example.petshelter.bot.listener;

import com.example.petshelter.bot.service.KeyboardService;
import com.example.petshelter.service.*;
import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TelegramBotUpdatesListenerTest {

    @Mock
    private TelegramBot telegramBot;
    @Mock
    private UserService userService;
    @Mock
    private CatShelterService catShelterService;
    @Mock
    private MessageToVolunteerService message;
    @Mock
    private KeyboardService keyboardService;
    @Mock
    private ReportService reportService;
    @Mock
    private CatOwnerService catOwnerService;
    @Mock
    private DogOwnerService dogOwnerService;
    @Mock
    private DogShelterService dogShelterService;
    @Mock
    private MessageToVolunteerService messageToVolunteerService;

    @InjectMocks
    private TelegramBotUpdatesListener telegramBotUpdatesListener;

    @Test
    public void handleStartTest() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(Objects.requireNonNull(TelegramBotUpdatesListenerTest.class.getResource("update.json")).toURI()));
        Update update = BotUtils.fromJson(json.replace("%text%", "/start"), Update.class);
        SendResponse sendResponse = BotUtils.fromJson("""
                {
                "ok": true
                }
                """, SendResponse.class);
        when(telegramBot.execute(any())).thenReturn(sendResponse);

        telegramBotUpdatesListener.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        assertThat(actual.getParameters().get("chat_id")).isEqualTo(update.message().chat().id());
        assertThat(actual.getParameters().get("text")).isEqualTo("Здравствуйте! Вы используете бот для приюта животных. Выберете, пожалуйста, приют!");
    }

    @Test
    public void handleDogsTest() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(Objects.requireNonNull(TelegramBotUpdatesListenerTest.class.getResource("updateCallback.json")).toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", "/dogs"), Update.class);
        SendResponse sendResponse = BotUtils.fromJson("""
                {
                "ok": true
                }
                """, SendResponse.class);
        when(telegramBot.execute(any())).thenReturn(sendResponse);

        telegramBotUpdatesListener.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        assertThat(actual.getParameters().get("chat_id")).isEqualTo(update.callbackQuery().message().chat().id());
        assertThat(actual.getParameters().get("text")).isEqualTo("Выберете нужный вариант");
    }

    /*@Test
    public void handlePhotoTest() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(Objects.requireNonNull(TelegramBotUpdatesListenerTest.class.getResource("updatePhoto.json")).toURI()));
        Update update = BotUtils.fromJson(json.replace("%text%", "text"), Update.class);
        SendResponse sendResponse = BotUtils.fromJson("""
                {
                "ok": true
                }
                """, SendResponse.class);
        when(telegramBot.execute(any())).thenReturn(sendResponse);

        telegramBotUpdatesListener.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        ArgumentCaptor<GetFile> argumentCaptor1 = ArgumentCaptor.forClass(GetFile.class);
        verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        assertThat(actual.getParameters().get("chat_id")).isEqualTo(update.message().chat().id());
        assertThat(actual.getParameters().get("text")).isEqualTo("""
                                    Отправьте данные в отчете в следующем формате без кавычек - 
                                    "Отчет. Рацион питания. Состояние здоровья. Изменения в поведении"
                                    Недопустимо отправлять более одного отчета в день. Не нарушайте, пожалуйста, правила!
                                    """);
    }*/

    @Test
    public void handleCatsTest() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(Objects.requireNonNull(TelegramBotUpdatesListenerTest.class.getResource("updateCallback.json")).toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", "/cats"), Update.class);
        SendResponse sendResponse = BotUtils.fromJson("""
                {
                "ok": true
                }
                """, SendResponse.class);
        when(telegramBot.execute(any())).thenReturn(sendResponse);

        telegramBotUpdatesListener.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        assertThat(actual.getParameters().get("chat_id")).isEqualTo(update.callbackQuery().message().chat().id());
        assertThat(actual.getParameters().get("text")).isEqualTo("Выберете нужный вариант");
    }

    @Test
    public void handleCommonInformationTest() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(Objects.requireNonNull(TelegramBotUpdatesListenerTest.class.getResource("updateCallback.json")).toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", "/common_information"), Update.class);
        SendResponse sendResponse = BotUtils.fromJson("""
                {
                "ok": true
                }
                """, SendResponse.class);
        when(telegramBot.execute(any())).thenReturn(sendResponse);

        telegramBotUpdatesListener.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        assertThat(actual.getParameters().get("chat_id")).isEqualTo(update.callbackQuery().message().chat().id());
        assertThat(actual.getParameters().get("text")).isEqualTo("Выберете нужный вариант");
    }

    @Test
    public void handleRegisterTest() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(Objects.requireNonNull(TelegramBotUpdatesListenerTest.class.getResource("updateCallback.json")).toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", "/register"), Update.class);
        SendResponse sendResponse = BotUtils.fromJson("""
                {
                "ok": true
                }
                """, SendResponse.class);
        when(telegramBot.execute(any())).thenReturn(sendResponse);

        telegramBotUpdatesListener.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        assertThat(actual.getParameters().get("chat_id")).isEqualTo(update.callbackQuery().message().chat().id());
        assertThat(actual.getParameters().get("text")).isEqualTo("Нажмите, чтобы отправить свой номер в базу");
    }


}