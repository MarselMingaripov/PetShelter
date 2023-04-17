package com.example.petshelter.service;

import com.example.petshelter.entity.User;
import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatShelterRepository;
import com.example.petshelter.repository.UserRepository;
import com.example.petshelter.service.impl.CatShelterServiceImpl;
import com.example.petshelter.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CatShelterServiceTest {

    @Mock
    private CatShelterRepository catShelterRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;
    @InjectMocks
    private CatShelterServiceImpl catShelterServiceOut;

    private static Long ID = 1L;
    private static String INFORMATION = "information";
    private static String ADDRESS = "address";
    private static String PHONE_NUMBER = "+79053930303";
    private static String WORK_SCHEDULE = "always";
    private static String SECURITY_CONTACTS = "contacts";
    private static String SAFETY_RECOMMENDATIONS = "recommendation";

    private CatShelter catShelter;

    /*@BeforeEach
    public void init() {
        catShelter = new CatShelter(ID, INFORMATION, ADDRESS, PHONE_NUMBER, WORK_SCHEDULE, SECURITY_CONTACTS,
                                    SAFETY_RECOMMENDATIONS);
    }*/
    @Test
    void shouldReturnWhenCreateNewCatShelter() {
        Mockito.when(validationServiceMock.validate(catShelter)).thenReturn(true);
        Mockito.when(catShelterRepositoryMock.save(any())).thenReturn(catShelter);
        assertEquals(catShelter, catShelterServiceOut.createCatShelter(catShelter));

    }
    @Test
    void shouldThrowValidationExceptionWhenValidateNotValid() {
        Mockito.when(validationServiceMock.validate(catShelter)).thenReturn(false);
        assertThrows(ValidationException.class, () -> catShelterServiceOut.createCatShelter(catShelter));

    }

}