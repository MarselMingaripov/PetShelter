package com.example.petshelter.controller;

import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.service.CatOwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("catOwner")
@Tag(name = "API по хранению данных хозяев котов",
        description = "Регистрация желающих стать хозяином кота из приюта, " +
                "находящихся на испытательном сроке, " +
                "нашедших своих котов хозяева")
public class CatOwnerController {
    private final CatOwnerService catOwnerService;

    public CatOwnerController (CatOwnerService catOwnerService) {
        this.catOwnerService = catOwnerService;
    }

    @PostMapping
    @Operation(summary = "Регистрация потенциальных хозяев")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные добавлены в БД")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatOwner> createCatOwner(@RequestBody CatOwner catOwner) {
        catOwnerService.createCatOwner(catOwner);
        return ResponseEntity.ok(catOwner);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных хозяина кота по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatOwner> findById(@PathVariable Long id) {
        CatOwner catOwner = catOwnerService.findById(id);
        return ResponseEntity.ok(catOwner);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Получение данных хозяина кота по номеру телефона")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatOwner> findByPhoneNumber(@PathVariable String phoneNumber) {
        CatOwner catOwner = catOwnerService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(catOwner);
    }

    @GetMapping
    @Operation(summary = "Получение списка данных хозяев котов")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<CatOwner>> findAll() {
        return ResponseEntity.ok(catOwnerService.findAll());
    }

    @PutMapping
    @Operation(summary = "Изменение данных хозяина кота по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "400", description = "Данных нет в БД или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatOwner> updateById(@PathVariable Long id, @RequestBody CatOwner catOwner) {
        return ResponseEntity.ok(catOwnerService.updateById(id, catOwner));
    }

    @DeleteMapping
    @Operation(summary = "Удаление данных хозяина кота из БД приюта")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "200", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatOwner> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(catOwnerService.deleteById(id));
    }
}



