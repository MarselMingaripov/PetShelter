package com.example.bot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bot/user")
@RequiredArgsConstructor
public class UserController {

    public String getCatShelterInfo(Long id){
        Map<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/user/phone/{id}", String.class, uriVariables);
        String result = responseEntity.getBody();
        return result;
    }
}
