package com.swiss.re.service;

import com.swiss.re.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class EmployeeReportGeneratorServiceImplTest {

    @Mock
    private EmployeeInfoReaderService empInfoReaderSvc;

    @Mock
    private EmployeeInfoAnalyzerService empInfoAnalyzerSvc;

    @InjectMocks
    private EmployeeReportGeneratorServiceImpl reportGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reportGenerator = new EmployeeReportGeneratorServiceImpl();
    }

    @Test
    void testGenerateEmployeeInformationReport() {
        String filePath = "./src/test/resources/employee-info.csv";
        // Setup mock behavior
        List<Employee> mockEmployeeList = new ArrayList<>();
        Employee mockRootEmployee = new Employee();
        Map<Integer, Double> avgSalaryMap = new HashMap<>();

        when(empInfoReaderSvc.readEmployeeInfo(anyString())).thenReturn(mockEmployeeList);
        when(empInfoAnalyzerSvc.createEmployeeHierarchy(mockEmployeeList)).thenReturn(mockRootEmployee);
        when(empInfoAnalyzerSvc.creatingDirectSubordinatesAvgSalariesMapping(mockRootEmployee)).thenReturn(avgSalaryMap);

        // Using doNothing() since these methods have void return type
        doNothing().when(empInfoAnalyzerSvc).analyzeSalaries(any(), any(), any());
        doNothing().when(empInfoAnalyzerSvc).analyzeReportingLine(any(), anyInt(), any());

        // Execute the method
        assertDoesNotThrow(() -> reportGenerator.generateEmployeeInformationReport());
    }
}
