package com.swiss.re;

import com.swiss.re.service.EmployeeReportGeneratorServiceImpl;

public class EmployeeReportGeneratorApp {
    public static void main(String[] args) {
        new EmployeeReportGeneratorServiceImpl().generateEmployeeInformationReport();
    }
}
