package com.example.petshelter.service;

import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.entity.shelter.DogShelter;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatShelterRepository;
import com.example.petshelter.repository.DogShelterRepository;
import com.example.petshelter.service.impl.CatShelterServiceImpl;
import com.example.petshelter.service.impl.DogShelterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DogShelterServiceTest {

    @Mock
    private DogShelterRepository dogShelterRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;
    @InjectMocks
    private DogShelterServiceImpl dogShelterServiceOut;

    private static Long ID = 1L;
    private static String INFORMATION = "information";
    private static String ADDRESS = "address";
    private static String PHONE_NUMBER = "+79053930303";
    private static String WORK_SCHEDULE = "always";
    private static String SECURITY_CONTACTS = "contacts";
    private static String SAFETY_RECOMMENDATIONS = "recommendation";

    private DogShelter dogShelter;

    // TODO: 19.04.2023 Убрать наследование метод DogShelter ot AnimalShelter, изменить конструктор
    @BeforeEach
    public void init() {
        dogShelter = new DogShelter(INFORMATION, ADDRESS, PHONE_NUMBER, WORK_SCHEDULE, SECURITY_CONTACTS,
                SAFETY_RECOMMENDATIONS);
    }

    @Test
    @DisplayName("Проверка корректного создания приюта для собак")
    void shouldReturnWhenCreateNewDogShelter() {
        Mockito.when(validationServiceMock.validate(dogShelter)).thenReturn(true);
        Mockito.when(dogShelterRepositoryMock.save(any())).thenReturn(dogShelter);
        assertEquals(dogShelter, dogShelterServiceOut.createDogShelter(dogShelter));

    }

    @Test
    @DisplayName("Исключение при некорректной валидации приюта для собак")
    void shouldThrowValidationExceptionWhenValidateNotValid() {
        Mockito.when(validationServiceMock.validate(dogShelter)).thenReturn(false);
        assertThrows(ValidationException.class, () -> dogShelterServiceOut.createDogShelter(dogShelter));

    }

    @Test
    @DisplayName("Поиск и обновление приюта для собак по его Id")
    public void shouldReturnWhenUpdateDogShelter() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        Mockito.when(dogShelterRepositoryMock.save(any())).thenReturn(dogShelter);
        assertEquals(dogShelter, dogShelterServiceOut.updateById(ID, dogShelter));

    }
    @Test
    @DisplayName("Исключение при обновлении приюта для собак по некорректному Id")
    public void shouldThrowNotFoundInBdExceptionWhenUpdateDogShelterByIdIsNotValid() {
        Mockito.when(dogShelterRepositoryMock.findById(ID)).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> dogShelterServiceOut.updateById(ID, dogShelter));
    }
}