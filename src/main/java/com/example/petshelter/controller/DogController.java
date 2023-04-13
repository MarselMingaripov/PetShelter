package com.example.petshelter.controller;

import com.example.petshelter.entity.Dog;
import com.example.petshelter.service.DogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dog")
@Tag(name = "API по хранению данных собак",
        description = "Регистрация поступивших в приют, " +
                "находящихся на испытательном сроке, " +
                "нашедших своих хозяев собак")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    @Operation(summary = "Регистрация собак поступивших  в приют")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, собака добавлена в БД")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        dogService.createDog(dog);
        return ResponseEntity.ok(dog);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных собаки по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> findById(@PathVariable Long id) {
        Dog dog = dogService.findById(id);
        return ResponseEntity.ok(dog);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Получение данных собаки по имени")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> findByName(@PathVariable String name) {
        Dog dog = dogService.findByName(name);
        return ResponseEntity.ok(dog);
    }

    @GetMapping
    @Operation(summary = "Получение списка данных собак в приюте")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<Dog>> findAll() {
        return ResponseEntity.ok(dogService.findAll());
    }

    @PutMapping
    @Operation(summary = "Изменение данных собаки по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "400", description = "Данного кота нет в БД или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> updateById(@PathVariable Long id, @RequestBody Dog dog) {
        return ResponseEntity.ok(dogService.updateById(id, dog));
    }

    @DeleteMapping
    @Operation(summary = "Удаление данных собаки из БД приюта")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "200", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(dogService.deleteById(id));
    }
}



