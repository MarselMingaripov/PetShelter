package com.example.petshelter.controller;

import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.service.CatShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catShelter")
@RequiredArgsConstructor
@Tag(name = "API для работы с кошачьим приютом",
        description = "Сохранение, изменение, получение, удаление данных кошачьего приюта из БД")
public class CatShelterController {
    private final CatShelterService catShelterService;

    @PostMapping
    @Operation(summary = "Сохранение кошачьего приюта в БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные добавлены в БД")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatShelter> createCatShelter(@RequestBody CatShelter catShelter) {
        return ResponseEntity.ok().body(catShelterService.createCatShelter(catShelter));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных кошачьего приюта по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatShelter> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(catShelterService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Получение списка кошачьих приютов")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<List<CatShelter>> findAll() {
        return ResponseEntity.ok().body(catShelterService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных кошачьего приюта по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Данных нет в БД  или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatShelter> updateById(@PathVariable Long id, @RequestBody CatShelter catShelter) {
        return ResponseEntity.ok().body(catShelterService.updateById(id, catShelter));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных кошачьего приюта из БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatShelter> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(catShelterService.deleteById(id));
    }
    @GetMapping("/information")
    @Operation(summary = "Получение информации о приюте")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnInformation() {
         return ResponseEntity.ok().body(catShelterService.returnInformation());
    }
    @GetMapping("/address-and-work-schedule")
    @Operation(summary = "Получение расписания работы приюта, адрес, схему проезда")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnAddressAndWorkSchedule() {
        return ResponseEntity.ok().body(catShelterService.returnAddressAndWorkSchedule());
    }
    @GetMapping("/security-contacts")
    @Operation(summary = "Получить контактные данные охраны для оформления пропуска на машину")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnSecurityContacts() {
        return ResponseEntity.ok().body(catShelterService.returnSecurityContacts());
    }
    @GetMapping("/safety-recommendations")
    @Operation(summary = "Получить общие рекомендации о технике безопасности на территории приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnSafetyRecommendations() {
        return ResponseEntity.ok().body(catShelterService.returnSafetyRecommendations());
    }

    @PostMapping("/add-cat-to-shelter")
    @Operation(summary = "Добавление кота в БД приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addCatToShelter(@RequestParam String name) {
        catShelterService.addCatToShelter(name);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-cat-owner-to-shelter")
    @Operation(summary = "Добавление опекуна кота в БД приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addCatOwnerToShelter(@RequestParam String phoneNumber) {
        catShelterService.addCatOwnerToShelter(phoneNumber);
        return ResponseEntity.ok().build();
    }
}



