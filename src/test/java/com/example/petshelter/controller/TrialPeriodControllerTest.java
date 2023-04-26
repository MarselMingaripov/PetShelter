package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.Report;
import com.example.petshelter.entity.TrialPeriod;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
//        trialPeriod = new TrialPeriod(ID, OWNER_NAME);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldReturn200WhenCreateCorrectFieldsMessageToVolunteer() throws Exception {
        when(trialPeriodServiceMock.createTrialPeriod(any())).thenReturn(trialPeriod);
        mockMvc.perform(post("http://localhost:8080/dog")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(trialPeriodServiceMock)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value(ID),
                        jsonPath("$.ownerName").value(OWNER_NAME)

                );
    }

    @Test
    void shouldThrow405WhenCreateIncorrectFieldsMessageToVolunteer() throws Exception {
        when(trialPeriodServiceMock.createTrialPeriod(any())).thenThrow(ValidationException.class);
        mockMvc.perform(post("http://localhost:8080/dog")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(trialPeriodServiceMock)))
                .andExpectAll(
                        status().isMethodNotAllowed()
                );
    }

}
