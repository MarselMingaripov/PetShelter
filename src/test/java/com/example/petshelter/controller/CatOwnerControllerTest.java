package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.*;
import com.example.petshelter.service.CatOwnerService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.phoneNumber").value(PHONE_NUMBER)
                        //jsonPath("$.cats").value(cats)
                        //jsonPath("$.trialPeriodsInActiveStatus").value(trialPeriods),
                        //jsonPath("$.trialPeriodsCompleted").value(trialPeriods)
                );
    }
}