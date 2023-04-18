package com.example.petshelter.service;

import com.example.petshelter.entity.*;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.DogOwnerRepository;
import com.example.petshelter.repository.UserRepository;
import com.example.petshelter.service.impl.DogOwnerServiceImpl;
import com.example.petshelter.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DogOwnerServiceTest {
    @Mock
    private DogOwnerRepository dogOwnerRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;

    private UserRepository userRepositoryMock;

    @InjectMocks
    private DogOwnerServiceImpl dogOwnerServiceOut;
    @InjectMocks
    private UserServiceImpl userServiceOut;

    private static Long ID = 1L;
    private static String PHONE_NUMBER = "+79053930303";


    private static List<Report> REPORTS = List.of(new Report());
    private static List<TrialPeriod> TRIAL_PERIODS_IN_ACTIVE_STATUS = List.of(new TrialPeriod());
    private static List<TrialPeriod> TRIAL_PERIODS_COMPLETED = List.of(new TrialPeriod());
    //            "Vasya", LocalDate.now(),LocalDate.now(), REPORTS, TrialPeriodResult.SUCCESS));
    private static List<Dog> DOGS = List.of(new Dog());
    //            "Barsik",5, true,true, StatusAnimal.IN_THE_SHELTER ));
    private static List<DogOwner> DOG_OWNER = List.of(new DogOwner());
//    ID,PHONE_NUMBER, CATS, TRIAL_PERIODS_IN_ACTIVE_STATUS,TRIAL_PERIODS_COMPLETED ));


    private DogOwner dogOwner;
    private User user;

    @BeforeEach
    public void init() {
        dogOwner = new DogOwner(ID, PHONE_NUMBER, DOGS, TRIAL_PERIODS_IN_ACTIVE_STATUS, TRIAL_PERIODS_COMPLETED);
        user = new User(PHONE_NUMBER);
    }

    @Test
    @DisplayName("Проверка корректного создания владельца собаки")
    void shouldReturnWhenCreateNewDogOwner() {
        Mockito.when(validationServiceMock.validate(dogOwner)).thenReturn(true);
        Mockito.when(dogOwnerRepositoryMock.save(any())).thenReturn(dogOwner);
        assertEquals(dogOwner, dogOwnerServiceOut.createDogOwner(dogOwner));
    }

    @Test
    @DisplayName("Исключение при некорректной валидации владельца собаки")
    void shouldThrowValidationExceptionWhenValidateNotValid() {
        Mockito.when(validationServiceMock.validate(dogOwner)).thenReturn(false);
        assertThrows(ValidationException.class, () -> dogOwnerServiceOut.createDogOwner(dogOwner));

    }

    @Test
    @DisplayName("Поиск владельца собаки по его Id")
    void shouldFindDogOwnerById() {
        Mockito.when(dogOwnerRepositoryMock.findById(any())).thenReturn(Optional.of(dogOwner));
        assertEquals(dogOwner, dogOwnerServiceOut.findById(ID));
    }

    @Test
    @DisplayName("Исключение при поиске владельца собаки по некорректному Id")
    void shouldThrowNotFoundInBdExceptionWhenIdDogOwnerIsNotValid() {
        Mockito.when(dogOwnerRepositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> dogOwnerServiceOut.findById(ID));
    }

    @Test
    @DisplayName("Поиск и обновление владельца собаки по его Id")
    public void shouldFindAndUpdateCorrectCatOwner() {
        Mockito.when(dogOwnerRepositoryMock.findById(ID)).thenReturn(Optional.of(dogOwner));
        Mockito.when(dogOwnerRepositoryMock.save(any())).thenReturn(dogOwner);
        assertEquals(dogOwner, dogOwnerServiceOut.updateById(ID, dogOwner));
    }

    @Test
    @DisplayName("Исключение при обновлении владельца собаки по некорректному Id")
    public void shouldThrowNotFoundInBdExceptionWhenUpdateDogOwnerByIdIsNotValid() {
        Mockito.when(dogOwnerRepositoryMock.findById(ID)).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> dogOwnerServiceOut.updateById(ID, dogOwner));
    }

    @Test
    @DisplayName("Поиск владельца собаки по его номеру телефона")
    void shouldFindDogOwnerByPhoneNumber() {
        Mockito.when(dogOwnerRepositoryMock.findByPhoneNumber(PHONE_NUMBER)).thenReturn(Optional.of(dogOwner));
        assertEquals(dogOwner, dogOwnerServiceOut.findByPhoneNumber(PHONE_NUMBER));
    }

    @Test
    @DisplayName("Исключение при поиске владельца собаки по некорректному номеру телефона")
    void shouldThrowNotFoundInBdExceptionWhenPhoneNumberDogOwnerIsNotValid() {
        Mockito.when(dogOwnerRepositoryMock.findByPhoneNumber(PHONE_NUMBER)).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> dogOwnerServiceOut.findByPhoneNumber(PHONE_NUMBER));
    }

    @Test
    @DisplayName("Передача собаки опекуну на испытательный срок")
    void shouldTransferDogOnProbation() {
//        Mockito.when(catOwnerRepositoryMock.existsByPhoneNumber(PHONE_NUMBER)).thenReturn(false);

//        Mockito.when(userRepositoryMock.findByPhoneNumber(PHONE_NUMBER)).thenReturn(Optional.empty());
//        assertEquals(dogOwner, userServiceOut.findByPhone(PHONE_NUMBER));
//        Mockito.when(userRepositoryMock.save(any())).thenReturn(user);
//        assertEquals(user, userServiceOut.createUser(user));
//        assertThrows(NotFoundInBdException.class,()->catOwnerServiceOut.findById(ID));
    }

}