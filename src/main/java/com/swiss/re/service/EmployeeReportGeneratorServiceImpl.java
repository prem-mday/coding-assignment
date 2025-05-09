package com.swiss.re.service;

import com.swiss.re.model.Employee;

import java.util.List;
import java.util.Map;

import static com.swiss.re.constant.EmployeeReportConstant.*;

public class EmployeeReportGeneratorServiceImpl implements EmployeeReportGeneratorService {
    EmployeeInfoReaderService empInfoReaderSvc = new EmployeeInfoCSVReaderServiceImpl();
    EmployeeInfoAnalyzerService empInfoAnalyzerSvc = new EmployeeInfoAnalyzerServiceImpl();

    @Override
    public void generateEmployeeInformationReport() {
        List<Employee> employeesInformation = empInfoReaderSvc.readEmployeeInfo(EMPLOYEE_INFO_CSV_FILE_PATH);
        Employee employeeWithHierarchy = empInfoAnalyzerSvc.createEmployeeHierarchy(employeesInformation);
        Map<Integer, Double> directSubordinatesAvgSalaries =
                empInfoAnalyzerSvc.creatingDirectSubordinatesAvgSalariesMapping(employeeWithHierarchy);
        StringBuilder salaryCorrectionMsg = new StringBuilder();
        empInfoAnalyzerSvc.analyzeSalaries(employeeWithHierarchy, directSubordinatesAvgSalaries, salaryCorrectionMsg);
        StringBuilder reportingLineMsg = new StringBuilder();
        empInfoAnalyzerSvc.analyzeReportingLine(employeeWithHierarchy, 0, reportingLineMsg);

        printReport(salaryCorrectionMsg, reportingLineMsg);
    }

    private void printReport(StringBuilder salaryCorrectionMsg, StringBuilder reportingLineMsg) {
        // PRINTING THE REPORT HERE //
        System.out.println(SEPARATOR_SYMBOL);
        System.out.println(SALARY_IMPROVEMENT_REPORT);
        System.out.println(SEPARATOR_SYMBOL);
        System.out.println(salaryCorrectionMsg);
        System.out.println(SEPARATOR_SYMBOL);
        System.out.println(REPORTING_LINE_REPORT);
        System.out.println(SEPARATOR_SYMBOL);
        System.out.println(reportingLineMsg);
    }
}
