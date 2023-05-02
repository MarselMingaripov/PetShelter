package com.example.petshelter.service;

import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.entity.shelter.DogShelter;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatShelterRepository;
import com.example.petshelter.repository.DogShelterRepository;
import com.example.petshelter.service.impl.CatShelterServiceImpl;
import com.example.petshelter.service.impl.DogShelterServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogShelterServiceTest {

    @Mock
    private DogShelterRepository dogShelterRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;
    @InjectMocks
    private DogShelterServiceImpl dogShelterServiceOut;

    private static Long ID = 1L;
    private static String INFORMATION = "information";
    private static String ADDRESS = "address";
    private static String PHONE_NUMBER = "+79053930303";
    private static String WORK_SCHEDULE = "always";
    private static String SECURITY_CONTACTS = "contacts";
    private static String SAFETY_RECOMMENDATIONS = "recommendation";

    private static String DOG_NAME = "Tuzik";
    private DogShelter dogShelter;

    // TODO: 19.04.2023 Убрать наследование DogShelter ot AnimalShelter, изменить конструктор
    @BeforeEach
    public void init() {
//        dogShelter = new DogShelter(INFORMATION, ADDRESS, PHONE_NUMBER, WORK_SCHEDULE, SECURITY_CONTACTS,
//                SAFETY_RECOMMENDATIONS);
    }

    @Test
    @DisplayName("Проверка корректного создания приюта для собак")
    void shouldReturnWhenCreateNewDogShelter() {
        Mockito.when(validationServiceMock.validate(dogShelter)).thenReturn(true);
        Mockito.when(dogShelterRepositoryMock.save(any())).thenReturn(dogShelter);
        assertEquals(dogShelter, dogShelterServiceOut.createDogShelter(dogShelter));
    }

//    @Test
//    @DisplayName("Исключение при некорректной валидации приюта для собак")
//    void shouldThrowValidationExceptionWhenValidateNotValid() {
//        Mockito.when(validationServiceMock.validate(dogShelter)).thenReturn(false);
//        assertThrows(ValidationException.class, () -> dogShelterServiceOut.createDogShelter(dogShelter));
//
//    }

    @Test
    @DisplayName("Поиск и обновление приюта для собак по его Id")
    public void shouldReturnWhenUpdateDogShelter() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        Mockito.when(dogShelterRepositoryMock.save(any())).thenReturn(dogShelter);
        assertEquals(dogShelter, dogShelterServiceOut.updateById(ID, dogShelter));
    }

    @Test
    @DisplayName("Исключение при поиске приюта для собак по некорректному Id")
    public void shouldThrowNotFoundInBdExceptionWhenFindDogShelterByIdIsNotValid() {
        Mockito.when(dogShelterServiceOut.findById(ID)).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> dogShelterServiceOut.findById(ID));
    }
    @Test
    @DisplayName("Исключение при обновлении приюта для собак по некорректному Id")
    public void shouldThrowNotFoundInBdExceptionWhenUpdateDogShelterByIdIsNotValid() {
        Mockito.when(dogShelterRepositoryMock.findById(ID)).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> dogShelterServiceOut.updateById(ID, dogShelter));
    }

    @Test
    @DisplayName("Удаление приюта для собак по его Id")
    public void shouldReturnWhenDeleteDogShelter() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
