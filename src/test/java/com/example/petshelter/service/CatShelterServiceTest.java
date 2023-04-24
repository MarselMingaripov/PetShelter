package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatRepository;
import com.example.petshelter.repository.CatShelterRepository;
import com.example.petshelter.service.impl.CatServiceImpl;
import com.example.petshelter.service.impl.CatShelterServiceImpl;
import com.sun.xml.bind.v2.TODO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CatShelterServiceTest {

    @Mock
    private CatShelterRepository catShelterRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;
    @Mock
    private CatRepository catRepositoryMock;

    @InjectMocks
    private CatShelterServiceImpl catShelterServiceOut;

    @InjectMocks
    private CatServiceImpl catServiceOut;

    private static Long ID = 1L;
    private static String INFORMATION = "information";
    private static String ADDRESS = "address";
    private static String PHONE_NUMBER = "+79053930303";
    private static String WORK_SCHEDULE = "always";
    private static String SECURITY_CONTACTS = "contacts";
    private static String SAFETY_RECOMMENDATIONS = "recommendation";
    private static String CAT_NAME = "Murzik";
    private CatShelter catShelter;

    @BeforeEach
    public void init() {
        catShelter = new CatShelter(INFORMATION, ADDRESS, PHONE_NUMBER, WORK_SCHEDULE, SECURITY_CONTACTS,
                SAFETY_RECOMMENDATIONS);
    }

    @Test
    @DisplayName("Проверка корректного создания приюта для кошек")
    void shouldReturnWhenCreateNewCatShelter() {
//        Mockito.when(validationServiceMock.validate(catShelter)).thenReturn(true);
        Mockito.when(catShelterRepositoryMock.save(any())).thenReturn(catShelter);
        assertEquals(catShelter, catShelterServiceOut.createCatShelter(catShelter));

    }

//    @Test
//    @DisplayName("Исключение при некорректной валидации приюта для кошек")
//    void shouldThrowValidationExceptionWhenValidateNotValid() {
//        Mockito.when(validationServiceMock.validate(catShelter)).thenReturn(false);
//        Mockito.when(catShelterRepositoryMock.save(any())).thenReturn(catShelter);
//        assertThrows(ValidationException.class, () -> catShelterServiceOut.createCatShelter(catShelter));
//
//    }

    @Test
    @DisplayName("Поиск и обновление приюта для кошек по его Id")
    public void shouldReturnWhenUpdateCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        Mockito.when(catShelterRepositoryMock.save(any())).thenReturn(catShelter);
        assertEquals(catShelter, catShelterServiceOut.updateById(ID, catShelter));

    }

    @Test
    @DisplayName("Исключение при обновлении приюта для кошек по некорректному Id")
    public void shouldThrowNotFoundInBdExceptionWhenUpdateCatShelterByIdIsNotValid() {
        Mockito.when(catShelterRepositoryMock.findById(ID)).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> catShelterServiceOut.updateById(ID, catShelter));
    }

    @Test
    @DisplayName("Удаление приюта для кошек по его Id")
    public void shouldReturnWhenDeleteCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
//        Mockito.when(catShelterRepositoryMock.deleteById(ID)).thenReturn(catShelter);
        assertEquals(catShelter, catShelterServiceOut.deleteById(ID));

    }


     //TODO: 21.04.2023 Исправить ошибку
//    @Test
//    @DisplayName("Проверка корректного добавления кошки в приют")
//    void shouldReturnWhenAddNewCatToShelter() {
//        Cat cat = new Cat(CAT_NAME, 5, true, true, StatusAnimal.IN_THE_SHELTER);
//        Mockito.when(validationServiceMock.validateString(CAT_NAME)).thenReturn(true);
//        Mockito.when(catRepositoryMock.save(any())).thenReturn(cat);
//        assertEquals(cat, catServiceOut.createCat(cat));
//
//    }

//    @Test
//    @DisplayName("Исключение при некорректной валидации приюта для кошек")
//    void shouldThrowValidationExceptionWhenValidateNotValid() {
//        Mockito.when(validationServiceMock.validate(catShelter)).thenReturn(false);
//        assertThrows(ValidationException.class, () -> catShelterServiceOut.createCatShelter(catShelter));
//
//    }
}