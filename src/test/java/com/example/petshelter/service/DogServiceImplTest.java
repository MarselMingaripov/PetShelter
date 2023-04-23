package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.StatusAnimal;

import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.DogRepository;
import com.example.petshelter.service.impl.DogServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DogServiceImplTest {
    private static final Dog VALID_DOG = new Dog(1L,"Шарик", 12, true, true, StatusAnimal.HAS_HOUSE);
    private static final Dog INVALID_DOG = new Dog(1L,"Жук", 0, true, true, StatusAnimal.IN_THE_SHELTER);

    @Mock
    DogRepository dogRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;
    @InjectMocks
    DogServiceImpl dogServiceOut;
    private static Long ID = 1L;
    private Dog dog;
    private List<Dog> DOG = List.of(new Dog());

    @Test
    @DisplayName("Проверка корректного создания нового собакина")
    public void shouldReturnWhenCreateNewUser() {

        Mockito.when(validationServiceMock.validatePhoneNumber(any())).thenReturn(true);
        Mockito.when(dogRepositoryMock.save(any())).thenReturn(dog);
        assertEquals(dog, dogServiceOut.createDog(dog));
        verify(dogRepositoryMock, times(1)).save(any());
    }
    @Test
    @DisplayName("Поиск собакина по его Id")
    public void shouldFindUserById() {
        Mockito.when(dogRepositoryMock.findById(ID)).thenReturn(Optional.of(dog));
        assertEquals(dog, dogServiceOut.findById(ID));
    }
    @Test
    @DisplayName("Исключение при поиске собакина по некорректному ID")
    public void shouldThrowNotFoundInBdExceptionWhenIdIsNotValid() {
        Mockito.when(dogRepositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> dogServiceOut.findById(ID));
    }
    @Test
    @DisplayName("Возвращает true если собакина с таким именем есть в списке")
    void shouldReturnTrue() {
        when(dogRepositoryMock.findByName(VALID_DOG.getName())).thenReturn(Optional.of(VALID_DOG));
        assertTrue((BooleanSupplier) dogServiceOut.findByName(String.valueOf(VALID_DOG)));
        verify(dogRepositoryMock, times(1)).findByName(VALID_DOG.getName());
    }
    @Test
    @DisplayName("Возвращает false если собакина с именем нет в списке")
    void shouldReturnFalse() {
        when(dogRepositoryMock.findByName(INVALID_DOG.getName())).thenReturn(Optional.of(null));
        assertTrue((BooleanSupplier) dogServiceOut.findByName(String.valueOf(INVALID_DOG)));
        verify(dogRepositoryMock, times(1)).findByName(INVALID_DOG.getName());
    }
    @Test
    @DisplayName("Исключение при обновлении по некорректному Id собакина")
    public void shouldThrowNotFoundInBdExceptionWhenUpdateByIdIsNotValid() {
        Mockito.when(dogRepositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> dogServiceOut.updateById(ID, dog));
    }
    @Test
    @DisplayName("Вывод списка всех собачек")
    public void shouldFindByAllUsers(){
        Mockito.when(dogRepositoryMock.findAll()).thenReturn(DOG);
        assertEquals(DOG, dogServiceOut.findAll());
    }
}
