package com.swiss.re.service;

import com.swiss.re.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toMap;

public class EmployeeInfoAnalyzerServiceImpl implements EmployeeInfoAnalyzerService {

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
        System.out.printf("Employee hierarchy created: %s%n", employeeHierarchy);

        return employeeHierarchy;
    }

    @Override
    public Map<Integer, Double> creatingDirectSubordinatesAvgSalariesMapping(Employee ceo) {
        Map<Integer, Double> averageSalariesMapping = new HashMap<>();
        calculateAverageSalaries(ceo, averageSalariesMapping);

        return averageSalariesMapping;
    }

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
    public void analyzeSalaries(Employee employee, Map<Integer, Double> subordinatesAvgSalaries) {
        if (employee.getSubordinates().isEmpty()) {
            return;
        }
        double averageSalary = subordinatesAvgSalaries.get(employee.getId());
        double minSalary = averageSalary * 1.2;
        double maxSalary = averageSalary * 1.5;

        if (employee.getSalary() < minSalary) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName() + " earns less than they should by " + (minSalary - employee.getSalary()));
        } else if (employee.getSalary() > maxSalary) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName() + " earns more than they should by " + (employee.getSalary() - maxSalary));
        }

        for (Employee subordinate : employee.getSubordinates()) {
            analyzeSalaries(subordinate, subordinatesAvgSalaries);
        }
    }

    @Override
    public void analyzeReportingLine(Employee employee, int depth) {
        if (depth > 4) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName() + " has a reporting line which is too long by " + (depth - 4));
        }

        for (Employee subordinate : employee.getSubordinates()) {
            analyzeReportingLine(subordinate, depth + 1);
        }
    }
}
