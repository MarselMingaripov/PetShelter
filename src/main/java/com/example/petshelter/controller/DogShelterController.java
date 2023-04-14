package com.example.petshelter.controller;

import com.example.petshelter.entity.shelter.DogShelter;
import com.example.petshelter.service.DogShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogShelter")
@RequiredArgsConstructor
@Tag(name = "API для работы с собачьим приютом",
        description = "Сохранение, изменение, получение, удаление данных собачьего приюта из БД")
public class DogShelterController {
    private final DogShelterService dogShelterService;

    @PostMapping
    @Operation(summary = "Сохранение собачьего приюта в БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные добавлены в БД")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogShelter> createDogShelter(@RequestBody DogShelter dogShelter) {
        return ResponseEntity.ok().body(dogShelterService.createDogShelter(dogShelter));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных собачьего приюта по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogShelter> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogShelterService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Получение списка собачьих приютов")
    public ResponseEntity<List<DogShelter>> findAll() {
        return ResponseEntity.ok().body(dogShelterService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных собачьего приюта по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Данных нет в БД или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogShelter> updateById(@PathVariable Long id, @RequestBody DogShelter dogShelter) {
        return ResponseEntity.ok().body(dogShelterService.updateById(id, dogShelter));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных собачьего приюта из БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogShelter> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogShelterService.deleteById(id));
    }
}


