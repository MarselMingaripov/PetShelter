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
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatShelter> createCatShelter(@RequestBody CatShelter catShelter) {
        return ResponseEntity.ok().body(catShelterService.createCatShelter(catShelter));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных кошачьего приюта по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatShelter> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(catShelterService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Получение списка кошачьих приютов")
    public ResponseEntity<List<CatShelter>> findAll() {
        return ResponseEntity.ok().body(catShelterService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных кошачьего приюта по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Данных нет в БД или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatShelter> updateById(@PathVariable Long id, @RequestBody CatShelter catShelter) {
        return ResponseEntity.ok().body(catShelterService.updateById(id, catShelter));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных кошачьего приюта из БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<CatShelter> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(catShelterService.deleteById(id));
    }
}