//        Mockito.when(catShelterRepositoryMock.deleteById(ID)).thenReturn(catShelter);
        assertEquals(dogShelter, dogShelterServiceOut.deleteById(ID));
    }

    @Test
    @DisplayName("Вывод списка всех приютов для собак")
    public void shouldFindAllDogShelters() {
        List<DogShelter> dogShelterList = List.of(new DogShelter());
        when(dogShelterServiceOut.findAll()).thenReturn(dogShelterList);
        assertEquals(dogShelterList, dogShelterServiceOut.findAll());
    }

    @Test
    @DisplayName("Вывод информации о приюте для собак")
    public void shouldReturnInformationAboutDogShelter() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        assertEquals(dogShelter.getInformation(), dogShelterServiceOut.returnInformation(any()));
    }

    @Test
    @DisplayName("Вывод номера телефона приюта для собак")
    public void shouldReturnPhoneNumberDogShelter() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        assertEquals(dogShelter.getPhoneNumber(), dogShelterServiceOut.returnPhone(any()));
    }
    @Test
    @DisplayName("Вывод информации об адресе и режиме работы приюта для собак")
    public void shouldReturnAddressAndWorkScheduleDogShelter() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        assertEquals(dogShelter.getAddress() + " " + dogShelter.getWorkSchedule(), dogShelterServiceOut.returnAddressAndWorkSchedule(any()));
    }
    @Test
    @DisplayName("Вывод контактов охраны приюта для собак")
    public void shouldReturnSecurityContactsDogShelter() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        assertEquals(dogShelter.getSecurityContacts(), dogShelterServiceOut.returnSecurityContacts(any()));
    }

    @Test
    @DisplayName("Вывод рекомендаций по технике безопасности на территории приюта для собак")
    public void shouldReturnSafetyRecommendationsDogShelter() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        assertEquals(dogShelter.getSafetyRecommendations(), dogShelterServiceOut.returnSafetyRecommendations(any()));
    }

    @Test
    @DisplayName("Вывод правил по первоначальному знакомству с животными")
    public void shouldReturnDatingDogShelter() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        assertEquals(dogShelter.getDating(), dogShelterServiceOut.returnDating(any()));
    }

    @Test
    @DisplayName("Добавление данных по правилам первоначального знакомства с животными")
    public void shouldReturnAddDatingToDogShelter() {
        String dating = "Dating";
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        dogShelterServiceOut.addDating(ID, dating);
    }

    @Test
    @DisplayName("Вывод списка необходимых документов для взятия животного из приюта")
    public void shouldReturnDocumentsDogShelter() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        assertEquals(dogShelter.getDocuments(), dogShelterServiceOut.returnDocuments(any()));
    }
    @Test
    @DisplayName("Добавление правил о взятии животного из приюта")
    public void shouldReturnAddDocumentsToDogShelter() {
        String documents = "Documents";
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        dogShelterServiceOut.addDocuments(ID, documents);
    }
    @Test
    @DisplayName("Вывод рекомендаций по перевозке животного")
    public void shouldReturnTransportationDogShelter() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        assertEquals(dogShelter.getTransportation(), dogShelterServiceOut.returnTransportation(any()));
    }
    @Test
    @DisplayName("Добавление рекомендаций по перевозке животного")
    public void shouldReturnAddTransportationToDogShelter() {
        String transportation = "Transportation";
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        dogShelterServiceOut.addTransportation(ID, transportation);
    }
    @Test
    @DisplayName("Вывод рекомендаций по обустройству дома для щенков")
    public void shouldReturnArrangementPuppy() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        assertEquals(dogShelter.getArrangementPuppy(), dogShelterServiceOut.returnArrangementPuppy(any()));
    }

    @Test
    @DisplayName("Добавление рекомендаций по обустройству дома для щенков")
    public void shouldReturnAddArrangementPuppyToDogShelter() {
        String arrangementPuppy = "ArrangementPuppy";
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        dogShelterServiceOut.addArrangementPuppy(ID, arrangementPuppy);
    }

    @Test
    @DisplayName("Вывод рекомендаций по обустройству дома для взрослого животного")
    public void shouldReturnArrangementDog() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        assertEquals(dogShelter.getArrangementDog(), dogShelterServiceOut.returnArrangementDog(any()));
    }
    @Test
    @DisplayName("Добавление рекомендаций по обустройству дома для взрослых животных")
    public void shouldReturnAddArrangementDogToDogShelter() {
        String arrangementDog = "ArrangementDog";
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        dogShelterServiceOut.addArrangementDog(ID, arrangementDog);
    }
    @Test
    @DisplayName("Вывод рекомендаций по обустройству дома для животных с ограниченными возможностями")
    public void shouldReturnArrangementDisabled() {
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        assertEquals(dogShelter.getArrangementDisabled(), dogShelterServiceOut.returnArrangementDisabled(any()));
    }

    @Test
    @DisplayName("Добавление рекомендаций по обустройству дома для животных с ограниченными возможностями")
    public void shouldReturnAddArrangementDisabledToDogShelter() {
        String arrangementDisabled = "ArrangementDisabled";
        Mockito.when(dogShelterRepositoryMock.findById(any())).thenReturn(Optional.of(dogShelter));
        dogShelterServiceOut.addArrangementDisabled(ID, arrangementDisabled);
    }
}