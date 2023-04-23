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
    @GetMapping("/information/{id}")
    @Operation(summary = "Получение информации о приюте")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnInformation(@PathVariable Long id) {
         return ResponseEntity.ok().body(catShelterService.returnInformation(id));
    }
    @GetMapping("/address-and-work-schedule/{id}")
    @Operation(summary = "Получение расписания работы приюта, адрес, схему проезда")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnAddressAndWorkSchedule(@PathVariable Long id) {
        return ResponseEntity.ok().body(catShelterService.returnAddressAndWorkSchedule(id));
    }
    @GetMapping("/phone_number/{id}")
    @Operation(summary = "Получение номера телефона приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnPhoneNumber(@PathVariable Long id) {
        return ResponseEntity.ok().body(catShelterService.returnPhone(id));
    }
    @GetMapping("/security-contacts/{id}")
    @Operation(summary = "Получить контактные данные охраны для оформления пропуска на машину")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnSecurityContacts(@PathVariable Long id) {
        return ResponseEntity.ok().body(catShelterService.returnSecurityContacts(id));
    }
    @GetMapping("/safety-recommendations/{id}")
    @Operation(summary = "Получить общие рекомендации о технике безопасности на территории приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnSafetyRecommendations(@PathVariable Long id) {
        return ResponseEntity.ok().body(catShelterService.returnSafetyRecommendations(id));
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
    @PostMapping("/add-dating")
    @Operation(summary = "Добавление информации для потенциального опекуна кота в БД приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addCatConsultDating(@RequestParam String value,
                                              @RequestParam Long id) {
        catShelterService.addDating(id, value);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-documents")
    @Operation(summary = "Добавление информации для потенциального опекуна кота в БД приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addCatConsultDocuments(@RequestParam String value,
                                              @RequestParam Long id) {
        catShelterService.addDocuments(id, value);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-transportation")
    @Operation(summary = "Добавление информации для потенциального опекуна кота в БД приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addCatConsultTransportation(@RequestParam String value,
                                              @RequestParam Long id) {
        catShelterService.addTransportation(id, value);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-arrangement-kitten")
    @Operation(summary = "Добавление информации для потенциального опекуна кота в БД приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addCatConsultArrangementKitten(@RequestParam String value,
                                              @RequestParam Long id) {
        catShelterService.addArrangementKitten(id, value);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-arrangement-cat")
    @Operation(summary = "Добавление информации для потенциального опекуна кота в БД приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addCatConsultArrangementCat(@RequestParam String value,
                                              @RequestParam Long id) {
        catShelterService.addArrangementCat(id, value);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-arrangement-disabled")
    @Operation(summary = "Добавление информации для потенциального опекуна кота в БД приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addCatConsultArrangementDisabled(@RequestParam String value,
                                              @RequestParam Long id) {
        catShelterService.addArrangementDisabled(id, value);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-cat-dating")
    @Operation(summary = "Получить консультацию")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnDating(@RequestParam Long id) {
        return ResponseEntity.ok().body(catShelterService.returnDating(id));
    }
    @GetMapping("/get-cat-documents")
    @Operation(summary = "Получить консультацию")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnDocuments(@RequestParam Long id) {
        return ResponseEntity.ok().body(catShelterService.returnDocuments(id));
    }
    @GetMapping("/get-cat-transportation")
    @Operation(summary = "Получить консультацию")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnTransportation(@RequestParam Long id) {
        return ResponseEntity.ok().body(catShelterService.returnTransportation(id));
    }
    @GetMapping("/get-cat-arrangement-kitten")
    @Operation(summary = "Получить консультацию")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnArrangementKitten(@RequestParam Long id) {
        return ResponseEntity.ok().body(catShelterService.returnArrangementKitten(id));
    }
    @GetMapping("/get-cat-arrangement-cat")
    @Operation(summary = "Получить консультацию")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnArrangementCat(@RequestParam Long id) {
        return ResponseEntity.ok().body(catShelterService.returnArrangementCat(id));
    }
    @GetMapping("/get-cat-arrangement-disabled")
    @Operation(summary = "Получить консультацию")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnArrangementDisabled(@RequestParam Long id) {
        return ResponseEntity.ok().body(catShelterService.returnArrangementDisabled(id));
    }
}



