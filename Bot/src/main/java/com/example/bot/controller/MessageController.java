package com.example.bot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
}
