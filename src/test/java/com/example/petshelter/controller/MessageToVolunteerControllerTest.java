package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.service.CatService;
import com.example.petshelter.service.MessageToVolunteerService;
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
public class MessageToVolunteerControllerTest {
    private final static Long ID = 1L;
    private final static String SENDER = "sender";
    private final static String TEXT = "text";

    private MessageToVolunteer messageToVolunteer;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private MessageToVolunteerService messageToVolunteerServiceMock;

    private MessageToVolunteerController out;

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() throws Exception {
        messageToVolunteer = new MessageToVolunteer(ID, SENDER, TEXT);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldReturn200WhenCreateCorrectFieldsMessageToVolunteer() throws Exception {
        when(messageToVolunteerServiceMock.createMessageToVolunteer(any())).thenReturn(messageToVolunteer);
        mockMvc.perform(post("http://localhost:8080/dog")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(messageToVolunteer)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value(ID),
                        jsonPath("$.sender").value(SENDER),
                        jsonPath("$.text").value(TEXT)

                        );
    }

    @Test
    void shouldThrow405WhenCreateIncorrectFieldsMessageToVolunteer() throws Exception {
        when(messageToVolunteerServiceMock.createMessageToVolunteer(any())).thenThrow(ValidationException.class);
        mockMvc.perform(post("http://localhost:8080/dog")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(messageToVolunteer)))
                .andExpectAll(
                        status().isMethodNotAllowed()
                );
    }
}
