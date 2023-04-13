package com.example.petshelter.controller;

import com.example.petshelter.entity.MessageToVolunteer;
import com.example.petshelter.service.MessageToVolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageToVolunteerController {

    private final MessageToVolunteerService messageToVolunteerService;

    @PostMapping
    public ResponseEntity<MessageToVolunteer> createMessage(@RequestBody MessageToVolunteer messageToVolunteer){
        return ResponseEntity.ok().body(messageToVolunteerService.createMessageToVolunteer(messageToVolunteer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageToVolunteer> findMessage(@PathVariable Long id){
        return ResponseEntity.ok().body(messageToVolunteerService.findById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<MessageToVolunteer> updateMessage(@PathVariable Long id, @RequestBody MessageToVolunteer messageToVolunteer){
        return ResponseEntity.ok().body(messageToVolunteerService.updateById(id, messageToVolunteer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageToVolunteer> deleteMessage(@PathVariable Long id){
        return ResponseEntity.ok().body(messageToVolunteerService.deleteById(id));
    }

    @GetMapping
    public ResponseEntity<List<MessageToVolunteer>> findAll(){
        return ResponseEntity.ok().body(messageToVolunteerService.findAll());
    }
}
