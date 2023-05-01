package com.example.petshelter.service;

import com.example.petshelter.entity.*;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatOwnerRepository;
import com.example.petshelter.service.impl.CatOwnerServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CatOwnerServiceTest {
    @Mock
    private CatOwnerRepository catOwnerRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;


    @InjectMocks
    private CatOwnerServiceImpl catOwnerServiceOut;

    private static Long ID = 1L;
    private static String PHONE_NUMBER = "+79053930303";


    private static List<Report> REPORTS = List.of(new Report());
    private static List<TrialPeriod> TRIAL_PERIODS_IN_ACTIVE_STATUS = List.of(new TrialPeriod());
    private static List<TrialPeriod> TRIAL_PERIODS_COMPLETED = List.of(new TrialPeriod());
    private static List<Cat> CATS = List.of(new Cat());
    private static List<CatOwner> CAT_OWNER = List.of(new CatOwner());


    private CatOwner catOwner;
//    private User user;

    @BeforeEach
    public void init() {
        catOwner = new CatOwner(ID, PHONE_NUMBER, CATS, TRIAL_PERIODS_IN_ACTIVE_STATUS, TRIAL_PERIODS_COMPLETED);
//        user = new User(PHONE_NUMBER);
    }

    @Test
    @DisplayName("Проверка корректного создания владельца кошки")
    void shouldReturnWhenCreateNewCatOwner() {
        Mockito.when(validationServiceMock.validate(catOwner)).thenReturn(true);
        Mockito.when(catOwnerRepositoryMock.save(any())).thenReturn(catOwner);
        assertEquals(catOwner, catOwnerServiceOut.createCatOwner(catOwner));
    }

    @Test
    @DisplayName("Исключение при некорректной валидации владельца кошки")
    void shouldThrowValidationExceptionWhenValidateNotValid() {
        Mockito.when(validationServiceMock.validate(catOwner)).thenReturn(false);
        assertThrows(ValidationException.class, () -> catOwnerServiceOut.createCatOwner(catOwner));

    }

    @Test
    @DisplayName("Поиск владельца кошки по его Id")
    void shouldFindCatOwnerById() {
        Mockito.when(catOwnerRepositoryMock.findById(any())).thenReturn(Optional.of(catOwner));
        assertEquals(catOwner, catOwnerServiceOut.findById(ID));
    }

    @Test
    @DisplayName("Исключение при поиске владельца кошки по некорректному Id")
    void shouldThrowNotFoundInBdExceptionWhenIdCatOwnerIsNotValid() {
        Mockito.when(catOwnerRepositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> catOwnerServiceOut.findById(ID));
    }

    @Test
    @DisplayName("Поиск и обновление владельца кошки по его Id")
    public void shouldFindAndUpdateCorrectCatOwner() {
        Mockito.when(catOwnerRepositoryMock.findById(ID)).thenReturn(Optional.of(catOwner));
        Mockito.when(catOwnerRepositoryMock.save(any())).thenReturn(catOwner);
        assertEquals(catOwner, catOwnerServiceOut.updateById(ID, catOwner));
    }

    @Test
    @DisplayName("Исключение при обновлении владельца кошки по некорректному Id")
    public void shouldThrowNotFoundInBdExceptionWhenUpdateCatOwnerByIdIsNotValid() {
        Mockito.when(catOwnerRepositoryMock.findById(ID)).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> catOwnerServiceOut.updateById(ID, catOwner));
    }

    @Test
    @DisplayName("Поиск владельца кошки по его номеру телефона")
    void shouldFindCatOwnerByPhoneNumber() {
        Mockito.when(catOwnerRepositoryMock.findByPhoneNumber(PHONE_NUMBER)).thenReturn(Optional.of(catOwner));
        assertEquals(catOwner, catOwnerServiceOut.findByPhoneNumber(PHONE_NUMBER));
    }

    @Test
    @DisplayName("Исключение при поиске владельца кошки по некорректному номеру телефона")
    void shouldThrowNotFoundInBdExceptionWhenPhoneNumberCatOwnerIsNotValid() {
        Mockito.when(catOwnerRepositoryMock.findByPhoneNumber(PHONE_NUMBER)).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> catOwnerServiceOut.findByPhoneNumber(PHONE_NUMBER));
    }

    @Test
    @DisplayName("Проверка удаления владельца кошки")
    void shouldReturnWhenDeleteCatOwner() {
        Mockito.when(catOwnerRepositoryMock.findById(any())).thenReturn(Optional.of(catOwner));
        assertEquals(catOwner, catOwnerServiceOut.deleteById(ID));
    }

    //TODO: 27.04.2023 написать тест передачи кошки владельцу на испытательный срок
    @Test
    @DisplayName("Передача кошки опекуну на испытательный срок")
    void shouldTransferCatOnProbation() {
//        Mockito.when(catOwnerRepositoryMock.existsByPhoneNumber(PHONE_NUMBER)).thenReturn(false);
//        Mockito.when(catOwnerRepositoryMock.findByPhoneNumber(PHONE_NUMBER)).thenReturn(Optional.empty());
//
//        assertEquals(catOwner, userServiceOut.findByPhone(PHONE_NUMBER));
//        Mockito.when(userRepositoryMock.save(any())).thenReturn(user);
//        assertEquals(user, userServiceOut.createUser(user));
//        assertThrows(NotFoundInBdException.class,()->catOwnerServiceOut.findById(ID));
    }

}