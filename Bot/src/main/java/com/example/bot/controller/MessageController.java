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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bot/message")
@RequiredArgsConstructor
public class MessageController {

    private HttpClient client = HttpClient.newHttpClient();

    public void sendMessage(String message) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/message"))
                                .POST(HttpRequest.BodyPublishers.ofString(message))
                                        .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public Boolean checkUnread(){
        ResponseEntity<Boolean> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/message/check", Boolean.class);
        Boolean result = responseEntity.getBody();
        return result;
    }

    public List<String> getUnread(){
        ResponseEntity<List> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/message/unread", List.class);
        List<String> messages = responseEntity.getBody();
        return messages;
    }
}
