package com.example.petshelter.controller;

import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.service.CatOwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catOwner")
@RequiredArgsConstructor
@Tag(name = "API по работе с усыновителями котов",
        description = "Сохранение, изменение, получение, удаление данных усыновителей котов из БД")

public class CatOwnerController {
    private final CatOwnerService catOwnerService;

    @PostMapping
    @Operation(summary = "Сохранение усыновителя в БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, усыновитель добавлен в БД")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatOwner> createCatOwner(@RequestBody CatOwner catOwner) {
        return ResponseEntity.ok().body(catOwnerService.createCatOwner(catOwner));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных усыновителя кота по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatOwner> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(catOwnerService.findById(id));
    }

    @GetMapping("/find-by-phone-number/{phoneNumber}")
    @Operation(summary = "Получение данных усыновителя кота по номеру телефона")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatOwner> findByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok().body(catOwnerService.findByPhoneNumber(phoneNumber));
    }

    @GetMapping
    @Operation(summary = "Получение списка усыновителей котов")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<List<CatOwner>> findAll() {
        return ResponseEntity.ok().body(catOwnerService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных усыновителя кота по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Данных нет в БД  или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatOwner> updateById(@PathVariable Long id, @RequestBody CatOwner catOwner) {
        return ResponseEntity.ok().body(catOwnerService.updateById(id, catOwner));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных усыновителя кота из БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatOwner> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(catOwnerService.deleteById(id));
    }
}



