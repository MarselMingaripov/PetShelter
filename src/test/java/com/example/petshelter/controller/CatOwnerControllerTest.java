package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.*;
import com.example.petshelter.service.CatOwnerService;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {PetShelterApplication.class})
@RequiredArgsConstructor
class CatOwnerControllerTest {
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

    private Cat cat;
    private CatOwner catOwner;
    private Report report;
    private TrialPeriod trialPeriod;
    private List<Cat> cats;
    private List<Report> reports;
    private List<TrialPeriod> trialPeriods;


    @MockBean
    private CatOwnerService catOwnerServiceMock;
    //    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private static final String NAME = "Barsik";

    private CatOwnerController out;

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;


    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate date;


    @BeforeEach
    void init() throws Exception {
        cat = new Cat(NAME, AGE, HEALTH_STATUS, VACCINATION, STATUS);
        cats = List.of(cat);

        report = new Report(ID, PHOTO, FOOD_RATION, GENERAL_HEALTH, BEHAVIOR_CHANGES);
        reports = List.of(report);

        trialPeriod = new TrialPeriod(
                PHONE_NUMBER,
                LocalDate.of(2023, 04, 24),
                LocalDate.of(2023, 04, 24),
                reports,
                TrialPeriodResult.SUCCESS);

        trialPeriods = List.of(trialPeriod);

        catOwner = new CatOwner(ID, PHONE_NUMBER, cats, trialPeriods, trialPeriods);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldReturn200WhenCreateCorrectFieldsCatOwner() throws Exception {
        when(catOwnerServiceMock.createCatOwner(any())).thenReturn(catOwner);
        String json = objectMapper.writeValueAsString(catOwner);
        mockMvc.perform(post("http://localhost:8080/catOwner")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(); // Преобразование данныех в строку
//                .andExpectAll(
//                        status().isOk(),
//                .andExpect(jsonPath("$.phoneNumber").value(PHONE_NUMBER))
//                .andExpect(jsonPath("$.cats").value(cats))
//                .andExpect(jsonPath("$.trialPeriodsInActiveStatus").value(trialPeriods))
//                .andExpect(jsonPath("$.trialPeriodsCompleted").value(trialPeriods));
    }

}