package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.exception.NotFoundInBdException;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        when(messageToVolunteerServiceMock.createMessageFromText(any())).thenReturn(messageToVolunteer);
        mockMvc.perform(post("http://localhost:8080/message")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(TEXT))
                .andExpectAll(
                        status().isOk());
    }

    @Test
    void shouldReturn200WhenFindByIdCorrectFieldsMessageToVolunteer() throws Exception {
        when(messageToVolunteerServiceMock.findById(1L)).thenReturn(messageToVolunteer);
        mockMvc.perform(get("http://localhost:8080/message/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.sender").value(SENDER),
                        jsonPath("$.text").value(TEXT)
                );
    }
    @Test
    void shouldReturn200WhenUpdateByIdCorrectFieldsMessageToVolunteer() throws Exception {
        MessageToVolunteer message2 = new MessageToVolunteer();
        message2.setSender("sender2");
        message2.setText("text2");

        when(messageToVolunteerServiceMock.updateById(1L, message2)).thenReturn(message2);
        mockMvc.perform(post("http://localhost:8080/message/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(message2)))
                .andExpect(status().isOk());
    }
    @Test
    void shouldReturn404WhenDeleteByIdIncorrectFieldsMessageToVolunteer() throws Exception {
        when(messageToVolunteerServiceMock.deleteById(1L)).thenThrow(NotFoundInBdException.class);
        mockMvc.perform(delete("http://localhost:8080/message/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isNotFound()
                );
    }
    @Test
    void shouldReturn200WhenFindAllCorrectFieldsMessageToVolunteer() throws Exception {
        List<MessageToVolunteer> messageToVolunteerList = new ArrayList<>(List.of(messageToVolunteer));
        when(messageToVolunteerServiceMock.findAll()).thenReturn(messageToVolunteerList);
        mockMvc.perform(get("http://localhost:8080/message")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }
    @Test
    void shouldReturn200WhenFindAllUnreadCorrectFieldsMessageToVolunteer() throws Exception {
        mockMvc.perform(get("http://localhost:8080/message/unread")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }
    @Test
    void shouldReturn200WhenCheckAllUnreadCorrectFieldsMessageToVolunteer() throws Exception {
        mockMvc.perform(get("http://localhost:8080/message/check")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }
}
