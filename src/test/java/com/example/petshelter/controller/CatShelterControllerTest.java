package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.service.CatShelterService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PetShelterApplication.class})
@RequiredArgsConstructor
class CatShelterControllerTest {

    private static final String NAME1 = "Barsik";
    private static final String NAME2 = "Murka";
    private static final int AGE = 3;
    private static final boolean HEALTH_STATUS = true;
    private static final boolean VACCINATION = true;
    private static final StatusAnimal STATUS = StatusAnimal.IN_THE_SHELTER;

    private static String INFORMATION = "information";
    private static String ADDRESS = "address";
    private static String PHONE_NUMBER = "+79053930303";
    private static String WORK_SCHEDULE = "always";
    private static String SECURITY_CONTACTS = "contacts";
    private static String SAFETY_RECOMMENDATIONS = "recommendation";

    private List<Cat> cats;

    private CatShelter catShelter;
    private MockMvc mockMvc;

    @MockBean
    private CatShelterService catShelterServiceMock;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final WebApplicationContext webApplicationContext;


    @BeforeEach
    void init() throws Exception {
        Cat cat1 = new Cat(NAME1, AGE, HEALTH_STATUS, VACCINATION, STATUS);
        cats = List.of(cat1);

        catShelter = new CatShelter(INFORMATION,ADDRESS,PHONE_NUMBER, WORK_SCHEDULE, SECURITY_CONTACTS, SAFETY_RECOMMENDATIONS);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldReturn200WhenCreateCorrectFieldsCatShelter() throws Exception {
        Mockito.when(catShelterServiceMock.createCatShelter(any())).thenReturn(catShelter);
        mockMvc.perform(post("http://localhost:8080/catShelter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(catShelter)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.information").value(INFORMATION),
                        jsonPath("$.address").value(ADDRESS),
                        jsonPath("$.phoneNumber").value(PHONE_NUMBER),
                        jsonPath("$.workSchedule").value(WORK_SCHEDULE),
                        jsonPath("$.securityContacts").value(SECURITY_CONTACTS),
                        jsonPath("$.safetyRecommendations").value(SAFETY_RECOMMENDATIONS));
        verify(catShelterServiceMock).createCatShelter(any());
    }


}