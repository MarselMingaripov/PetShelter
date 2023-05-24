package com.example.petshelter.service;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatRepository;
import com.example.petshelter.repository.CatShelterRepository;
import com.example.petshelter.service.impl.CatServiceImpl;
import com.example.petshelter.service.impl.CatShelterServiceImpl;
import com.sun.xml.bind.v2.TODO;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatShelterServiceTest {

    @Mock
    private CatShelterRepository catShelterRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;
    @Mock
    private CatRepository catRepositoryMock;

    @InjectMocks
    private CatShelterServiceImpl catShelterServiceOut;

    @InjectMocks
    private CatServiceImpl catServiceOut;

    private static Long ID = 1L;
    private static String INFORMATION = "information";
    private static String ADDRESS = "address";
    private static String PHONE_NUMBER = "+79053930303";
    private static String WORK_SCHEDULE = "always";
    private static String SECURITY_CONTACTS = "contacts";
    private static String SAFETY_RECOMMENDATIONS = "recommendation";
    private static String CAT_NAME = "Murzik";
    private CatShelter catShelter;

    @BeforeEach
    public void init() {
        catShelter = new CatShelter(INFORMATION, ADDRESS, PHONE_NUMBER, WORK_SCHEDULE, SECURITY_CONTACTS,
                SAFETY_RECOMMENDATIONS);
    }

    @Test
    @DisplayName("Проверка корректного создания приюта для кошек")
    void shouldReturnWhenCreateNewCatShelter() {
//        Mockito.when(validationServiceMock.validate(catShelter)).thenReturn(true);
        Mockito.when(catShelterRepositoryMock.save(any())).thenReturn(catShelter);
        assertEquals(catShelter, catShelterServiceOut.createCatShelter(catShelter));

    }
//    @Test
//    @DisplayName("Исключение при некорректной валидации приюта для кошек")
//    void shouldThrowValidationExceptionWhenValidateNotValid() {
//        Mockito.when(validationServiceMock.validate(catShelter)).thenReturn(false);
//        Mockito.when(catShelterRepositoryMock.save(any())).thenReturn(catShelter);
//        assertThrows(ValidationException.class, () -> catShelterServiceOut.createCatShelter(catShelter));
//    }

    @Test
    @DisplayName("Поиск и обновление приюта для кошек по его Id")
    public void shouldReturnWhenUpdateCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        Mockito.when(catShelterRepositoryMock.save(any())).thenReturn(catShelter);
        assertEquals(catShelter, catShelterServiceOut.updateById(ID, catShelter));

    }
    @Test
    @DisplayName("Исключение при поиске приюта для кошек по некорректному Id")
    public void shouldThrowNotFoundInBdExceptionWhenFindCatShelterByIdIsNotValid() {
        Mockito.when(catShelterRepositoryMock.findById(ID)).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> catShelterServiceOut.findById(ID));
    }
    @Test
    @DisplayName("Исключение при обновлении приюта для кошек по некорректному Id")
    public void shouldThrowNotFoundInBdExceptionWhenUpdateCatShelterByIdIsNotValid() {
        Mockito.when(catShelterRepositoryMock.findById(ID)).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> catShelterServiceOut.updateById(ID, catShelter));
    }

    @Test
    @DisplayName("Удаление приюта для кошек по его Id")
    public void shouldReturnWhenDeleteCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
//        Mockito.when(catShelterRepositoryMock.deleteById(ID)).thenReturn(catShelter);
        assertEquals(catShelter, catShelterServiceOut.deleteById(ID));

    }


    //TODO: 21.04.2023 Исправить ошибку
//    @Test
//    @DisplayName("Проверка корректного добавления кошки в приют")
//    void shouldReturnWhenAddNewCatToShelter() {
//        Cat cat = new Cat(1L, "Murzik", 5, true, true, StatusAnimal.IN_THE_SHELTER);
//        Mockito.when(validationServiceMock.validateString(CAT_NAME)).thenReturn(true);
//        Mockito.when(catRepositoryMock.findByName("Murzik")).thenReturn(Optional.of(cat));
//        Mockito.when(catRepositoryMock.save(any())).thenReturn(cat);
//        assertEquals(cat, catServiceOut.createCat(cat));
//
//    }

