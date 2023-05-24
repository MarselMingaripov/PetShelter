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
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogShelter> createDogShelter(@RequestBody DogShelter dogShelter) {
        return ResponseEntity.ok().body(dogShelterService.createDogShelter(dogShelter));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных собачьего приюта по id")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogShelter> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogShelterService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Получение списка собачьих приютов")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    public ResponseEntity<List<DogShelter>> findAll() {
        return ResponseEntity.ok().body(dogShelterService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных собачьего приюта по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные изменены")
    @ApiResponse(responseCode = "404", description = "Данных нет в БД  или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogShelter> updateById(@PathVariable Long id, @RequestBody DogShelter dogShelter) {
        return ResponseEntity.ok().body(dogShelterService.updateById(id, dogShelter));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных собачьего приюта из БД")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, данные удалены")
    @ApiResponse(responseCode = "404", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DogShelter> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogShelterService.deleteById(id));
    }

    @GetMapping("/information/{id}")
    @Operation(summary = "Получение информации о приюте")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnInformation(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogShelterService.returnInformation(id));
    }

    @GetMapping("/address-and-work-schedule/{id}")
    @Operation(summary = "Получение расписания работы приюта, адрес, схему проезда")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnAddressAndWorkSchedule(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogShelterService.returnAddressAndWorkSchedule(id));
    }

    @GetMapping("/phone_number/{id}")
    @Operation(summary = "Получение номера телефона приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnPhoneNumber(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogShelterService.returnPhone(id));
    }

    @GetMapping("/security-contacts/{id}")
    @Operation(summary = "Получить контактные данные охраны для оформления пропуска на машину")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnSecurityContacts(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogShelterService.returnSecurityContacts(id));
    }

    @GetMapping("/safety-recommendations/{id}")
    @Operation(summary = "Получить общие рекомендации о технике безопасности на территории приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnSafetyRecommendations(@PathVariable Long id) {
        return ResponseEntity.ok().body(dogShelterService.returnSafetyRecommendations(id));
    }

    @PostMapping("/add-dog-to-shelter")
    @Operation(summary = "Добавление собаки в БД приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addDogToShelter(@RequestParam String name) {
        dogShelterService.addDogToShelter(name);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-dog-owner-to-shelter")
    @Operation(summary = "Добавление опекуна собаки в БД приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "405", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addDogOwnerToShelter(@RequestParam String phoneNumber) {
        dogShelterService.addDogOwnerToShelter(phoneNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-dating")
    @Operation(summary = "Добавление в БД приюта информации о правилах знакомства с животными")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addDogConsultDating(@RequestParam String value,
                                                    @RequestParam Long id) {
        dogShelterService.addDating(id, value);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-documents")
    @Operation(summary = "Добавление в БД приюта информации о документах, необходимых для взятия животного из приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addDogConsultDocuments(@RequestParam String value,
                                                       @RequestParam Long id) {
        dogShelterService.addDocuments(id, value);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-transportation")
    @Operation(summary = "Добавление в БД приюта списка рекомендаций по транспортировке животного")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addDogConsultTransportation(@RequestParam String value,
                                                            @RequestParam Long id) {
        dogShelterService.addTransportation(id, value);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-arrangement-puppy")
    @Operation(summary = "Добавление в БД приюта списка рекомендаций по обустройству дома для щенка")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addDogConsultArrangementPuppy(@RequestParam String value,
                                                              @RequestParam Long id) {
        dogShelterService.addArrangementPuppy(id, value);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-arrangement-dog")
    @Operation(summary = "Добавление в БД приюта списка рекомендаций по обустройству дома для взрослого животного")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addDogConsultArrangementDog(@RequestParam String value,
                                                            @RequestParam Long id) {
        dogShelterService.addArrangementDog(id, value);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-arrangement-disabled")
    @Operation(summary = "Добавление в БД приюта списка рекомендаций по обустройству дома для животного с ограниченными возможностями")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addDogConsultArrangementDisabled(@RequestParam String value,
                                                                 @RequestParam Long id) {
        dogShelterService.addArrangementDisabled(id, value);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-dog-handler-recommendations")
    @Operation(summary = "Добавление в БД приюта советов кинолога по первичному общению с собакой")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addDogConsultDogHandlerRecommendations(@RequestParam String value,
                                                                       @RequestParam Long id) {
        dogShelterService.addDogHandlerRecommendations(id, value);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-recommended-dog-handlers")
    @Operation(summary = "Добавление в БД приюта информации по проверенным кинологам")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addDogConsultRecommendedDogHandlers(@RequestParam String value,
                                                                    @RequestParam Long id) {
        dogShelterService.addRecommendedDogHandlers(id, value);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-cancel-dog-taker")
    @Operation(summary = "Добавление в БД приюта причин отказа в передаче животного усыновителю")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> addDogConsultCancelDogTaker(@RequestParam String value,
                                                            @RequestParam Long id) {
        dogShelterService.addCancelDogTaker(id, value);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-dog-dating")
    @Operation(summary = "Получить информацию о правилах знакомства с животными")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnDating(@RequestParam Long id) {
        return ResponseEntity.ok().body(dogShelterService.returnDating(id));
    }

    @GetMapping("/get-dog-documents")
    @Operation(summary = "Получить список документов, необходимых для взятия животного из приюта")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnDocuments(@RequestParam Long id) {
        return ResponseEntity.ok().body(dogShelterService.returnDocuments(id));
    }

    @GetMapping("/get-dog-transportation")
    @Operation(summary = "Получить список рекомендаций по транспортировке животного")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnTransportation(@RequestParam Long id) {
        return ResponseEntity.ok().body(dogShelterService.returnTransportation(id));
    }

    @GetMapping("/get-dog-arrangement-puppy")
    @Operation(summary = "Получить список рекомендаций по обустройству дома для щенка")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnArrangementPuppy(@RequestParam Long id) {
        return ResponseEntity.ok().body(dogShelterService.returnArrangementPuppy(id));
    }

    @GetMapping("/get-dog-arrangement-dog")
    @Operation(summary = "Получить список рекомендаций по обустройству дома для взрослого животного")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnArrangementDog(@RequestParam Long id) {
        return ResponseEntity.ok().body(dogShelterService.returnArrangementDog(id));
    }

    @GetMapping("/get-dog-arrangement-disabled")
    @Operation(summary = "Получить список рекомендаций по обустройству дома для животного с ограниченными возможностями")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnArrangementDisabled(@RequestParam Long id) {
        return ResponseEntity.ok().body(dogShelterService.returnArrangementDisabled(id));
    }


    @GetMapping("/get-dog-handler-recommendations")
    @Operation(summary = "Получить список советов кинолога по первичному общению с собакой")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> returnDogConsultDogHandlerRecommendations(@RequestParam Long id) {
        dogShelterService.returnDogHandlerRecommendations(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-recommended-dog-handlers")
    @Operation(summary = "Получить информацию по проверенным кинологам")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Void> getDogConsultRecommendedDogHandlers(@RequestParam Long id) {
        dogShelterService.returnRecommendedDogHandlers(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-cancel-dog-taker")
    @Operation(summary = "Получить список причин отказа в передаче животного усыновителю")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен, данные получены")
    @ApiResponse(responseCode = "404", description = "Не найден по ид")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<String> getDogConsultCancelDogTaker(@RequestParam Long id) {
        dogShelterService.returnCancelDogTaker(id);
        return ResponseEntity.ok().build();
    }

}


