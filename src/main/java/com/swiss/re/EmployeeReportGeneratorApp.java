package com.swiss.re;

import com.swiss.re.model.Employee;
import com.swiss.re.service.EmployeeInfoAnalyzerService;
import com.swiss.re.service.EmployeeInfoAnalyzerServiceImpl;
import com.swiss.re.service.EmployeeInfoCSVReaderServiceImpl;
import com.swiss.re.service.EmployeeInfoReaderService;

import java.util.List;
import java.util.Map;

public class EmployeeReportGeneratorApp {
    private static final String EMPLOYEE_INFO_CSV_FILE_PATH = "./src/main/resources/employee-info.csv";

    private static final EmployeeInfoReaderService empInfoReaderSvc = new EmployeeInfoCSVReaderServiceImpl();
    private static final EmployeeInfoAnalyzerService empInfoAnalyzerSvc = new EmployeeInfoAnalyzerServiceImpl();

    public static void main(String[] args) {
        List<Employee> employeesInformation = empInfoReaderSvc.readEmployeeInfo(EMPLOYEE_INFO_CSV_FILE_PATH);
        Employee employeeWithHierarchy = empInfoAnalyzerSvc.createEmployeeHierarchy(employeesInformation);
        Map<Integer, Double> directSubordinatesAvgSalaries =
                empInfoAnalyzerSvc.creatingDirectSubordinatesAvgSalariesMapping(employeeWithHierarchy);
        empInfoAnalyzerSvc.analyzeSalaries(employeeWithHierarchy, directSubordinatesAvgSalaries);
        empInfoAnalyzerSvc.analyzeReportingLine(employeeWithHierarchy, 0);
    }
}
