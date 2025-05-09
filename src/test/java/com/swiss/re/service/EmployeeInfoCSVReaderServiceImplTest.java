package com.swiss.re.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeInfoCSVReaderServiceImplTest {
    @Test
    void testReadEmployeeInfo() {
        // Arrange, Act and Assert
        assertTrue((new EmployeeInfoCSVReaderServiceImpl()).readEmployeeInfo("/directory/foo.txt").isEmpty());
    }
}
