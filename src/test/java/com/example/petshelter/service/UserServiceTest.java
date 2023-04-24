package com.example.petshelter.service;

import com.example.petshelter.entity.Report;
import com.example.petshelter.entity.User;
import com.example.petshelter.entity.RoleSt;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    private static Long TELEGRAM_ID = 123L;
    private static String TRIAL_PERIODS_IN_ACTIVE_STATUS = null;
    private static String TRIAL_PERIODS_COMPLETED = null;
    private User user;
    private static List<User> USERS = List.of(new User());

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
        verify(userRepositoryMock, times(1)).save(any());
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
    @DisplayName("Поиск пользователя по его номеру телефона")
    public void shouldFindUserByPhoneNumber() {
        User user1 = new User(ID, PHONE_NUMBER, TELEGRAM_ID);
        Mockito.when(userRepositoryMock.existsByPhoneNumber(any())).thenReturn(true);
        Mockito.when(userRepositoryMock.findByPhoneNumber(PHONE_NUMBER)).thenReturn(Optional.of(user1));
        assertEquals(user1, userServiceOut.findByPhone(PHONE_NUMBER));

    }

    @Test
    @DisplayName("Поиск пользователя по его Telegram Id")
    public void shouldFindUserByTelegramId() {
        Mockito.when(userRepositoryMock.findByTelegramId(ID)).thenReturn(Optional.of(user));
        assertEquals(user, userServiceOut.findByTelegramID(ID));
        verify(userRepositoryMock, times(1)).findByTelegramId(any());
    }

    @Test
    @DisplayName("Исключение при поиске пользователя по некорректному Telegram Id")
    public void shouldThrowNotFoundInBdExceptionWhenTelegramIdIsNotValid() {
        Mockito.when(userRepositoryMock.findByTelegramId(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> userServiceOut.findByTelegramID(ID));
    }

    @Test
    @DisplayName("Удаление пользователя по его Id")
    public void shouldDeleteUserById() {
        Mockito.when(userRepositoryMock.findById(ID)).thenReturn(Optional.of(user));
//        Mockito.when(userRepositoryMock.save(any())).thenReturn(user);
        assertEquals(user, userServiceOut.deleteById(ID));
    }


    @Test
    @DisplayName("Вывод списка всех пользователей")
    public void shouldFindAllUsers() {
        Mockito.when(userRepositoryMock.findAll()).thenReturn(USERS);
        assertEquals(USERS, userServiceOut.findAll());
    }

    @Test
    @DisplayName("Вывод TelegramId волонтера")
    public void shouldReturnVolunteerTgId() {

        User volunteerUser = new User();

        volunteerUser.setRoleSt(RoleSt.VOLUNTEER);
        volunteerUser.setTelegramId(TELEGRAM_ID);

        User simpleUser = new User();
        simpleUser.setRoleSt(RoleSt.USER);
        simpleUser.setTelegramId(4321L);

        List<User> fakeUsers = Arrays.asList(volunteerUser, simpleUser);

        Mockito.when(userRepositoryMock.findAll()).thenReturn(fakeUsers);
        List<Long> volunteerTgIdList = userServiceOut.returnVolunteerTgId();
        Mockito.verify(userRepositoryMock, Mockito.times(1)).findAll();
        assertEquals(1, volunteerTgIdList.size());
        assertEquals(volunteerUser.getTelegramId(), volunteerTgIdList.get(0));
    }

    @Test
    @DisplayName("Создание пользователя из TbB")
    public void shouldCreateUserFromTgB() {

        User user1 = new User("+79050505055", TELEGRAM_ID);

        Mockito.when(validationServiceMock.validatePhoneNumber(any())).thenReturn(true);
        Mockito.when(userRepositoryMock.existsByPhoneNumber(PHONE_NUMBER)).thenReturn(false);
        Mockito.when(userRepositoryMock.save(any())).thenReturn(user1);
        assertEquals(user1, userServiceOut.createUserFromTgB("+79053930303 12354"));
    }

    @Test
    @DisplayName("Создание пользователя из TbB когда номер есть в базе")
    public void shouldCreateUserFromTgBForExistingPhoneNumber() throws ValidationException {
        String text = "+79053930303 12354";

        User user1 = new User(PHONE_NUMBER, TELEGRAM_ID);
        User existingUser = new User(PHONE_NUMBER, 88888888L);
        Mockito.when(validationServiceMock.validatePhoneNumber(UserServiceTest.PHONE_NUMBER)).thenReturn(true);
        Mockito.when(userRepositoryMock.existsByPhoneNumber(PHONE_NUMBER)).thenReturn(true);
        Mockito.when(userRepositoryMock.findByPhoneNumber(PHONE_NUMBER)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepositoryMock.save(any())).thenReturn(user1);
        assertEquals(user1,userServiceOut.createUserFromTgB(text));

    }

    @Test
    @DisplayName("Исключение при вводе некорректного номера телефона из TgB")
    public void shouldThrowValidationExceptionWhenPhoneNumberFromTgBIsNotValid() {
        String text = "+79053930303 12354";
        Mockito.when(validationServiceMock.validatePhoneNumber(any())).thenReturn(false);
        assertThrows(ValidationException.class, () -> userServiceOut.createUserFromTgB(text));
    }
}
