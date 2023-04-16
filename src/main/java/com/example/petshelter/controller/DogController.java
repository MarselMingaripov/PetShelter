package com.example.petshelter.controller;

import com.example.petshelter.entity.Dog;
import com.example.petshelter.service.DogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dog")
@RequiredArgsConstructor
@Tag(name = "API по для работы с собаками",
        description = "Сохранение, изменение, получение, удаление данных собак из БД")

public class DogController {
    private final DogService dogService;

    @PostMapping
    @Operation(summary = "Сохранение собак в БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, собака добавлена в БД")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        return ResponseEntity.ok().body(dogService.createDog(dog));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных собаки по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogService.findById(id));
    }

    @GetMapping("/find-by-name")
    @Operation(summary = "Получение данных собаки по имени")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> findByName(@RequestParam String name) {
        return ResponseEntity.ok().body(dogService.findByName(name));
    }

    @GetMapping
    @Operation(summary = "Получение списка собак в приюте")
    public ResponseEntity<List<Dog>> findAll() {
        return ResponseEntity.ok().body(dogService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных собаки по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Собаки с таким id нет в БД или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> updateById(@PathVariable Long id, @RequestBody Dog dog) {
        return ResponseEntity.ok().body(dogService.updateById(id, dog));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных собаки из БД приюта")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogService.deleteById(id));
    }
}



