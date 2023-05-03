package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.entity.Report;
import com.example.petshelter.exception.NotFoundInBdException;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private LocalDate RECEIVE_DATE = LocalDate.now();

    private Report report;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ReportService reportServiceMock;

    private ReportController out;

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() throws Exception {
        report = new Report(PHOTO, FOOD_RATION, GENERAL_HEALTH, BEHAVIOR_CHANGES);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldReturn200WhenCreateCorrectFieldsReport() throws Exception {
        Report report = new Report();
        report.setFoodRation("foodRation");
        report.setGeneralHealth("generalHealth");
        report.setBehaviorChanges("behaviorChanges");
        String requestBody = new ObjectMapper().writeValueAsString(report);
        mockMvc.perform(post("http://localhost:8080/report")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn200WhenFindByIdCorrectFieldsReport() throws Exception {
        when(reportServiceMock.findById(1L)).thenReturn(report);
        mockMvc.perform(get("http://localhost:8080/report/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.photo").value(PHOTO),
                        jsonPath("$.foodRation").value(FOOD_RATION),
                        jsonPath("$.generalHealth").value(GENERAL_HEALTH),
                        jsonPath("$.behaviorChanges").value(BEHAVIOR_CHANGES)
                );
    }

    @Test
    void shouldReturn200WhenUpdateByIdCorrectFieldsReport() throws Exception {
        Report report2 = new Report();
        report.setPhoto(PHOTO);
        report.setFoodRation("foodRation2");
        report.setGeneralHealth("generalHealth2");
        report.setBehaviorChanges("behaviorChanges2");

        when(reportServiceMock.updateById(1L, report2)).thenReturn(report2);
        mockMvc.perform(post("http://localhost:8080/report/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(report2)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404WhenDeleteByIdIncorrectFieldsReport() throws Exception {
        when(reportServiceMock.deleteById(1L)).thenThrow(NotFoundInBdException.class);
        mockMvc.perform(delete("http://localhost:8080/report/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isNotFound()
                );
    }

    @Test
    void shouldReturn200WhenFindAllCorrectFieldsReport() throws Exception {
        List<Report> reportList = new ArrayList<>(List.of(report));
        when(reportServiceMock.findAll()).thenReturn(reportList);
        mockMvc.perform(get("http://localhost:8080/report")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }

}
