package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.StatusAnimal;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.service.CatService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PetShelterApplication.class})
@RequiredArgsConstructor
class CatControllerTest {

    private static final String NAME = "Barsik";
    private static final int AGE = 3;
    private static final boolean HEALTH_STATUS = true;
    private static final boolean VACCINATION = true;
    private static final StatusAnimal STATUS = StatusAnimal.IN_THE_SHELTER;
    private Cat cat;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CatService catServiceMock;

    private CatController out;

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() throws Exception {
        cat = new Cat(NAME, AGE, HEALTH_STATUS, VACCINATION, STATUS);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    @Test
    void shouldReturn200WhenCreateCorrectFieldsCat() throws Exception {
        when(catServiceMock.createCat(any())).thenReturn(cat);
        mockMvc.perform(post("http://localhost:8080/cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cat)))
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
    void shouldThrow405WhenCreateIncorrectFieldsCat() throws Exception {
        when(catServiceMock.createCat(any())).thenThrow(ValidationException.class);
        mockMvc.perform(post("http://localhost:8080/cat")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cat)))
                .andExpectAll(
                        status().isMethodNotAllowed()
                );
    }

    @Test
    void shouldReturn200WhenFindByIdCorrectFieldsCat() throws Exception {
        when(catServiceMock.findById(1L)).thenReturn(cat);
        mockMvc.perform(get("http://localhost:8080/cat/1")
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
    void shouldThrow404WhenFindByIdIncorrectFieldsCat() throws Exception {
        when(catServiceMock.findById(1L)).thenThrow(NotFoundInBdException.class);
        mockMvc.perform(get("http://localhost:8080/cat/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isNotFound()
                );
    }

    @Test
    void shouldReturn200WhenFindByNameCorrectFieldsCat() throws Exception {
        when(catServiceMock.findByName(any())).thenReturn(cat);
        mockMvc.perform(get("http://localhost:8080/cat/find-by-name").param("name", NAME))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.age").value(AGE))
                .andExpect(jsonPath("$.healthStatus").value(HEALTH_STATUS))
                .andExpect(jsonPath("$.vaccination").value(VACCINATION));
    }
    @Test
    void shouldThrow404WhenFindByNameIncorrectFieldsCat() throws Exception {
        when(catServiceMock.findByName(any())).thenThrow(NotFoundInBdException.class);
        mockMvc.perform(get("http://localhost:8080/cat/find-by-name").param("name", NAME)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isNotFound()
                );
    }

    @Test
    void shouldReturn200WhenFindAllCorrectFieldsCat() throws Exception {
        List<Cat>catList = new ArrayList<>(List.of(cat));
        when(catServiceMock.findAll()).thenReturn(catList);
        mockMvc.perform(get("http://localhost:8080/cat")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }

    @Test
    void shouldReturn200WhenUpdateByIdCorrectFieldsCat() throws Exception {
        Cat updatedCat = new Cat();
        updatedCat.setName("Васька");
        updatedCat.setAge(5);
        updatedCat.setHealthStatus(true);
        updatedCat.setVaccination(true);
        when(catServiceMock.updateById(1L, updatedCat)).thenReturn(updatedCat);
        mockMvc.perform(put("http://localhost:8080/cat/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCat)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Васька"))
                .andExpect(jsonPath("$.age").value(5))
                .andExpect(jsonPath("$.healthStatus").value(true))
                .andExpect(jsonPath("$.vaccination").value(true));
    }
    @Test
    void shouldThrow404WhenUpdateByIdIncorrectFieldsCat() throws Exception {
        Cat updatedCat = new Cat();
        updatedCat.setName("Васька");
        updatedCat.setAge(5);
        updatedCat.setHealthStatus(true);
        updatedCat.setVaccination(true);
        when(catServiceMock.updateById(1L, updatedCat)).thenThrow(NotFoundInBdException.class);
        mockMvc.perform(put("http://localhost:8080/cat/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCat)))
                .andExpect(
                        status().isNotFound()
                );
    }

    @Test
    void shouldReturn404WhenDeleteByIdIncorrectFieldsCat() throws Exception {
        when(catServiceMock.deleteById(1L)).thenThrow(NotFoundInBdException.class);
        mockMvc.perform(delete("http://localhost:8080/cat/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cat)))
                .andExpect(
                        status().isNotFound()
                );
    }

    @Test
    void shouldReturn200WhenReserveCatCorrectFieldsCat() throws Exception {

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