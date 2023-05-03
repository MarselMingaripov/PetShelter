package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.Report;
import com.example.petshelter.entity.TrialPeriod;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.service.ReportService;
import com.example.petshelter.service.TrialPeriodService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PetShelterApplication.class})
@RequiredArgsConstructor
public class TrialPeriodControllerTest {

    private Long ID = 1L;
    private String OWNER_NAME = "ownerName";

    private TrialPeriod trialPeriod;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private TrialPeriodService trialPeriodServiceMock;

    private TrialPeriodController out;

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() throws Exception {
        trialPeriod = new TrialPeriod(ID, OWNER_NAME);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldReturn200WhenCreateCorrectFieldsTrialPeriod() throws Exception {
        when(trialPeriodServiceMock.createTrialPeriod(any())).thenReturn(trialPeriod);
        mockMvc.perform(post("http://localhost:8080/trial_period")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trialPeriod)))
                .andExpectAll(
                        status().isOk());
    }

    @Test
    void shouldReturn200WhenFindByIdCorrectFieldsTrialPeriod() throws Exception {
        when(trialPeriodServiceMock.findById(1L)).thenReturn(trialPeriod);
        mockMvc.perform(get("http://localhost:8080/trial_period/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk());
    }

    @Test
    void shouldReturn200WhenUpdateByIdCorrectFieldsTrialPeriod() throws Exception {
        TrialPeriod trialPeriod2 = new TrialPeriod();
        trialPeriod.setOwnerName("ownerName2");
        when(trialPeriodServiceMock.updateById(1L, trialPeriod2)).thenReturn(trialPeriod2);
        mockMvc.perform(post("http://localhost:8080/trial_period/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trialPeriod2)))
                .andExpectAll(
                        status().isOk());
    }

    @Test
    void shouldReturn404WhenDeleteByIdIncorrectFieldsTrialPeriod() throws Exception {
        when(trialPeriodServiceMock.deleteById(1L)).thenThrow(NotFoundInBdException.class);
        mockMvc.perform(delete("http://localhost:8080/trial_period/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trialPeriod)))
                .andExpect(
                        status().isNotFound()
                );
    }

    @Test
    void shouldReturn200WhenFindAllCorrectFieldsTrialPeriod() throws Exception {
        List<TrialPeriod> trialPeriodList = new ArrayList<>(List.of(trialPeriod));
        when(trialPeriodServiceMock.findAll()).thenReturn(trialPeriodList);
        mockMvc.perform(get("http://localhost:8080/trial_period")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }
}
