package com.example.petshelter.controller;

import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.service.MessageToVolunteerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@Tag(name = "API для работы с сообщениями для волонтера",
        description = "Сохранение, изменение, получение, удаление сообщений для волонтера из БД")
public class MessageToVolunteerController {

    private final MessageToVolunteerService messageToVolunteerService;

    @PostMapping
    @Operation(summary = "Сохранение сообщения для волонтера в БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные добавлены в БД")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<MessageToVolunteer> createMessage(@RequestBody String text){
        MessageToVolunteer messageToVolunteer = messageToVolunteerService.createMessageFromText(text);
        return ResponseEntity.ok().body(messageToVolunteerService.createMessageToVolunteer(messageToVolunteer));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение сообщения для волонтера по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<MessageToVolunteer> findMessage(@PathVariable Long id){
        return ResponseEntity.ok().body(messageToVolunteerService.findById(id));
    }

    @PostMapping("/{id}")
    @Operation(summary = "Изменение сообщения для волонтера по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<MessageToVolunteer> updateMessage(@PathVariable Long id, @RequestBody MessageToVolunteer messageToVolunteer){
        return ResponseEntity.ok().body(messageToVolunteerService.updateById(id, messageToVolunteer));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление сообщения для волонтера из БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<MessageToVolunteer> deleteMessage(@PathVariable Long id){
        return ResponseEntity.ok().body(messageToVolunteerService.deleteById(id));
    }

    @GetMapping
    @Operation(summary = "Получение списка сообщений для волонтера")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<List<MessageToVolunteer>> findAll(){
        return ResponseEntity.ok().body(messageToVolunteerService.findAll());
    }

    @GetMapping("/unread")
    @Operation(summary = "Получение списка непрочитанных сообщений для волонтера")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<List<String>> findAllUnread(){
        return ResponseEntity.ok().body(messageToVolunteerService.findAllUnread());
    }

    @GetMapping("/check")
    @Operation(summary = "Проверка на непрочитанные сообщения")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<Boolean> checkAllUnread(){
        return ResponseEntity.ok().body(messageToVolunteerService.checker());
    }
}
