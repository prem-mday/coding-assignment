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

        // Verify method calls
        //verify(empInfoReaderSvc).readEmployeeInfo(anyString());
        //verify(empInfoAnalyzerSvc).createEmployeeHierarchy(mockEmployeeList);
        //verify(empInfoAnalyzerSvc).creatingDirectSubordinatesAvgSalariesMapping(mockRootEmployee);
        //verify(empInfoAnalyzerSvc).analyzeSalaries(eq(mockRootEmployee), eq(avgSalaryMap), any());
        //verify(empInfoAnalyzerSvc).analyzeReportingLine(eq(mockRootEmployee), eq(0), any());
    }
}
