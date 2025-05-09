package com.swiss.re.service;

import com.swiss.re.model.Employee;

import java.util.List;
import java.util.Map;

import static com.swiss.re.constant.EmployeeReportConstant.*;

/**
 * Implementation of the {@link EmployeeReportGeneratorService} interface.
 * <p>
 * This service coordinates the process of reading employee data, analyzing it,
 * and generating a comprehensive report that includes:
 * <ul>
 *     <li>Salary analysis: Detects if a manager's salary is too low or too high compared to their subordinates</li>
 *     <li>Reporting line analysis: Identifies overly deep reporting structures</li>
 * </ul>
 * The final report is printed to the console.
 */
public class EmployeeReportGeneratorServiceImpl implements EmployeeReportGeneratorService {
    /**
     * Service responsible for reading employee information from a CSV source.
     */
    private EmployeeInfoReaderService empInfoReaderSvc = new EmployeeInfoCSVReaderServiceImpl();

    /**
     * Service responsible for analyzing employee hierarchy and salary structure.
     */
    private EmployeeInfoAnalyzerService empInfoAnalyzerSvc = new EmployeeInfoAnalyzerServiceImpl();

    /**
     * Generates and prints a detailed employee information report.
     * <p>
     * This includes:
     * <ol>
     *     <li>Reading employee data from a CSV file</li>
     *     <li>Building a hierarchy of employees</li>
     *     <li>Calculating average subordinate salaries</li>
     *     <li>Analyzing salary variations</li>
     *     <li>Analyzing reporting line depth</li>
     *     <li>Printing the final report to the console</li>
     * </ol>
     */
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

    /**
     * Prints the final employee report to the console, including salary issues and reporting line warnings.
     *
     * @param salaryCorrectionMsg A StringBuilder containing salary analysis results
     * @param reportingLineMsg    A StringBuilder containing reporting line depth analysis
     */
    private void printReport(StringBuilder salaryCorrectionMsg, StringBuilder reportingLineMsg) {
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
