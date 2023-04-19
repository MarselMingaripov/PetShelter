package com.example.petshelter.service;

import com.example.petshelter.entity.Report;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.repository.ReportRepository;
import com.example.petshelter.service.impl.ReportServiceImpl;
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
public class ReportServiceTest {
    @Mock
    private ReportRepository reportRepositoryMock;
    @Mock
    private ValidationService validationServiceMock;
    @InjectMocks
    private ReportServiceImpl reportServiceOut;

    private final static Long ID = 1L;
    private byte[] PHOTO;
    private String FOOD_RATION = "foodRation";
    private String GENERAL_HEALTH = "generalHealth";
    private String BEHAVIOR_CHANGES = "behaviorChanges";

    private Report report;

    @BeforeEach
    public void init() {
        report = new Report(ID, PHOTO, FOOD_RATION, GENERAL_HEALTH, BEHAVIOR_CHANGES);
    }

    @Test
    @DisplayName("Проверка корректности создания нового отчета")
    void shouldReturnWhenCreateReport() {
        Mockito.when(validationServiceMock.validate(report)).thenReturn(true);
        Mockito.when(reportRepositoryMock.save(any())).thenReturn(report);
        assertEquals(report, reportServiceOut.createReport(report));
    }

    @Test
    @DisplayName("Поиск и обновление отчета по его Id")
    public void shouldFindAndUpdateCorrectReport() {
        Mockito.when(reportRepositoryMock.findById(ID)).thenReturn(Optional.of(report));
        Mockito.when(reportRepositoryMock.save(any())).thenReturn(report);
        assertEquals(report, reportServiceOut.updateById(ID, report));

    }
    @Test
    @DisplayName("Исключение при поиске отчета по некорректному ID")
    public void shouldThrowNotFoundInBdExceptionWhenIdIsNotValid() {
        Mockito.when(reportRepositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundInBdException.class, () -> reportServiceOut.findById(ID));
    }
    @Test
    @DisplayName("Вывод списка всех отчетов")
    public void shouldFindByAllCorrectReport() {
        Report report1 = new Report(1L, PHOTO, FOOD_RATION, GENERAL_HEALTH, BEHAVIOR_CHANGES);
        Report report2 = new Report(2L, PHOTO, FOOD_RATION, GENERAL_HEALTH, BEHAVIOR_CHANGES);
        List<Report> reports = new ArrayList<>();
        reports.add(report1);
        reports.add(report2);

        Mockito.when(reportRepositoryMock.findAll()).thenReturn(reports);
        List<Report> result = reportRepositoryMock.findAll();
        assertEquals(reports, result);
    }

}
