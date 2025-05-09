package com.swiss.re.service;

import com.swiss.re.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeInfoAnalyzerService {
    Employee createEmployeeHierarchy(List<Employee> employeeList);

    Map<Integer, Double> creatingDirectSubordinatesAvgSalariesMapping(Employee ceo);

    void analyzeSalaries(Employee ceo, Map<Integer, Double> subordinatesAvgSalaries, StringBuilder salaryCorrectionMsg);

    void analyzeReportingLine(Employee ceo, int depth, StringBuilder reportingLineMsg);
}
