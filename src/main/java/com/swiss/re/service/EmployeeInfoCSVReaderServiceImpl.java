package com.swiss.re.service;

import com.opencsv.CSVReader;
import com.swiss.re.model.Employee;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static com.swiss.re.constant.EmployeeReportConstant.*;
import static java.lang.Integer.parseInt;

/**
 * Implementation of the {@link EmployeeInfoReaderService} that reads employee data from a CSV file.
 * <p>
 * This class uses the OpenCSV library to parse the file and create a list of {@link Employee} objects.
 */
public class EmployeeInfoCSVReaderServiceImpl implements EmployeeInfoReaderService {

    /**
     * Reads employee information from the specified CSV file and returns it as a list of {@link Employee} objects.
     * <p>
     * Each row in the CSV file represents an employee. The method skips the header row and parses each subsequent row
     * based on fixed column indices defined in {@link com.swiss.re.constant.EmployeeReportConstant}.
     * <p>
     * If the manager ID column is empty, the employee is assumed to have no manager (e.g., a CEO).
     *
     * @param filePath The path to the CSV file containing employee data
     * @return A list of employees parsed from the file; returns an empty list if the file cannot be read
     */
    @Override
    public List<Employee> readEmployeeInfo(String filePath) {
        List<Employee> employeesInformation = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            csvReader.skip(1); // Skip header
            for (String[] row : csvReader.readAll()) {
                String managerIdCol = row[MANAGER_COL_INDEX];
                Employee employeeInfo = new Employee();
                employeeInfo.setId(parseInt(row[ID_COL_INDEX]));
                employeeInfo.setFirstName(row[FIRST_NAME_COL_INDEX]);
                employeeInfo.setLastName(row[LAST_NAME_COL_INDEX]);
                employeeInfo.setSalary(parseInt(row[SALARY_COL_INDEX]));
                employeeInfo.setManagerId((managerIdCol.isEmpty()) ? null : parseInt(managerIdCol));
                employeesInformation.add(employeeInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeesInformation;
    }
}
