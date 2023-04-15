package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.repository.CatRepository;
import com.example.petshelter.service.impl.CatServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatServiceImplTest {
    private static final Cat VALID_CAT = new Cat("Барсик", 12, true, true, StatusAnimal.HAS_HOUSE);
    private static final Cat INVALID_CAT = new Cat("Жук", 0, true, true, StatusAnimal.IN_THE_SHELTER);

    @Mock
    CatRepository catRepositoryMock;
    @InjectMocks
    CatServiceImpl catServiceOut;

    @Test
    @DisplayName("Возвращает true если пользователь с именем есть в списке")
    void shouldReturnTrue() {
        when(catRepositoryMock.findByName(VALID_CAT.getName())).thenReturn(Optional.of(VALID_CAT));
        assertTrue((BooleanSupplier) catServiceOut.findByName(String.valueOf(VALID_CAT)));
        verify(catRepositoryMock, times(1)).findByName(VALID_CAT.getName());
    }

    @Test
    @DisplayName("Возвращает false если пользователя с именем нет в списке")
    void shouldReturnFalse() {
        when(catRepositoryMock.findByName(INVALID_CAT.getName())).thenReturn(Optional.of(null));
        assertTrue((BooleanSupplier) catServiceOut.findByName(String.valueOf(INVALID_CAT)));
        verify(catRepositoryMock, times(1)).findByName(INVALID_CAT.getName());
    }
}
