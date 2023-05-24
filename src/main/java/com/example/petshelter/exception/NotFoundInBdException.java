package com.example.petshelter.exception;

public class NotFoundInBdException extends RuntimeException {

    public NotFoundInBdException(String message) {

        super(message);
    }
}
