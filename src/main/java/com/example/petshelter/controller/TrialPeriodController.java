package com.example.petshelter.controller;

import com.example.petshelter.entity.TrialPeriod;
import com.example.petshelter.service.TrialPeriodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trial_period")
@RequiredArgsConstructor
@Tag(name = "API для работы с испытательным сроком",
        description = "Сохранение, изменение, получение, удаление испытательных сроков из БД")
public class TrialPeriodController {

    private final TrialPeriodService trialPeriodService;

    @PostMapping
    @Operation(summary = "Сохранение испытательного срока в БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные добавлены в БД")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<TrialPeriod> createTrialPeriod(@RequestBody TrialPeriod trialPeriod){
        return ResponseEntity.ok().body(trialPeriodService.createTrialPeriod(trialPeriod));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение испытательного срока по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<TrialPeriod> findTrialPeriod(@PathVariable Long id){
        return ResponseEntity.ok().body(trialPeriodService.findById(id));
    }

    @PostMapping("/{id}")
    @Operation(summary = "Изменение испытательного срока по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<TrialPeriod> updateTrialPeriod(@PathVariable Long id, @RequestBody TrialPeriod trialPeriod){
        return ResponseEntity.ok().body(trialPeriodService.updateById(id, trialPeriod));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление испытательного срока из БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<TrialPeriod> deleteReport(@PathVariable Long id){
        return ResponseEntity.ok().body(trialPeriodService.deleteById(id));
    }

    @GetMapping
    @Operation(summary = "Получение списка испытательных сроков")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<List<TrialPeriod>> findAll(){
        return ResponseEntity.ok().body(trialPeriodService.findAll());
    }
}
