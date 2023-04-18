package com.example.petshelter.service;

import com.example.petshelter.entity.User;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.UserRepository;
import com.example.petshelter.service.impl.UserServiceImpl;
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
public class UserServiceTest {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;

    @InjectMocks
    private UserServiceImpl userServiceOut;

    private static Long ID = 1L;
    private static String PHONE_NUMBER = "+79053930303";
    private static String TRIAL_PERIODS_IN_ACTIVE_STATUS = null;
    private static String TRIAL_PERIODS_COMPLETED = null;
    private User user;

    @BeforeEach
    public void init() {
        user = new User(PHONE_NUMBER);
    }

    @Test
    @DisplayName("Проверка корректного создания нового пользователя")
    public void shouldReturnWhenCreateNewUser() {

        Mockito.when(validationServiceMock.validatePhoneNumber(any())).thenReturn(true);
        Mockito.when(userRepositoryMock.save(any())).thenReturn(user);
        assertEquals(user, userServiceOut.createUser(user));
    }

    @Test
    @DisplayName("Исключение при вводе некорректного номера телефона")
    public void shouldThrowValidationExceptionWhenPhoneNumberIsNotValid() {

        Mockito.when(validationServiceMock.validatePhoneNumber(any())).thenReturn(false);
        assertThrows(ValidationException.class, () -> userServiceOut.createUser(user));
    }
    @Test
    @DisplayName("Поиск пользователя по его Id")
    public void shouldFindUserById() {
        Mockito.when(userRepositoryMock.findById(ID)).thenReturn(Optional.of(user));
        assertEquals(user, userServiceOut.findById(ID));
    }
    @Test
    @DisplayName("Исключение при поиске пользователя по некорректному ID")
    public void shouldThrowNotFoundInBdExceptionWhenIdIsNotValid() {
        Mockito.when(userRepositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> userServiceOut.findById(ID));
    }

    @Test
    @DisplayName("Поиск и обновление пользователя по его Id")
    public void shouldFindAndUpdateCorrectUser() {
        Mockito.when(userRepositoryMock.findById(ID)).thenReturn(Optional.of(user));
        Mockito.when(userRepositoryMock.save(any())).thenReturn(user);
        assertEquals(user, userServiceOut.updateById(ID, user));
    }

    @Test
    @DisplayName("Исключение при обновлении по некорректному Id пользователя")
    public void shouldThrowNotFoundInBdExceptionWhenUpdateByIdIsNotValid() {
        Mockito.when(userRepositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> userServiceOut.updateById(ID, user));
    }
    @Test
    @DisplayName("Поиск пользователя по его Telegram Id")
    public void shouldFindUserByTelegramId() {
        Mockito.when(userRepositoryMock.findByTelegramId(ID)).thenReturn(Optional.of(user));
        assertEquals(user, userServiceOut.findByTelegramID(ID));
    }
    @Test
    @DisplayName("Исключение при поиске пользователя по некорректному Telegram Id")
    public void shouldThrowNotFoundInBdExceptionWhenTelegramIdIsNotValid() {
        Mockito.when(userRepositoryMock.findByTelegramId(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> userServiceOut.findByTelegramID(ID));
    }
}
