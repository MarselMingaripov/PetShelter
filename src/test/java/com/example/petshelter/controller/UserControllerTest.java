package com.example.petshelter.controller;

import com.example.petshelter.PetShelterApplication;
import com.example.petshelter.entity.RoleSt;
import com.example.petshelter.entity.User;
import com.example.petshelter.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PetShelterApplication.class})
@RequiredArgsConstructor
class UserControllerTest {

    private static Long ID = 1L;
    private static String PHONE_NUMBER = "+79053930303";
    private User user;
    private MockMvc mockMvc;

    @MockBean
    private UserService userServiceMock;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() throws Exception {
        user = new User(PHONE_NUMBER);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }
    @Test
    void shouldReturn200WhenCreateCorrectFieldsUser() throws Exception {
        Mockito.when(userServiceMock.createUser(any())).thenReturn(user);
        mockMvc.perform(post("http://localhost:8080/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.phoneNumber").value(PHONE_NUMBER));
        verify(userServiceMock, times(1)).createUser(any());
    }

    @Test
    public void shouldReturn200WhenCreateCorrectFieldsUserFromTgB() throws Exception {

        String text = "+79053930303 12345";
        User user1 = new User("+79050505055", 12345L);

        Mockito.when(userServiceMock.createUserFromTgB(text)).thenReturn(user1);
        mockMvc.perform(post("http://localhost:8080/user/from-tg")
                        .content(text))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userServiceMock,times(1)).createUserFromTgB(text);
    }

    @Test
    void shouldReturn200WhenReceivedCorrectFieldsUserById() throws Exception {
        when(userServiceMock.findById(any())).thenReturn(user);
        mockMvc.perform(get("http://localhost:8080/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(PHONE_NUMBER));
        verify(userServiceMock,times(1)).findById(any());
    }

    @Test
    void shouldReturn200WhenReceivedCorrectFieldsUserByTgId() throws Exception {
        when(userServiceMock.findByTelegramID(any())).thenReturn(user);
        mockMvc.perform(get("http://localhost:8080/user/phone/1234")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userServiceMock,times(1)).findByTelegramID(any());
    }

    @Test
    void shouldReturn200WhenUpdateCorrectFieldsUserById() throws Exception {
        String json = objectMapper.writeValueAsString(user);
        when(userServiceMock.updateById(any(), any())).thenReturn(user);
        mockMvc.perform(post("http://localhost:8080/user/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString())
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userServiceMock,times(1)).updateById(any(), any());
    }
    @Test
    void shouldReturn200WhenDeleteCorrectFieldsUserById() throws Exception {
        when(userServiceMock.deleteById(any())).thenReturn(user);
        mockMvc.perform(delete("http://localhost:8080/user/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ID.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userServiceMock,times(1)).deleteById(any());
    }
    @Test
    void shouldReturn200WhenFindAllUsers() throws Exception {
        when(userServiceMock.findAll()).thenReturn(List.of(user));
        mockMvc.perform(get("http://localhost:8080/user"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userServiceMock,times(1)).findAll();
    }
    @Test
    void shouldReturn200WhenFindAllUsersVolunteer() throws Exception {

        User user1 = new User("+79053930303",1234L, RoleSt.VOLUNTEER);
        User user2 = new User("+79053930003",5678L, RoleSt.VOLUNTEER);
        List<Long> userList = Arrays.asList(user1.getTelegramId(),user2.getTelegramId());

        when(userServiceMock.returnVolunteerTgId()).thenReturn(userList);
        mockMvc.perform(get("http://localhost:8080/user/tg-id-volunteer"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userServiceMock,times(1)).returnVolunteerTgId();
    }

}