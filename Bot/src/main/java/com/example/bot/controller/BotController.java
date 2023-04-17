package com.example.bot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotController {

    public String getCatShelterInfo(){
        Map<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("id", 1L);
        ResponseEntity<String> responseEntity =new RestTemplate().getForEntity(
                "http://localhost:8080/catShelter/information/{id}", String.class, uriVariables);
        String result = responseEntity.getBody();
        return result;
    }
}
