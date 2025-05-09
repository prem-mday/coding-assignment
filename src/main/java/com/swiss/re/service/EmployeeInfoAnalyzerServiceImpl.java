package com.swiss.re.service;

import com.swiss.re.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.swiss.re.constant.EmployeeReportConstant.*;
import static java.lang.System.lineSeparator;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toMap;

public class EmployeeInfoAnalyzerServiceImpl implements EmployeeInfoAnalyzerService {

    // Calculating the average salaries of direct subordinates here
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

    @Override
    public Employee createEmployeeHierarchy(List<Employee> employees) {
        Employee employeeHierarchy = null;
        Map<Integer, Employee> employeesMapping = employees.stream().collect(toMap(e -> e.getId(), e -> e));
        // Updating employees with their subordinates
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

    @Override
    public Map<Integer, Double> creatingDirectSubordinatesAvgSalariesMapping(Employee ceo) {
        Map<Integer, Double> averageSalariesMapping = new HashMap<>();
        calculateAverageSalaries(ceo, averageSalariesMapping);

        return averageSalariesMapping;
    }

    @Override
    public void analyzeSalaries(Employee employee, Map<Integer, Double> subordinatesAvgSalaries, StringBuilder msg) {
        if (employee.getSubordinates().isEmpty()) {
            return;
        }
        double averageSalary = subordinatesAvgSalaries.get(employee.getId());
        double minSalary = averageSalary * 1.2;
        double maxSalary = averageSalary * 1.5;
        if (employee.getSalary() < minSalary) {
            msg.append(SALARY_VARIATION_MSG.formatted(employee.getFirstName(), employee.getLastName(), SALARY_LESS, (minSalary - employee.getSalary()))).append(lineSeparator());
        } else if (employee.getSalary() > maxSalary) {
            msg.append(SALARY_VARIATION_MSG.formatted(employee.getFirstName(), employee.getLastName(), SALARY_MORE, (employee.getSalary() - maxSalary))).append(lineSeparator());
        }
        for (Employee subordinate : employee.getSubordinates()) {
            analyzeSalaries(subordinate, subordinatesAvgSalaries, msg);
        }
    }

    @Override
    public void analyzeReportingLine(Employee employee, int depth, StringBuilder reportingLineMsg) {
        if (depth > REPORTING_LINE_THRESHOLD) {
            reportingLineMsg.append(LONG_REPORTING_LINE_MSG.formatted(employee.getFirstName(), employee.getLastName(), (depth - REPORTING_LINE_THRESHOLD))).append(lineSeparator());
        }
        for (Employee subordinate : employee.getSubordinates()) {
            analyzeReportingLine(subordinate, depth + 1, reportingLineMsg);
        }
    }
}
