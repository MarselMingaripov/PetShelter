package com.example.petshelter.service;

import com.example.petshelter.repository.CatRepository;
import com.example.petshelter.service.impl.CatServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatServiceImplTest {
    @Mock
    private CatRepository catRepositoryMock;
    @InjectMocks
    private CatServiceImpl outCatMock;
    @Test
    public void doTestCat() {

    }
}
