package com.swiss.re.service;

import com.swiss.re.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeInfoCSVReaderServiceImplTest {
    @Test
    void testReadEmployeeInfo() {
        String filePath = "./src/test/resources/employee-info.csv";
        EmployeeInfoReaderService empInfoReaderSvc = new EmployeeInfoCSVReaderServiceImpl();
        List<Employee> employees = empInfoReaderSvc.readEmployeeInfo(filePath);
        assertTrue(!employees.isEmpty());
    }
}
