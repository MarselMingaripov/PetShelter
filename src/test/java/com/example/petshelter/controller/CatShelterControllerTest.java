package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.service.CatShelterService;
import com.example.petshelter.service.impl.CatServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    private static Long ID = 1L;
    private static String INFORMATION = "information";
    private static String ADDRESS = "address";
    private static String PHONE_NUMBER = "+79053930303";
    private static String WORK_SCHEDULE = "always";
    private static String SECURITY_CONTACTS = "contacts";
    private static String SAFETY_RECOMMENDATIONS = "recommendation";

    private static String DATING = "Информация о знакомстве с животными";

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

        catShelter = new CatShelter(INFORMATION, ADDRESS, PHONE_NUMBER, WORK_SCHEDULE, SECURITY_CONTACTS, SAFETY_RECOMMENDATIONS);

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
        verify(catShelterServiceMock,times(1)).createCatShelter(any());
    }

    @Test
    void shouldReturn200WhenReceivedCorrectFieldsCatShelterById() throws Exception {
        when(catShelterServiceMock.findById(any())).thenReturn(catShelter);
        mockMvc.perform(get("http://localhost:8080/catShelter/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(PHONE_NUMBER));
        verify(catShelterServiceMock,times(1)).findById(any());
    }

    @Test
    void shouldReturn200WhenFindAllCatShelters() throws Exception {
        when(catShelterServiceMock.findAll()).thenReturn(List.of(catShelter));
        mockMvc.perform(get("http://localhost:8080/catShelter"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock,times(1)).findAll();
    }

    @Test
    void shouldReturn200WhenUpdateCorrectFieldsCatShelterById() throws Exception {
        String json = objectMapper.writeValueAsString(catShelter);
        when(catShelterServiceMock.updateById(any(), any())).thenReturn(catShelter);
        mockMvc.perform(put("http://localhost:8080/catShelter/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString())
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock,times(1)).updateById(any(), any());
    }

    @Test
    void shouldReturn200WhenDeleteCorrectFieldsCatShelterById() throws Exception {
        when(catShelterServiceMock.deleteById(any())).thenReturn(catShelter);
        mockMvc.perform(delete("http://localhost:8080/catShelter/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock,times(1)).deleteById(any());
    }

    @Test
    void shouldReturn200WhenGetInformationAboutCatShelterById() throws Exception {
        when(catShelterServiceMock.returnInformation(any())).thenReturn(INFORMATION);
        mockMvc.perform(get("http://localhost:8080/catShelter/information/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock,times(1)).returnInformation(any());
    }

    @Test
    void shouldReturn200WhenGetAddressAndWorkScheduleAboutCatShelterById() throws Exception {
        when(catShelterServiceMock.returnAddressAndWorkSchedule(any())).thenReturn(INFORMATION + " " + ADDRESS);
        mockMvc.perform(get("http://localhost:8080/catShelter/address-and-work-schedule/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock,times(1)).returnAddressAndWorkSchedule(any());
    }

    @Test
    void shouldReturn200WhenGetPhoneNumberAboutCatShelterById() throws Exception {
        when(catShelterServiceMock.returnPhone(any())).thenReturn(PHONE_NUMBER);
        mockMvc.perform(get("http://localhost:8080/catShelter/phone_number/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock,times(1)).returnPhone(any());
    }

    @Test
    void shouldReturn200WhenGetSecurityContactsAboutCatShelterById() throws Exception {
        when(catShelterServiceMock.returnSecurityContacts(any())).thenReturn(PHONE_NUMBER);
        mockMvc.perform(get("http://localhost:8080/catShelter/security-contacts/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock,times(1)).returnSecurityContacts(any());
    }

    @Test
    void shouldReturn200WhenGetSafetyRecommendationsAboutCatShelterById() throws Exception {
        when(catShelterServiceMock.returnSafetyRecommendations(any())).thenReturn(PHONE_NUMBER);
        mockMvc.perform(get("http://localhost:8080/catShelter/safety-recommendations/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock,times(1)).returnSafetyRecommendations(any());
    }

    @Test
    void shouldReturn200AddCatToCatShelter() throws Exception {
        mockMvc.perform(post("http://localhost:8080/catShelter/add-cat-to-shelter")
                        .param("name", NAME1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock,times(1)).addCatToShelter(any());
    }

    @Test
    void shouldReturn200AddCatOwnerToCatShelter() throws Exception {
        mockMvc.perform(post("http://localhost:8080/catShelter/add-cat-owner-to-shelter")
                        .param("phoneNumber", PHONE_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock,times(1)).addCatOwnerToShelter(any());
    }

    @Test
    void shouldReturn200AddDatingToCatShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/catShelter/add-dating")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(catShelterServiceMock, times(1)).addDating(1l, value);
    }
    @Test
    void shouldReturn200AddDocumentsToCatShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/catShelter/add-documents")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(catShelterServiceMock, times(1)).addDocuments(1l, value);
    }
    @Test
    void shouldReturn200AddTransportationToCatShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/catShelter/add-transportation")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(catShelterServiceMock, times(1)).addTransportation(1l, value);
    }

    @Test
    void shouldReturn200AddArrangementKittenToCatShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/catShelter/add-arrangement-kitten")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(catShelterServiceMock, times(1)).addArrangementKitten(1l, value);
    }
    @Test
    void shouldReturn200AddArrangementCatToCatShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/catShelter/add-arrangement-cat")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(catShelterServiceMock, times(1)).addArrangementCat(1l, value);
    }

    @Test
    void shouldReturn200AddArrangementDisabledToCatShelter() throws Exception {

        Long id = 1L;
        String value = "test";

        mockMvc.perform(post("http://localhost:8080/catShelter/add-arrangement-disabled")
                        .param("id", id.toString())
                        .param("value", value))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(catShelterServiceMock, times(1)).addArrangementDisabled(1l, value);
    }
    @Test
    void shouldReturn200GetCatDatingFromCatShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/catShelter/get-cat-dating")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock, times(1)).returnDating(any());
    }

    @Test
    void shouldReturn200GetCatDocumentsFromCatShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/catShelter/get-cat-documents")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock, times(1)).returnDocuments(any());
    }

    @Test
    void shouldReturn200GetCatTransportationFromCatShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/catShelter/get-cat-transportation")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock, times(1)).returnTransportation(any());
    }

    @Test
    void shouldReturn200GetCatArrangementKittenFromCatShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/catShelter/get-cat-arrangement-kitten")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock, times(1)).returnArrangementKitten(any());
    }

    @Test
    void shouldReturn200GetCatArrangementCatFromCatShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/catShelter/get-cat-arrangement-cat")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock, times(1)).returnArrangementCat(any());
    }

    @Test
    void shouldReturn200GetCatArrangementDisabledFromCatShelter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/catShelter/get-cat-arrangement-disabled")
                        .param("id", ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(catShelterServiceMock, times(1)).returnArrangementDisabled(any());
    }

}