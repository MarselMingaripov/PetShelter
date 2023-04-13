package com.example.petshelter.controller;

import com.example.petshelter.entity.DogOwner;
import com.example.petshelter.service.DogOwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogOwner")
@Tag(name = "API по хранению данных хозяев собак",
        description = "Регистрация желающих стать хозяином собаки из приюта, " +
                "находящихся на испытательном сроке, " +
                "нашедших своих собак хозяева")
public class DogOwnerController {
    private final DogOwnerService dogOwnerService;

    public DogOwnerController (DogOwnerService dogOwnerService) {
        this.dogOwnerService = dogOwnerService;
    }

    @PostMapping
    @Operation(summary = "Регистрация потенциальных хозяев")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные добавлены в БД")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogOwner> createDogOwner(@RequestBody DogOwner dogOwner) {
        dogOwnerService.createDogOwner(dogOwner);
        return ResponseEntity.ok(dogOwner);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных хозяина собаки по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogOwner> findById(@PathVariable Long id) {
        DogOwner dogOwner = dogOwnerService.findById(id);
        return ResponseEntity.ok(dogOwner);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Получение данных хозяина кота по номеру телефона")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogOwner> findByPhoneNumber(@PathVariable String name) {
        DogOwner dogOwner = dogOwnerService.findByPhoneNumber(name);
        return ResponseEntity.ok(dogOwner);
    }

    @GetMapping
    @Operation(summary = "Получение списка данных хозяев собак")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<DogOwner>> findAll() {
        return ResponseEntity.ok(dogOwnerService.findAll());
    }

    @PutMapping
    @Operation(summary = "Изменение данных хозяина собаки по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "400", description = "Данных нет в БД или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogOwner> updateById(@PathVariable Long id, @RequestBody DogOwner dogOwner) {
        return ResponseEntity.ok(dogOwnerService.updateById(id, dogOwner));
    }

    @DeleteMapping
    @Operation(summary = "Удаление данных хозяина собаки из БД приюта")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "200", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogOwner> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(dogOwnerService.deleteById(id));
    }
}




