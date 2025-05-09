package com.swiss.re.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.swiss.re.model.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.swiss.re.constant.EmployeeReportConstant.*;
import static java.lang.Integer.parseInt;

public class EmployeeInfoCSVReaderServiceImpl implements EmployeeInfoReaderService {

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
                employeeInfo.setManagerId((managerIdCol.length() == 0) ? null : parseInt(managerIdCol));
                employeesInformation.add(employeeInfo);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return employeesInformation;
    }
}
