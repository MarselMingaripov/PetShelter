package com.example.petshelter.controller;

import com.example.petshelter.entity.User;
import com.example.petshelter.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "API для работы с пользователем",
        description = "Сохранение, изменение, получение, удаление пользователей из БД")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Сохранение пользователя в БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные добавлены в БД")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @PostMapping("/from-tg")
    @Operation(summary = "Сохранение пользователя в БД из ТГ бота")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные добавлены в БД")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<User> createUser(@RequestBody String text){
        User user = userService.createUserFromTgB(text);
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<User> findUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.findById(id));
    }
    @GetMapping("phone/{id}")
    @Operation(summary = "Получение телефона пользователя по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> findUserPhone(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.findByTelegramID(id).getPhoneNumber());
    }

    @PostMapping("/{id}")
    @Operation(summary = "Изменение пользователя по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok().body(userService.updateById(id, user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя из БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.deleteById(id));
    }

    @GetMapping
    @Operation(summary = "Получение списка пользователей")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/tg-id-volunteer")
    @Operation(summary = "Получение списка пользователей")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<String> findAllTgId(){
        return ResponseEntity.ok().body(userService.returnVolunteerTgId());
    }
}
