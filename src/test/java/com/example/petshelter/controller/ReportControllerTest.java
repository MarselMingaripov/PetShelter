package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.entity.Report;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.service.MessageToVolunteerService;
import com.example.petshelter.service.ReportService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PetShelterApplication.class})
@RequiredArgsConstructor
public class ReportControllerTest {
    private final static Long ID = 1L;
    private byte[] PHOTO;
    private String FOOD_RATION = "foodRation";
    private String GENERAL_HEALTH = "generalHealth";
    private String BEHAVIOR_CHANGES = "behaviorChanges";

    private Report report;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ReportService reportServiceMock;

    private ReportController out;

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() throws Exception {
        report = new Report(ID, PHOTO, FOOD_RATION, GENERAL_HEALTH, BEHAVIOR_CHANGES);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldReturn200WhenCreateCorrectFieldsMessageToVolunteer() throws Exception {
        when(reportServiceMock.createReport(any())).thenReturn(report);
        mockMvc.perform(post("http://localhost:8080/dog")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(reportServiceMock)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value(ID),
                        jsonPath("$.foodRation").value(FOOD_RATION),
                        jsonPath("$.generalHealth").value(GENERAL_HEALTH),
                        jsonPath("$.behaviorChanges").value(BEHAVIOR_CHANGES)


                );
    }

    @Test
    void shouldThrow405WhenCreateIncorrectFieldsMessageToVolunteer() throws Exception {
        when(reportServiceMock.createReport(any())).thenThrow(ValidationException.class);
        mockMvc.perform(post("http://localhost:8080/dog")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(reportServiceMock)))
                .andExpectAll(
                        status().isMethodNotAllowed()
                );
    }
}
