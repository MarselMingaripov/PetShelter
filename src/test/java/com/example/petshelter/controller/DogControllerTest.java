package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.service.DogService;
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

import java.util.ArrayList;
import java.util.List;

import static com.example.petshelter.entity.StatusAnimal.HAS_HOUSE;
import static com.example.petshelter.entity.StatusAnimal.RESERVED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PetShelterApplication.class})
@RequiredArgsConstructor
public class DogControllerTest {

    private static final String NAME = "Tuzik";
    private static final int AGE = 3;
    private static final boolean HEALTH_STATUS = true;
    private static final boolean VACCINATION = true;
    private static final StatusAnimal STATUS = RESERVED;
    private static final String PHONE_NUMBER = "+7(101)345-12-34";
    private Dog dog;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private DogService dogServiceMock;

    private DogController out;

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() throws Exception {
        dog = new Dog(NAME, AGE, HEALTH_STATUS, VACCINATION, STATUS);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldReturn200WhenCreateCorrectFieldsDog() throws Exception {
        when(dogServiceMock.createDog(any())).thenReturn(dog);
        mockMvc.perform(post("http://localhost:8080/dog")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dog)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.name").value(NAME),
                        jsonPath("$.age").value(AGE),
                        jsonPath("$.healthStatus").value(HEALTH_STATUS),
                        jsonPath("$.vaccination").value(VACCINATION)
                        //jsonPath("$.statusAnimal").value(STATUS)
                );
    }

    @Test
    void shouldThrow405WhenCreateIncorrectFieldsDog() throws Exception {
        when(dogServiceMock.createDog(any())).thenThrow(ValidationException.class);
        mockMvc.perform(post("http://localhost:8080/dog")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dog)))
                .andExpectAll(
                        status().isMethodNotAllowed()
                );
    }

    @Test
    void shouldReturn200WhenFindByIdCorrectFieldsDog() throws Exception {
        when(dogServiceMock.findById(1L)).thenReturn(dog);
        mockMvc.perform(get("http://localhost:8080/dog/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.name").value(NAME),
                        jsonPath("$.age").value(AGE),
                        jsonPath("$.healthStatus").value(HEALTH_STATUS),
                        jsonPath("$.vaccination").value(VACCINATION)
                );
    }
    @Test
    void shouldThrow404WhenFindByIdIncorrectFieldsDog() throws Exception {
        when(dogServiceMock.findById(1L)).thenThrow(NotFoundInBdException.class);
        mockMvc.perform(get("http://localhost:8080/dog/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isNotFound()
                );
    }


    @Test
    void shouldReturn200WhenFindByNameCorrectFieldsDog() throws Exception {
        when(dogServiceMock.findByName(any())).thenReturn(dog);
        mockMvc.perform(get("http://localhost:8080/dog/find-by-name").param("name", NAME))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.age").value(AGE))
                .andExpect(jsonPath("$.healthStatus").value(HEALTH_STATUS))
                .andExpect(jsonPath("$.vaccination").value(VACCINATION));
    }
    @Test
    void shouldThrow404WhenFindByNameIncorrectFieldsDog() throws Exception {
        when(dogServiceMock.findByName(any())).thenThrow(NotFoundInBdException.class);
        mockMvc.perform(get("http://localhost:8080/dog/find-by-name").param("name", NAME)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isNotFound()
                );
    }

    @Test
    void shouldReturn200WhenFindAllCorrectFieldsDog() throws Exception {
        List<Dog> dogList = new ArrayList<>(List.of(dog));
        when(dogServiceMock.findAll()).thenReturn(dogList);
        mockMvc.perform(get("http://localhost:8080/dog")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }

    @Test
    void shouldReturn200WhenUpdateByIdCorrectFieldsDog() throws Exception {
        Dog updatedDog = new Dog();
        updatedDog.setName("Sharik");
        updatedDog.setAge(4);
        updatedDog.setHealthStatus(true);
        updatedDog.setVaccination(true);
        when(dogServiceMock.updateById(1L, updatedDog)).thenReturn(updatedDog);
        mockMvc.perform(put("http://localhost:8080/dog/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDog)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sharik"))
                .andExpect(jsonPath("$.age").value(4))
                .andExpect(jsonPath("$.healthStatus").value(true))
                .andExpect(jsonPath("$.vaccination").value(true));
    }
    @Test
    void shouldThrow404WhenUpdateByIdIncorrectFieldsDog() throws Exception {
        Dog updatedDog = new Dog();
        updatedDog.setName("Sharik");
        updatedDog.setAge(4);
        updatedDog.setHealthStatus(true);
        updatedDog.setVaccination(true);
        when(dogServiceMock.updateById(1L, updatedDog)).thenThrow(NotFoundInBdException.class);
        mockMvc.perform(put("http://localhost:8080/dog/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDog)))
                .andExpect(
                        status().isNotFound()
                );
    }

    @Test
    void shouldReturn404WhenDeleteByIdIncorrectFieldsDog() throws Exception {
        when(dogServiceMock.deleteById(1L)).thenThrow(NotFoundInBdException.class);
        mockMvc.perform(delete("http://localhost:8080/dog/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dog)))
                .andExpect(
                        status().isNotFound()
                );
    }

    @Test
    void shouldReturn200WhenReserveDogCorrectFieldsDog() throws Exception {
        when(dogServiceMock.reserveDog(NAME, PHONE_NUMBER))
                .thenReturn(dog);
        mockMvc.perform(post("http://localhost:8080/dog/reserve")
                        .param("name", NAME)
                        .param("phoneNumber", PHONE_NUMBER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.age").value(AGE))
                .andExpect(jsonPath("$.healthStatus").value(true))
                .andExpect(jsonPath("$.vaccination").value(true));
    }

    @Test
    void shouldReturn200WhenFindAllInShelterCorrectFieldsDog() throws Exception {
        List<Dog>dogList = new ArrayList<>(List.of(dog));
        when(dogServiceMock.findAllInShelter()).thenReturn(dogList);
        mockMvc.perform(get("http://localhost:8080/dog/all-in-shelter")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }

    @Test
    void shouldReturn200WhenFindAllByStatusCorrectFieldsDog() throws Exception {
        List<Dog>dogList = new ArrayList<>(List.of(dog));
        when(dogServiceMock.showAllByStatus(RESERVED)).thenReturn(dogList);
        mockMvc.perform(get("http://localhost:8080/dog/all-by-status")
                        .param("statusAnimal", STATUS.toString()))
                .andExpect(
                        status().isOk());
    }

    @Test
    void shouldReturn200WhenChangeStatusCorrectFieldsDog() throws Exception  {
        when(dogServiceMock.changeStatusAnimal("Tuzik", HAS_HOUSE)).thenReturn(dog);
        mockMvc.perform(post("http://localhost:8080/dog/change-status")
                        .param("name", NAME)
                        .param("statusAnimal", String.valueOf(RESERVED)))
                .andExpect(status().isOk());

    }
}
