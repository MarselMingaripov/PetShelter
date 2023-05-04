package com.example.petshelter.controller;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.StatusAnimal;
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
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        return ResponseEntity.ok().body(dogService.createDog(dog));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных собаки по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogService.findById(id));
    }

    @GetMapping("/find-by-name")
    @Operation(summary = "Получение данных собаки по имени")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> findByName(@RequestParam String name) {
        return ResponseEntity.ok().body(dogService.findByName(name));
    }

    @GetMapping
    @Operation(summary = "Получение списка всех собак")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<List<Dog>> findAll() {
        return ResponseEntity.ok().body(dogService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных собаки по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Собаки с таким id нет в БД или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> updateById(@PathVariable Long id, @RequestBody Dog dog) {
        return ResponseEntity.ok().body(dogService.updateById(id, dog));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных собаки из БД приюта")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogService.deleteById(id));
    }

    @PostMapping("/reserve")
    @Operation(summary = "Бронирование собаки на 24 часа")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "404", description = "Собаки с таким именем нет в БД или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации полей")
    @ApiResponse(responseCode = "409", description = "Собака с таким именем уже забронирована")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> reserveDog(@RequestParam String name, @RequestParam String phoneNumber) {
        return ResponseEntity.ok().body(dogService.reserveDog(name, phoneNumber));
    }

    @GetMapping("/all-in-shelter")
    @Operation(summary = "Получение списка всех собак в приюте")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<List<Dog>> findAllInShelter() {
        return ResponseEntity.ok().body(dogService.findAllInShelter());
    }

    @GetMapping("/all-by-status")
    @Operation(summary = "Получение списка всех собак по их статусу")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<List<Dog>> findAllByStatus(@RequestParam StatusAnimal statusAnimal) {
        return ResponseEntity.ok().body(dogService.showAllByStatus(statusAnimal));
    }

    @PostMapping("/change-status")
    @Operation(summary = "Поменять статус собаки")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "404", description = "Собаки с таким именем нет в БД или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации полей")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Dog> changeStatus(@RequestParam String name, @RequestParam StatusAnimal statusAnimal) {
        return ResponseEntity.ok().body(dogService.changeStatusAnimal(name, statusAnimal));
    }


}