//    @Test
//    @DisplayName("Исключение при некорректной валидации приюта для кошек")
//    void shouldThrowValidationExceptionWhenValidateNotValid() {
//        Mockito.when(validationServiceMock.validate(catShelter)).thenReturn(false);
//        assertThrows(ValidationException.class, () -> catShelterServiceOut.createCatShelter(catShelter));
//
//    }

    @Test
    @DisplayName("Вывод списка всех приютов для кошек")
    public void shouldFindAllCatShelters() {
        List<CatShelter> catShelterList = List.of(new CatShelter());
        when(catShelterServiceOut.findAll()).thenReturn(catShelterList);
        assertEquals(catShelterList, catShelterServiceOut.findAll());
    }

    @Test
    @DisplayName("Вывод информации о приюте для кошек")
    public void shouldReturnInformationAboutCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        assertEquals(catShelter.getInformation(), catShelterServiceOut.returnInformation(any()));
    }
    @Test
    @DisplayName("Вывод номера телефона приюта для кошек")
    public void shouldReturnPhoneNumberCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        assertEquals(catShelter.getPhoneNumber(), catShelterServiceOut.returnPhone(any()));
    }
    @Test
    @DisplayName("Вывод информации об адресе и режиме работы приюта для кошек")
    public void shouldReturnAddressAndWorkScheduleCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        assertEquals(catShelter.getAddress() + " " + catShelter.getWorkSchedule(),
                catShelterServiceOut.returnAddressAndWorkSchedule(any()));
    }
    @Test
    @DisplayName("Вывод контактов охраны приюта для кошек")
    public void shouldReturnSecurityContactsCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        assertEquals(catShelter.getSecurityContacts(), catShelterServiceOut.returnSecurityContacts(any()));
    }

    @Test
    @DisplayName("Вывод рекомендаций по технике безопасности на территории приюта для кошек")
    public void shouldReturnSafetyRecommendationsCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        assertEquals(catShelter.getSafetyRecommendations(), catShelterServiceOut.returnSafetyRecommendations(any()));
    }

    @Test
    @DisplayName("Вывод правил по первоначальному знакомству с животными")
    public void shouldReturnDatingCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        assertEquals(catShelter.getDating(), catShelterServiceOut.returnDating(any()));
    }

    @Test
    @DisplayName("Добавление данных по правилам первоначального знакомства с животными")
    public void shouldReturnAddDatingToCatShelter() {
        String dating = "Dating";
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        catShelterServiceOut.addDating(ID, dating);
    }

    @Test
    @DisplayName("Вывод списка необходимых документов для взятия животного из приюта")
    public void shouldReturnDocumentsCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        assertEquals(catShelter.getDocuments(), catShelterServiceOut.returnDocuments(any()));
    }
    @Test
    @DisplayName("Добавление правил о взятии животного из приюта")
    public void shouldReturnAddDocumentsToCatShelter() {
        String documents = "Documents";
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        catShelterServiceOut.addDocuments(ID, documents);
    }
    @Test
    @DisplayName("Вывод рекомендаций по перевозке животного")
    public void shouldReturnTransportationCatShelter() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        assertEquals(catShelter.getTransportation(), catShelterServiceOut.returnTransportation(any()));
    }
    @Test
    @DisplayName("Добавление рекомендаций по перевозке животного")
    public void shouldReturnAddTransportationToCatShelter() {
        String transportation = "Transportation";
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        catShelterServiceOut.addTransportation(ID, transportation);
    }
    @Test
    @DisplayName("Вывод рекомендаций по обустройству дома для котят")
    public void shouldReturnArrangementKitten() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        assertEquals(catShelter.getArrangementKitten(), catShelterServiceOut.returnArrangementKitten(any()));
    }

    @Test
    @DisplayName("Добавление рекомендаций по обустройству дома для котят")
    public void shouldReturnAddArrangementKittenToCatShelter() {
        String arrangementKitten = "ArrangementKitten";
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        catShelterServiceOut.addArrangementKitten(ID, arrangementKitten);
    }

    @Test
    @DisplayName("Вывод рекомендаций по обустройству дома для взрослого животного")
    public void shouldReturnArrangementCat() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        assertEquals(catShelter.getArrangementCat(), catShelterServiceOut.returnArrangementCat(any()));
    }
    @Test
    @DisplayName("Добавление рекомендаций по обустройству дома для взрослых животных")
    public void shouldReturnAddArrangementCatToCatShelter() {
        String arrangementCat = "ArrangementCat";
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        catShelterServiceOut.addArrangementCat(ID, arrangementCat);
    }
    @Test
    @DisplayName("Вывод рекомендаций по обустройству дома для животных с ограниченными возможностями")
    public void shouldReturnArrangementDisabled() {
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        assertEquals(catShelter.getArrangementDisabled(), catShelterServiceOut.returnArrangementDisabled(any()));
    }

    @Test
    @DisplayName("Добавление рекомендаций по обустройству дома для животных с ограниченными возможностями")
    public void shouldReturnAddArrangementDisabledToCatShelter() {
        String arrangementDisabled = "ArrangementDisabled";
        Mockito.when(catShelterRepositoryMock.findById(any())).thenReturn(Optional.of(catShelter));
        catShelterServiceOut.addArrangementDisabled(ID, arrangementDisabled);
    }
}