package com.swiss.re.service;

import com.swiss.re.model.Employee;

import java.util.List;
import java.util.Map;

/**
 * Service interface for analyzing employee data, including hierarchy construction,
 * salary evaluation, and reporting structure analysis.
 */
public interface EmployeeInfoAnalyzerService {

    /**
     * Constructs the hierarchical organization tree from a flat list of employees.
     * <p>
     * The employee without a manager (i.e., with a null manager ID) is treated as the root (typically the CEO).
     *
     * @param employeeList A list of all employees
     * @return The root employee of the hierarchy
     */
    Employee createEmployeeHierarchy(List<Employee> employeeList);

    /**
     * Computes the average salary of direct subordinates for each employee in the hierarchy.
     *
     * @param ceo The root of the organizational hierarchy
     * @return A map where keys are employee IDs and values are the average salary of their direct subordinates
     */
    Map<Integer, Double> creatingDirectSubordinatesAvgSalariesMapping(Employee ceo);

    /**
     * Evaluates whether employees' salaries are within acceptable bounds compared to the average salaries
     * of their direct subordinates, and appends messages for any violations to the given StringBuilder.
     *
     * @param ceo                     The root of the employee hierarchy
     * @param subordinatesAvgSalaries Map of employee IDs to average subordinate salaries
     * @param salaryCorrectionMsg     A StringBuilder for collecting salary discrepancy messages
     */
    void analyzeSalaries(Employee ceo, Map<Integer, Double> subordinatesAvgSalaries, StringBuilder salaryCorrectionMsg);

    /**
     * Analyzes the depth of the reporting line for each employee and identifies cases where
     * the depth exceeds a predefined threshold. Messages are appended to the provided StringBuilder.
     *
     * @param ceo              The root of the employee hierarchy
     * @param depth            The current depth in the reporting chain (start with 0)
     * @param reportingLineMsg A StringBuilder for collecting long reporting line messages
     */
    void analyzeReportingLine(Employee ceo, int depth, StringBuilder reportingLineMsg);
}
