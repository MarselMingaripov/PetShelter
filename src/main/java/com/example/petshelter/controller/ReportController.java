package com.example.petshelter.controller;

import com.example.petshelter.entity.Report;
import com.example.petshelter.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@Tag(name = "API для работы с отчетами",
        description = "Сохранение, изменение, получение, удаление отчетов из БД")
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    @Operation(summary = "Сохранение отчета в БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные добавлены в БД")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Report> createReport(@RequestBody Report report){
        return ResponseEntity.ok().body(reportService.createReport(report));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение отчета по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Report> findReport(@PathVariable Long id){
        return ResponseEntity.ok().body(reportService.findById(id));
    }

    @PostMapping("/{id}")
    @Operation(summary = "Изменение отчета по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Данных нет в БД или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report report){
        return ResponseEntity.ok().body(reportService.updateById(id, report));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление отчета из БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Report> deleteReport(@PathVariable Long id){
        return ResponseEntity.ok().body(reportService.deleteById(id));
    }

    @GetMapping
    @Operation(summary = "Получение списка отчетов")
    public ResponseEntity<List<Report>> findAll(){
        return ResponseEntity.ok().body(reportService.findAll());
    }
}
