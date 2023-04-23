package com.example.bot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bot/user")
@RequiredArgsConstructor
public class UserController {

    private HttpClient client = HttpClient.newHttpClient();

    public String getCatShelterInfo(Long id) {
        Map<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/user/phone/{id}", String.class, uriVariables);
        String result = responseEntity.getBody();
        return result;
    }

    public void sendUser(String textUser) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/user/from-tg"))
                .POST(HttpRequest.BodyPublishers.ofString(textUser))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public List<Long> getVolunteerTgId() {
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/user/tg-id-volunteer", String.class);
        String str = responseEntity.getBody();
        str = str.replaceAll("[a-zA-z]", "");
        String[] arr = str.split(" ");
        List<Long> result = new ArrayList<>();
        for (String s : arr) {
            System.out.println(s);
            if (isNumber(s)) {
                Long l = Long.valueOf(s);
                result.add(l);
            }
        }
        return result;
    }

    public boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}
