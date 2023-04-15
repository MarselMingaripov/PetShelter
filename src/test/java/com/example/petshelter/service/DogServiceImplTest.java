package com.example.petshelter.service;

import com.example.petshelter.repository.DogRepository;
import com.example.petshelter.service.impl.DogServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DogServiceImplTest {
    @Mock
    private DogRepository dogRepositoryMock;
    @InjectMocks
    private DogServiceImpl outDogMock;

    @Test
    public void doTestDog() {

    }
}
