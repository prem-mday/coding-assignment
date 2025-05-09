package com.swiss.re.service;

import com.swiss.re.model.Employee;

import java.util.List;

public interface EmployeeInfoReaderService {
    List<Employee> readEmployeeInfo(String filePath);
}
