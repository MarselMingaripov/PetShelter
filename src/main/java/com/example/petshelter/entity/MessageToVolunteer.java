package com.example.petshelter.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 *  Класс - сообщение волонтеру
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "message_to_volunteer")
public class MessageToVolunteer {

    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Информация о потенциальном опекуне животного
     */
    private String sender;
    /**
     * Текст сообщения
     */
    private String text;
    /**
     * Дата сообщения
     */
    private LocalDate localDate;

    public MessageToVolunteer(Long id, String sender, String text, LocalDate localDate) {
        this.id = id;
        this.sender = sender;
        this.text = text;
        this.localDate = LocalDate.now();
    }
}
