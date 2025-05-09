package com.swiss.re.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EmployeeInfoCSVReaderServiceImplTest {
    @Test
    void testReadEmployeeInfo() {
        // Arrange, Act and Assert
        assertTrue((new EmployeeInfoCSVReaderServiceImpl()).readEmployeeInfo("/directory/foo.txt").isEmpty());
    }
}
