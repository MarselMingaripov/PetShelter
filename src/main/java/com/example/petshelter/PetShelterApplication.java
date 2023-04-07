package com.example.petshelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetShelterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetShelterApplication.class, args);
        System.out.println("Создали ветки");
        System.out.println("Создал ветку marselFeature");
    }

}
