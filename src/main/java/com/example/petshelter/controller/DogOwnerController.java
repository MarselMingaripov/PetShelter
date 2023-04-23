package com.example.petshelter.controller;

import com.example.petshelter.entity.DogOwner;
import com.example.petshelter.service.DogOwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogOwner")
@RequiredArgsConstructor
@Tag(name = "API по работе с усыновителями собак",
        description = "Сохранение, изменение, получение, удаление данных усыновителей собак из БД")
public class DogOwnerController {
    private final DogOwnerService dogOwnerService;

    @PostMapping
    @Operation(summary = "Сохранение усыновителя в БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные добавлены в БД")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogOwner> createDogOwner(@RequestBody DogOwner dogOwner) {
        return ResponseEntity.ok().body(dogOwnerService.createDogOwner(dogOwner));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных усыновителя собаки по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogOwner> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogOwnerService.findById(id));
    }

    @GetMapping("/find-by-phone-number")
    @Operation(summary = "Получение данных усыновителя собаки по номеру телефона")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogOwner> findByPhoneNumber(@RequestParam String phoneNumber) {
        return ResponseEntity.ok().body(dogOwnerService.findByPhoneNumber(phoneNumber));
    }

    @GetMapping
    @Operation(summary = "Получение списка усыновителей собак")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<List<DogOwner>> findAll() {
        return ResponseEntity.ok().body(dogOwnerService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных усыновителя собаки по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Данных нет в БД  или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogOwner> updateById(@PathVariable Long id, @RequestBody DogOwner dogOwner) {
        return ResponseEntity.ok().body(dogOwnerService.updateById(id, dogOwner));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных усыновителя собаки из БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogOwner> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogOwnerService.deleteById(id));
    }

}




