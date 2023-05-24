package com.example.petshelter.exception.handler;

import com.example.petshelter.exception.AnimalIsReservedException;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    //404
    @ExceptionHandler
    public ResponseEntity<String> notFoundInBdExceptionHandler(NotFoundInBdException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //405
    @ExceptionHandler
    public ResponseEntity<String> validationExceptionHandler(ValidationException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    //409
    @ExceptionHandler
    public ResponseEntity<String> AnimalReservedExceptionHandler(AnimalIsReservedException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }


}
