package com.swiss.re.service;

import com.swiss.re.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.swiss.re.constant.EmployeeReportConstant.*;
import static java.lang.System.lineSeparator;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toMap;

/**
 * Implementation of the {@link EmployeeInfoAnalyzerService} interface.
 * <p>
 * This class provides logic to:
 * <ul>
 *     <li>Create employee hierarchies</li>
 *     <li>Calculate average salaries of direct subordinates</li>
 *     <li>Analyze salary disparities between managers and subordinates</li>
 *     <li>Analyze long reporting lines exceeding a threshold depth</li>
 * </ul>
 */
public class EmployeeInfoAnalyzerServiceImpl implements EmployeeInfoAnalyzerService {

    /**
     * Recursively calculates and stores the average salary of direct subordinates for each employee.
     *
     * @param employee        The current employee being analyzed
     * @param averageSalaries A map to store the average salary of each employee's direct subordinates
     */
    private static void calculateAverageSalaries(Employee employee, Map<Integer, Double> averageSalaries) {
        List<Employee> subordinates = employee.getSubordinates();
        if (subordinates.isEmpty()) {
            return;
        }
        double totalSalary = subordinates.stream().mapToDouble(Employee::getSalary).sum();
        averageSalaries.put(employee.getId(), (totalSalary / subordinates.size()));
        for (Employee subordinate : subordinates) {
            calculateAverageSalaries(subordinate, averageSalaries);
        }
    }

    /**
     * Builds the organizational hierarchy from a flat list of employees.
     * <p>
     * The employee without a manager is considered the root (typically the CEO).
     *
     * @param employees A list of employees
     * @return The root employee of the hierarchy
     */
    @Override
    public Employee createEmployeeHierarchy(List<Employee> employees) {
        Employee employeeHierarchy = null;
        Map<Integer, Employee> employeesMapping = employees.stream().collect(toMap(e -> e.getId(), e -> e));
        for (Employee employee : employeesMapping.values()) {
            Integer managerId = employee.getManagerId();
            if (isNull(managerId)) {
                employeeHierarchy = employee;
            } else {
                employeesMapping.get(managerId).getSubordinates().add(employee);
            }
        }

        return employeeHierarchy;
    }

    /**
     * Creates a map containing the average salary of each employee's direct subordinates.
     *
     * @param ceo The root of the organizational hierarchy (typically the CEO)
     * @return A map of employee IDs to the average salary of their direct subordinates
     */
    @Override
    public Map<Integer, Double> creatingDirectSubordinatesAvgSalariesMapping(Employee ceo) {
        Map<Integer, Double> averageSalariesMapping = new HashMap<>();
        calculateAverageSalaries(ceo, averageSalariesMapping);

        return averageSalariesMapping;
    }

    /**
     * Analyzes whether an employee's salary is significantly below or above the average of their subordinates,
     * and appends appropriate messages to the report.
     *
     * @param employee                The employee being analyzed
     * @param subordinatesAvgSalaries A map of employee IDs to average subordinate salaries
     * @param msg                     A StringBuilder to append salary analysis messages
     */
    @Override
    public void analyzeSalaries(Employee employee, Map<Integer, Double> subordinatesAvgSalaries, StringBuilder msg) {
        int id = employee.getId();
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        double salary = employee.getSalary();
        List<Employee> subordinates = employee.getSubordinates();

        if (subordinates.isEmpty()) {
            return;
        }
        double averageSalary = subordinatesAvgSalaries.get(id);
        double minSalary = averageSalary * 1.2;
        double maxSalary = averageSalary * 1.5;
        if (salary < minSalary) {
            msg.append(SALARY_VARIATION_MSG.formatted(firstName, lastName, SALARY_LESS, (minSalary - salary)))
                    .append(lineSeparator());
        } else if (salary > maxSalary) {
            msg.append(SALARY_VARIATION_MSG.formatted(firstName, lastName, SALARY_MORE, (salary - maxSalary)))
                    .append(lineSeparator());
        }
        for (Employee subordinate : subordinates) {
            analyzeSalaries(subordinate, subordinatesAvgSalaries, msg);
        }
    }

    /**
     * Recursively analyzes the reporting line depth of each employee in the hierarchy.
     * <p>
     * If the reporting line depth exceeds the configured threshold, a message is added to the report.
     *
     * @param employee         The current employee in the hierarchy
     * @param depth            The current depth of the reporting chain
     * @param reportingLineMsg A StringBuilder to append reporting line messages
     */
    @Override
    public void analyzeReportingLine(Employee employee, int depth, StringBuilder reportingLineMsg) {
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        if (depth > REPORTING_LINE_THRESHOLD) {
            reportingLineMsg.append(LONG_REPORTING_LINE_MSG
                    .formatted(firstName, lastName, (depth - REPORTING_LINE_THRESHOLD))).append(lineSeparator());
        }
        for (Employee subordinate : employee.getSubordinates()) {
            analyzeReportingLine(subordinate, depth + 1, reportingLineMsg);
        }
    }
}
