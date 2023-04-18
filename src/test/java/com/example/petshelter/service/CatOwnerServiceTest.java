package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatOwnerRepository;
import com.example.petshelter.service.impl.CatOwnerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
    private static String TRIAL_PERIODS_IN_ACTIVE_STATUS = null;
    private static String TRIAL_PERIODS_COMPLETED = null;
    private static List<Cat> CATS = List.of(new Cat("Barsik",5, true,true, StatusAnimal.IN_THE_SHELTER ));
    private CatOwner catOwner;

    /*@BeforeEach
    public void init() {
        catOwner = new CatOwner(ID,PHONE_NUMBER, null, null, CATS);
    }*/
    @Test
    void shouldReturnWhenCreateNewCatOwner() {
        Mockito.when(validationServiceMock.validate(catOwner)).thenReturn(true);
        Mockito.when(catOwnerRepositoryMock.save(any())).thenReturn(catOwner);
        assertEquals(catOwner, catOwnerServiceOut.createCatOwner(catOwner));
    }
    @Test
    void shouldThrowValidationExceptionWhenValidateNotValid() {
        Mockito.when(validationServiceMock.validate(catOwner)).thenReturn(false);
        assertThrows(ValidationException.class, () -> catOwnerServiceOut.createCatOwner(catOwner));

    }

}