package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.*;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.CatOwnerRepository;
import com.example.petshelter.service.CatOwnerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.petshelter.entity.StatusAnimal.RESERVED;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PetShelterApplication.class})
@RequiredArgsConstructor
class CatOwnerControllerTest {

    private static final String NAME1 = "Barsik";
    private static final String NAME2 = "Murka";
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


    private CatOwner catOwner;
    private Report report;
    private TrialPeriod trialPeriod;
    private List<Cat> cats;
    private List<Report> reports;
    private List<TrialPeriod> trialPeriods;
    private MockMvc mockMvc;

    @MockBean
    private CatOwnerService catOwnerServiceMock;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() throws Exception {
        Cat cat1 = new Cat(NAME1, AGE, HEALTH_STATUS, VACCINATION, STATUS);
//        Cat cat2 = new Cat(NAME2, AGE, HEALTH_STATUS, VACCINATION, STATUS);
        cats = List.of(cat1);

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

        catOwner = new CatOwner(PHONE_NUMBER, new ArrayList<>(List.of(cat1)), null, null);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldReturn200WhenCreateCorrectFieldsCatOwner() throws Exception {
        when(catOwnerServiceMock.createCatOwner(any())).thenReturn(catOwner);
        mockMvc.perform(post("http://localhost:8080/catOwner")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(catOwner)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.phoneNumber").value(PHONE_NUMBER));
        verify(catOwnerServiceMock).createCatOwner(any());
    }

    @Test
    void shouldThrow405WhenCreateIncorrectFieldsCatOwner() throws Exception {
        when(catOwnerServiceMock.createCatOwner(any())).thenThrow(ValidationException.class);
        mockMvc.perform(post("http://localhost:8080/catOwner")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(catOwner)))
                .andExpectAll(
                        status().isMethodNotAllowed()
                );
    }

    @Test
    void shouldReturn200WhenReceivedCorrectFieldsCatOwnerById() throws Exception {
        when(catOwnerServiceMock.findById(any())).thenReturn(catOwner);
        mockMvc.perform(get("http://localhost:8080/catOwner/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(PHONE_NUMBER));
        verify(catOwnerServiceMock).findById(any());
    }

    @Test
    void shouldReturn404WhenReceivedNotCorrectFieldsCatOwnerById() throws Exception {
        when(catOwnerServiceMock.findById(any())).thenThrow(ValidationException.class);
        mockMvc.perform(get("http://localhost:8080/catOwner/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void shouldReturn200WhenReceivedCorrectFieldsCatOwnerByPhone_number() throws Exception {
        when(catOwnerServiceMock.findByPhoneNumber(any())).thenReturn(catOwner);
        mockMvc.perform(get("http://localhost:8080/catOwner/find-by-phone-number").param("phoneNumber", PHONE_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(PHONE_NUMBER));
        verify(catOwnerServiceMock).findByPhoneNumber(any());
    }

    @Test
    void shouldReturn400WhenReceivedNotCorrectFieldsCatOwnerByPhoneNumber() throws Exception {
        when(catOwnerServiceMock.findByPhoneNumber(any())).thenThrow(ValidationException.class);
        mockMvc.perform(get("http://localhost:8080/catOwner/find-by-phone-number")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn404WhenReceivedNotCorrectFieldsCatOwnerByPhoneNumber() throws Exception {
        when(catOwnerServiceMock.findByPhoneNumber(any())).thenThrow(ValidationException.class);
        mockMvc.perform(get("http://localhost:8080/catOwner/find-by-phone-number/"+PHONE_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn200WhenFindAllCatOwners() throws Exception {
        when(catOwnerServiceMock.findAll()).thenReturn(List.of(catOwner));
        mockMvc.perform(get("http://localhost:8080/catOwner"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catOwnerServiceMock).findAll();
    }

    @Test
    void shouldReturn200WhenUpdateCorrectFieldsCatOwner() throws Exception {
        String json = objectMapper.writeValueAsString(catOwner);
        when(catOwnerServiceMock.updateById(any(), any())).thenReturn(catOwner);
        mockMvc.perform(put("http://localhost:8080/catOwner/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString())
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catOwnerServiceMock).updateById(any(), any());
    }

    @Test
    void shouldThrow405WhenUpdateIncorrectFieldsCatOwner() throws Exception {
        String json = objectMapper.writeValueAsString(catOwner);
        when(catOwnerServiceMock.updateById(any(),any())).thenThrow(ValidationException.class);
        mockMvc.perform(put("http://localhost:8080/catOwner/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString())
                        .content(json))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void shouldReturn200WhenDeleteCorrectFieldsCatOwner() throws Exception {
        when(catOwnerServiceMock.deleteById(any())).thenReturn(catOwner);
        mockMvc.perform(delete("http://localhost:8080/catOwner/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catOwnerServiceMock).deleteById(any());
    }

    @Test
    void shouldReturn200WhenGetAnimalToCatOwnerIsCorrect() throws Exception {
        when(catOwnerServiceMock.getAnimalToTrialPeriod(PHONE_NUMBER,"Barsik",30L)).thenReturn(catOwner);
        mockMvc.perform(post("http://localhost:8080/catOwner/get-animal")
                        .param("phoneNumber",PHONE_NUMBER)
                        .param("animalName",NAME1)
                        .param("trialDays", String.valueOf(30L)))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catOwnerServiceMock).getAnimalToTrialPeriod(any(),any(),any());
    }

}