package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.*;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.service.DogOwnerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PetShelterApplication.class})
@RequiredArgsConstructor
class DogOwnerControllerTest {

    private static final String NAME1 = "Tuzik";
//    private static final String NAME2 = "Zhuchka";
    private static final int AGE = 3;
    private static final boolean HEALTH_STATUS = true;
    private static final boolean VACCINATION = true;
    private static final StatusAnimal STATUS = StatusAnimal.IN_THE_SHELTER;

    private static byte[] PHOTO = new byte[]{127};
    private static String FOOD_RATION = "foodRation";
    private static String GENERAL_HEALTH = "generalHealth";
    private static String BEHAVIOR_CHANGES = "behaviorChanges";

    private static Long ID = 1L;
    private static String PHONE_NUMBER = "+79053930303";


    private DogOwner dogOwner;
    private Report report;
    private TrialPeriod trialPeriod;
    private List<Dog> dogs;
    private List<Report> reports;
    private List<TrialPeriod> trialPeriods;
    private MockMvc mockMvc;

    @MockBean
    private DogOwnerService dogOwnerServiceMock;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() throws Exception {
        Dog dog1 = new Dog(NAME1, AGE, HEALTH_STATUS, VACCINATION, STATUS);
//        Cat cat2 = new Cat(NAME2, AGE, HEALTH_STATUS, VACCINATION, STATUS);
        dogs = List.of(dog1);

        report = new Report(ID, PHOTO, FOOD_RATION, GENERAL_HEALTH, BEHAVIOR_CHANGES);
        reports = List.of(report);

        trialPeriod = new TrialPeriod(
                PHONE_NUMBER,
                NAME1,
                LocalDate.of(2023, 04, 24),
                LocalDate.of(2023, 04, 24),
                reports,
                TrialPeriodResult.SUCCESS);

        trialPeriods = List.of(trialPeriod);

        dogOwner = new DogOwner(PHONE_NUMBER, new ArrayList<>(List.of(dog1)), null, null);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldReturn200WhenCreateCorrectFieldsDogOwner() throws Exception {
        when(dogOwnerServiceMock.createDogOwner(any())).thenReturn(dogOwner);
        mockMvc.perform(post("http://localhost:8080/dogOwner")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dogOwner)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.phoneNumber").value(PHONE_NUMBER));
        verify(dogOwnerServiceMock).createDogOwner(any());
    }

    @Test
    void shouldThrow405WhenCreateIncorrectFieldsDogOwner() throws Exception {
        when(dogOwnerServiceMock.createDogOwner(any())).thenThrow(ValidationException.class);
        mockMvc.perform(post("http://localhost:8080/dogOwner")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dogOwner)))
                .andExpectAll(
                        status().isMethodNotAllowed()
                );
    }

    @Test
    void shouldReturn200WhenReceivedCorrectFieldsDogOwnerById() throws Exception {
        when(dogOwnerServiceMock.findById(any())).thenReturn(dogOwner);
        mockMvc.perform(get("http://localhost:8080/dogOwner/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(PHONE_NUMBER));
        verify(dogOwnerServiceMock).findById(any());
    }

    @Test
    void shouldReturn404WhenReceivedNotCorrectFieldsDogOwnerById() throws Exception {
        when(dogOwnerServiceMock.findById(any())).thenThrow(ValidationException.class);
        mockMvc.perform(get("http://localhost:8080/dogOwner/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void shouldReturn200WhenReceivedCorrectFieldsDogOwnerByPhone_number() throws Exception {
        when(dogOwnerServiceMock.findByPhoneNumber(any())).thenReturn(dogOwner);
        mockMvc.perform(get("http://localhost:8080/dogOwner/find-by-phone-number").param("phoneNumber", PHONE_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(PHONE_NUMBER));
        verify(dogOwnerServiceMock).findByPhoneNumber(any());
    }

    @Test
    void shouldReturn400WhenReceivedNotCorrectFieldsDogOwnerByPhoneNumber() throws Exception {
        when(dogOwnerServiceMock.findByPhoneNumber(any())).thenThrow(ValidationException.class);
        mockMvc.perform(get("http://localhost:8080/dogOwner/find-by-phone-number")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn404WhenReceivedNotCorrectFieldsDogOwnerByPhoneNumber() throws Exception {
        when(dogOwnerServiceMock.findByPhoneNumber(any())).thenThrow(ValidationException.class);
        mockMvc.perform(get("http://localhost:8080/dogOwner/find-by-phone-number/"+PHONE_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn200WhenFindAllDogOwners() throws Exception {
        when(dogOwnerServiceMock.findAll()).thenReturn(List.of(dogOwner));
        mockMvc.perform(get("http://localhost:8080/dogOwner"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogOwnerServiceMock).findAll();
    }

    @Test
    void shouldReturn200WhenUpdateCorrectFieldsDogOwner() throws Exception {
        String json = objectMapper.writeValueAsString(dogOwner);
        when(dogOwnerServiceMock.updateById(any(), any())).thenReturn(dogOwner);
        mockMvc.perform(put("http://localhost:8080/dogOwner/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString())
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogOwnerServiceMock).updateById(any(), any());
    }

    @Test
    void shouldThrow405WhenUpdateIncorrectFieldsDogOwner() throws Exception {
        String json = objectMapper.writeValueAsString(dogOwner);
        when(dogOwnerServiceMock.updateById(any(),any())).thenThrow(ValidationException.class);
        mockMvc.perform(put("http://localhost:8080/dogOwner/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString())
                        .content(json))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void shouldReturn200WhenDeleteCorrectFieldsDogOwner() throws Exception {
        when(dogOwnerServiceMock.deleteById(any())).thenReturn(dogOwner);
        mockMvc.perform(delete("http://localhost:8080/dogOwner/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogOwnerServiceMock).deleteById(any());
    }

    @Test
    void shouldReturn200WhenGetAnimalToDogOwnerIsCorrect() throws Exception {
        when(dogOwnerServiceMock.getAnimalToTrialPeriod(PHONE_NUMBER,"Tuzik",30L)).thenReturn(dogOwner);
        mockMvc.perform(post("http://localhost:8080/dogOwner/get-animal")
                        .param("phoneNumber",PHONE_NUMBER)
                        .param("animalName",NAME1)
                        .param("trialDays", String.valueOf(30L)))
                .andDo(print())
                .andExpect(status().isOk());
        verify(dogOwnerServiceMock).getAnimalToTrialPeriod(any(),any(),any());
    }

}