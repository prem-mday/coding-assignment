package com.swiss.re.service;

/**
 * Service interface for generating a report on employee information.
 * <p>
 * Implementations of this interface are responsible for orchestrating the workflow
 * of reading employee data, analyzing it, and producing a formatted report.
 */
public interface EmployeeReportGeneratorService {
    /**
     * Generates and prints a comprehensive employee information report.
     * <p>
     * The report may include analysis of organizational hierarchy,
     * salary discrepancies, and reporting line depth.
     */
    void generateEmployeeInformationReport();
}
