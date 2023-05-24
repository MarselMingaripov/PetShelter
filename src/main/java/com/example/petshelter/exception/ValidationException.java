package com.example.petshelter.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String entity) {
        super("Ошибка валидации сущности " + entity);
    }
}
