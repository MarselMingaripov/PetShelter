package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.entity.shelter.DogShelter;
import com.example.petshelter.service.CatShelterService;
import com.example.petshelter.service.DogShelterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PetShelterApplication.class})
@RequiredArgsConstructor
class DogShelterControllerTest {

    private static final String NAME1 = "Tuzik";
    private static final String NAME2 = "Layka";
    private static final int AGE = 3;
    private static final boolean HEALTH_STATUS = true;
    private static final boolean VACCINATION = true;
    private static final StatusAnimal STATUS = StatusAnimal.IN_THE_SHELTER;

    private static Long ID = 1L;
    private static String INFORMATION = "information";
    private static String ADDRESS = "address";
    private static String PHONE_NUMBER = "+79053930303";
    private static String WORK_SCHEDULE = "always";
    private static String SECURITY_CONTACTS = "contacts";
    private static String SAFETY_RECOMMENDATIONS = "recommendation";

    private static String DATING = "Информация о знакомстве с животными";

    private List<Dog> dogs;

    private DogShelter dogShelter;

    private MockMvc mockMvc;

    @MockBean
    private DogShelterService dogShelterServiceMock;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final WebApplicationContext webApplicationContext;


    @BeforeEach
    void init() throws Exception {
        Dog dog1 = new Dog(NAME1, AGE, HEALTH_STATUS, VACCINATION, STATUS);
        dogs = List.of(dog1);

        dogShelter = new DogShelter(INFORMATION, ADDRESS, PHONE_NUMBER, WORK_SCHEDULE,
                SECURITY_CONTACTS, SAFETY_RECOMMENDATIONS);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldReturn200WhenCreateCorrectFieldsDogShelter() throws Exception {
        Mockito.when(dogShelterServiceMock.createDogShelter(any())).thenReturn(dogShelter);
        mockMvc.perform(post("http://localhost:8080/dogShelter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dogShelter)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.information").value(INFORMATION),
                        jsonPath("$.address").value(ADDRESS),
                        jsonPath("$.phoneNumber").value(PHONE_NUMBER),
                        jsonPath("$.workSchedule").value(WORK_SCHEDULE),
                        jsonPath("$.securityContacts").value(SECURITY_CONTACTS),
                        jsonPath("$.safetyRecommendations").value(SAFETY_RECOMMENDATIONS));
        verify(dogShelterServiceMock, times(1)).createDogShelter(any());
    }

    @Test
    void shouldReturn200WhenReceivedCorrectFieldsDogShelterById() throws Exception {
        when(dogShelterServiceMock.findById(any())).thenReturn(dogShelter);
        mockMvc.perform(get("http://localhost:8080/dogShelter/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(PHONE_NUMBER));
        verify(dogShelterServiceMock, times(1)).findById(any());
    }

    @Test
    void shouldReturn200WhenFindAllDogShelters() throws Exception {
        when(dogShelterServiceMock.findAll()).thenReturn(List.of(dogShelter));
        mockMvc.perform(get("http://localhost:8080/dogShelter"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).findAll();
    }

    @Test
    void shouldReturn200WhenUpdateCorrectFieldsDogShelterById() throws Exception {
        String json = objectMapper.writeValueAsString(dogShelter);
        when(dogShelterServiceMock.updateById(any(), any())).thenReturn(dogShelter);
        mockMvc.perform(put("http://localhost:8080/dogShelter/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString())
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).updateById(any(), any());
    }

    @Test
    void shouldReturn200WhenDeleteCorrectFieldsDogShelterById() throws Exception {
        when(dogShelterServiceMock.deleteById(any())).thenReturn(dogShelter);
        mockMvc.perform(delete("http://localhost:8080/dogShelter/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).deleteById(any());
    }

    @Test
    void shouldReturn200WhenGetInformationAboutDogShelterById() throws Exception {
        when(dogShelterServiceMock.returnInformation(any())).thenReturn(INFORMATION);
        mockMvc.perform(get("http://localhost:8080/dogShelter/information/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).returnInformation(any());
    }

    @Test
    void shouldReturn200WhenGetAddressAndWorkScheduleAboutDogShelterById() throws Exception {
        when(dogShelterServiceMock.returnAddressAndWorkSchedule(any())).thenReturn(INFORMATION + " " + ADDRESS);
        mockMvc.perform(get("http://localhost:8080/dogShelter/address-and-work-schedule/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).returnAddressAndWorkSchedule(any());
    }

    @Test
    void shouldReturn200WhenGetPhoneNumberAboutDogShelterById() throws Exception {
        when(dogShelterServiceMock.returnPhone(any())).thenReturn(PHONE_NUMBER);
        mockMvc.perform(get("http://localhost:8080/dogShelter/phone_number/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).returnPhone(any());
    }

    @Test
    void shouldReturn200WhenGetSecurityContactsAboutDogShelterById() throws Exception {
        when(dogShelterServiceMock.returnSecurityContacts(any())).thenReturn(PHONE_NUMBER);
        mockMvc.perform(get("http://localhost:8080/dogShelter/security-contacts/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).returnSecurityContacts(any());
    }

    @Test
    void shouldReturn200WhenGetSafetyRecommendationsAboutDogShelterById() throws Exception {
        when(dogShelterServiceMock.returnSafetyRecommendations(any())).thenReturn(PHONE_NUMBER);
        mockMvc.perform(get("http://localhost:8080/dogShelter/safety-recommendations/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).returnSafetyRecommendations(any());
    }

    @Test
    void shouldReturn200AddDogToDogShelter() throws Exception {
        mockMvc.perform(post("http://localhost:8080/dogShelter/add-dog-to-shelter")
                        .param("name", NAME1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).addDogToShelter(any());
    }

    @Test
    void shouldReturn200AddDogOwnerToDogShelter() throws Exception {
        mockMvc.perform(post("http://localhost:8080/dogShelter/add-dog-owner-to-shelter")
                        .param("phoneNumber", PHONE_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).addDogOwnerToShelter(any());
    }

    @Test
    void shouldReturn200AddDatingToDogShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/dogShelter/add-dating")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(dogShelterServiceMock, times(1)).addDating(1l, value);
    }

    @Test
    void shouldReturn200AddDocumentsToDogShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/dogShelter/add-documents")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(dogShelterServiceMock, times(1)).addDocuments(1l, value);
    }

    @Test
    void shouldReturn200AddTransportationToDogShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/dogShelter/add-transportation")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(dogShelterServiceMock, times(1)).addTransportation(1l, value);
    }

    @Test
    void shouldReturn200AddArrangementPuppyToDogShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/dogShelter/add-arrangement-puppy")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(dogShelterServiceMock, times(1)).addArrangementPuppy(1l, value);
    }

    @Test
    void shouldReturn200AddArrangementDogToDogShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/dogShelter/add-arrangement-dog")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(dogShelterServiceMock, times(1)).addArrangementDog(1l, value);
    }

    @Test
    void shouldReturn200AddArrangementDisabledToDogShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/dogShelter/add-arrangement-disabled")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(dogShelterServiceMock, times(1)).addArrangementDisabled(1l, value);
    }

    @Test
    void shouldReturn200GetDogDatingFromDogShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/dogShelter/get-dog-dating")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).returnDating(any());
    }

    @Test
    void shouldReturn200GetDogDocumentsFromDogShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/dogShelter/get-dog-documents")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).returnDocuments(any());
    }

    @Test
    void shouldReturn200GetDogTransportationFromDogShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/dogShelter/get-dog-transportation")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).returnTransportation(any());
    }

    @Test
    void shouldReturn200GetDogArrangementPuppyFromDogShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/dogShelter/get-dog-arrangement-puppy")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).returnArrangementPuppy(any());
    }

    @Test
    void shouldReturn200GetArrangementDogFromDogShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/dogShelter/get-dog-arrangement-dog")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).returnArrangementDog(any());
    }

    @Test
    void shouldReturn200GetArrangementDisabledFromDogShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/dogShelter/get-dog-arrangement-disabled")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogShelterServiceMock, times(1)).returnArrangementDisabled(any());
    }

}