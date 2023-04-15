package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.StatusAnimal;

import com.example.petshelter.repository.DogRepository;
import com.example.petshelter.service.impl.DogServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DogServiceImplTest {
    private static final Dog VALID_DOG = new Dog(1L,"Шарик", 12, true, true, StatusAnimal.HAS_HOUSE);
    private static final Dog INVALID_DOG = new Dog(1L,"Жук", 0, true, true, StatusAnimal.IN_THE_SHELTER);

    @Mock
    DogRepository dogRepositoryMock;
    @InjectMocks
    DogServiceImpl dogServiceOut;

    @Test
    @DisplayName("Возвращает true если пользователь с именем есть в списке")
    void shouldReturnTrue() {
        when(dogRepositoryMock.findByName(VALID_DOG.getName())).thenReturn(Optional.ofNullable(VALID_DOG));
        assertTrue((BooleanSupplier) dogServiceOut.findByName(String.valueOf(VALID_DOG)));
        verify(dogRepositoryMock, times(1)).findByName(VALID_DOG.getName());
    }

    @Test
    @DisplayName("Возвращает false если пользователя с именем нет в списке")
    void shouldReturnFalse() {
        when(dogRepositoryMock.findByName(INVALID_DOG.getName())).thenReturn(Optional.ofNullable(null));
        assertTrue((BooleanSupplier) dogServiceOut.findByName(String.valueOf(INVALID_DOG)));
        verify(dogRepositoryMock, times(1)).findByName(INVALID_DOG.getName());
    }
}
