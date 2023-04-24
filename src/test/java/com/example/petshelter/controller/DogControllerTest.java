package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.Dog;
import com.example.petshelter.entity.StatusAnimal;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PetShelterApplication.class})
@RequiredArgsConstructor
public class DogControllerTest {

    private static final String NAME = "Tuzik";
    private static final int AGE = 3;
    private static final boolean HEALTH_STATUS = true;
    private static final boolean VACCINATION = true;
    private static final StatusAnimal STATUS = StatusAnimal.IN_THE_SHELTER;
    private Dog dog;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private DogService dogServiceMock;

    private DogController out;

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() throws Exception {
        dog = new Dog();
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
    void findById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findAll() {
    }

    @Test
    void updateById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void reserveCat() {
    }

    @Test
    void findAllInShelter() {
    }

    @Test
    void findAllByStatus() {
    }

    @Test
    void changeStatus() {
    }
}
