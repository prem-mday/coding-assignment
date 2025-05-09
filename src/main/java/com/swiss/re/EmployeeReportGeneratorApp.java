package com.swiss.re;

import com.swiss.re.service.EmployeeReportGeneratorServiceImpl;

/**
 * Entry point for the Employee Report Generator application.
 * <p>
 * This class initializes the {@link EmployeeReportGeneratorServiceImpl} and triggers the
 * generation of the employee information report. The report includes:
 * <ul>
 *     <li>Salary discrepancies based on subordinate averages</li>
 *     <li>Deep reporting lines that exceed organizational thresholds</li>
 * </ul>
 * The output is printed to the console.
 */
public class EmployeeReportGeneratorApp {

    /**
     * Main method used to start the application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new EmployeeReportGeneratorServiceImpl().generateEmployeeInformationReport();
    }
}
