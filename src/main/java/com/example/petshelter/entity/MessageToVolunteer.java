package com.example.petshelter.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "message_to_volunteer")
public class MessageToVolunteer {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String sender;
    private String text;
    private LocalDate localDate;

    public MessageToVolunteer(Long id, String sender, String text, LocalDate localDate) {
        this.id = id;
        this.sender = sender;
        this.text = text;
        this.localDate = LocalDate.now();
    }
}
