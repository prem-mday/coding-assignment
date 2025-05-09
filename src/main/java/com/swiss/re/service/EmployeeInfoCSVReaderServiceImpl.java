package com.swiss.re.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.swiss.re.model.Employee;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

public class EmployeeInfoCSVReaderServiceImpl implements EmployeeInfoReaderService {

    @Override
    public List<Employee> readEmployeeInfo(String filePath) {
        System.out.printf("Request received to read file: %s%n", filePath);
        List<Employee> employeesInformation = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            csvReader.skip(1); // Skip header
            for (String[] row : csvReader.readAll()) {
                Employee employeeInfo = new Employee();
                employeeInfo.setId(parseInt(row[0]));
                employeeInfo.setFirstName(row[1]);
                employeeInfo.setLastName(row[2]);
                employeeInfo.setSalary(parseInt(row[3]));
                employeeInfo.setManagerId((row[4].length() == 0) ? null : parseInt(row[4]));
                employeesInformation.add(employeeInfo);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        System.out.printf("Returning employees information: %s%n", employeesInformation);

        return employeesInformation;
    }
}
