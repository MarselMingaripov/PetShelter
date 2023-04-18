package com.example.petshelter.service;

import com.example.petshelter.entity.TrialPeriod;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.TrialPeriodRepository;
import com.example.petshelter.service.impl.TrialPeriodServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TrialPeriodServiceTest {
    @Mock
    private TrialPeriodRepository trialPeriodRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;
    @InjectMocks
    private TrialPeriodServiceImpl trialPeriodServiceOut;

    private TrialPeriod trialPeriod;

    private Long ID = 1L;
    private String OWNER_NAME = "ownerName";

    @BeforeEach
    public void init() {
        trialPeriod = new TrialPeriod();
    }

    @Test
    @DisplayName("Проверка корректнсти создания нового отчета")
    void shouldReturnWhenCreateTrialPeriod() {
        Mockito.when(validationServiceMock.validate(trialPeriod)).thenReturn(true);
        Mockito.when(trialPeriodRepositoryMock.save(any())).thenReturn(trialPeriod);
        assertEquals(trialPeriod, trialPeriodServiceOut.createTrialPeriod(trialPeriod));
    }

    @Test
    @DisplayName("Поиск и обновление отчета по его Id")
    public void shouldFindAndUpdateCorrectTrialPeriod() {
        Mockito.when(trialPeriodRepositoryMock.findById(ID)).thenReturn(Optional.of(trialPeriod));
        Mockito.when(trialPeriodRepositoryMock.save(any())).thenReturn(trialPeriod);
        assertEquals(trialPeriod, trialPeriodServiceOut.updateById(ID, trialPeriod));

    }
    @Test
    @DisplayName("Исключение при поиске отчета по некорректному ID")
    public void shouldThrowNotFoundInBdExceptionWhenIdIsNotValid() {
        Mockito.when(trialPeriodRepositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> trialPeriodServiceOut.findById(ID));
    }
    @Test
    @DisplayName("Вывод списка всех отчетов")
    public void shouldFindByAllCorrectTrialPeriod() {
        TrialPeriod trialPeriod1 = new TrialPeriod();
        TrialPeriod trialPeriod2 = new TrialPeriod();
        List<TrialPeriod> trialPeriods = new ArrayList<>();
        trialPeriods.add(trialPeriod1);
        trialPeriods.add(trialPeriod2);

        Mockito.when(trialPeriodRepositoryMock.findAll()).thenReturn(trialPeriods);
        List<TrialPeriod> result = trialPeriodRepositoryMock.findAll();
        assertEquals(trialPeriods, result);
    }

}
