package com.example.petshelter.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Базовый класс - опекун животного
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    /**
     * Уникальный идентификатор записи в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Номер сотового телефона опекуна животного
     */
    private String phoneNumber;

    private Long telegramId;

    @Enumerated(value = EnumType.STRING)
    private RoleSt roleSt;

    public User(Long id) {
        this.id = id;
    }

    public User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.roleSt = RoleSt.USER;
    }

    public User(String phoneNumber, Long telegramId) {
        this.phoneNumber = phoneNumber;
        this.telegramId = telegramId;
        this.roleSt = RoleSt.USER;
    }

    public User(String phoneNumber, Long telegramId, RoleSt roleSt) {
        this.phoneNumber = phoneNumber;
        this.telegramId = telegramId;
        this.roleSt = roleSt;
    }
}
