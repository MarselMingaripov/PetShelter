package com.example.petshelter.controller;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.service.CatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
@RequiredArgsConstructor
@Tag(name = "API по хранению данных котов",
        description = "Регистрация поступивших в приют, " +
                "находящихся на испытательном сроке, " +
                "нашедших своих хозяев котов")
public class CatController {
    private final CatService catService;

    @PostMapping
    @Operation(summary = "Регистрация котов поступивших  в приют")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, кот добавлен в БД")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
        return ResponseEntity.ok().body(catService.createCat(cat));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных кота по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Cat> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(catService.findById(id));
    }

    @GetMapping("/find-by-name/{name}")
    @Operation(summary = "Получение данных кота по имени")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Cat> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(catService.findByName(name));
    }

    @GetMapping
    @Operation(summary = "Получение списка данных котов в приюте")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<Cat>> findAll() {
        return ResponseEntity.ok(catService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных кота по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Данного кота нет в БД или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Cat> updateById(@PathVariable Long id, @RequestBody Cat cat) {
        return ResponseEntity.ok(catService.updateById(id, cat));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных кота из БД приюта")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "200", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Cat> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(catService.deleteById(id));
    }
}


