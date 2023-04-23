package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.CatRepository;
import com.example.petshelter.service.impl.CatServiceImpl;
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
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatServiceImplTest {
    private static final Cat VALID_CAT = new Cat("Барсик", 12, true, true, StatusAnimal.HAS_HOUSE);
    private static final Cat INVALID_CAT = new Cat("Жук", 0, true, true, StatusAnimal.IN_THE_SHELTER);

    @Mock
    CatRepository catRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;
    @InjectMocks
    CatServiceImpl catServiceOut;
    private static Long ID = 1L;

    private Cat cat;
    private static List<Cat> CAT = List.of(new Cat());
    @BeforeEach
    public void init() {
        cat = new Cat();
    }
    @Test
    @DisplayName("Проверка корректного создания нового котика")
    public void shouldReturnWhenCreateNewUser() {

        Mockito.when(validationServiceMock.validatePhoneNumber(any())).thenReturn(true);
        Mockito.when(catRepositoryMock.save(any())).thenReturn(cat);
        assertEquals(cat, catServiceOut.createCat(cat));
        verify(catRepositoryMock, times(1)).save(any());
    }
    @Test
    @DisplayName("Поиск котика по его Id")
    public void shouldFindUserById() {
        Mockito.when(catRepositoryMock.findById(ID)).thenReturn(Optional.of(cat));
        assertEquals(cat, catServiceOut.findById(ID));
    }
    @Test
    @DisplayName("Исключение при поиске котика по некорректному ID")
    public void shouldThrowNotFoundInBdExceptionWhenIdIsNotValid() {
        Mockito.when(catRepositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> catServiceOut.findById(ID));
    }
    @Test
    @DisplayName("Возвращает true если котик с таким именем есть в списке")
    void shouldReturnTrue() {
        when(catRepositoryMock.findByName(VALID_CAT.getName())).thenReturn(Optional.of(VALID_CAT));
        assertTrue((BooleanSupplier) catServiceOut.findByName(String.valueOf(VALID_CAT)));
        verify(catRepositoryMock, times(1)).findByName(VALID_CAT.getName());
    }
    @Test
    @DisplayName("Поиск и обновление пользователя по его Id")
    public void shouldFindAndUpdateCorrectUser() {
        Mockito.when(catRepositoryMock.findById(ID)).thenReturn(Optional.of(cat));
        Mockito.when(catRepositoryMock.save(any())).thenReturn(cat);
        assertEquals(cat, catServiceOut.updateById(ID, cat));
    }
    @Test
    @DisplayName("Возвращает false если пользователя с именем нет в списке")
    void shouldReturnFalse() {
        when(catRepositoryMock.findByName(INVALID_CAT.getName())).thenReturn(Optional.of(null));
        assertTrue((BooleanSupplier) catServiceOut.findByName(String.valueOf(INVALID_CAT)));
        verify(catRepositoryMock, times(1)).findByName(INVALID_CAT.getName());
    }
    @Test
    @DisplayName("Исключение при обновлении по некорректному Id котика")
    public void shouldThrowNotFoundInBdExceptionWhenUpdateByIdIsNotValid() {
        Mockito.when(catRepositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> catServiceOut.updateById(ID, cat));
    }
    @Test
    @DisplayName("Вывод списка всех котиков")
    public void shouldFindByAllUsers(){
        Mockito.when(catRepositoryMock.findAll()).thenReturn(CAT);
        assertEquals(CAT, catServiceOut.findAll());
    }
}
