package com.swiss.re.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
    }

    @Test
    void testIdGetterSetter() {
        employee.setId(1001);
        assertEquals(1001, employee.getId());
    }

    @Test
    void testFirstNameGetterSetter() {
        employee.setFirstName("John");
        assertEquals("John", employee.getFirstName());
    }

    @Test
    void testLastNameGetterSetter() {
        employee.setLastName("Doe");
        assertEquals("Doe", employee.getLastName());
    }

    @Test
    void testSalaryGetterSetter() {
        employee.setSalary(75000.50);
        assertEquals(75000.50, employee.getSalary());
    }

    @Test
    void testManagerIdGetterSetter() {
        employee.setManagerId(200);
        assertEquals(200, employee.getManagerId());
    }

    @Test
    void testSubordinatesGetterSetter() {
        Employee subordinate1 = new Employee();
        subordinate1.setId(2);
        Employee subordinate2 = new Employee();
        subordinate2.setId(3);

        List<Employee> subordinates = Arrays.asList(subordinate1, subordinate2);
        employee.setSubordinates(subordinates);

        assertEquals(2, employee.getSubordinates().size());
        assertEquals(2, employee.getSubordinates().get(0).getId());
        assertEquals(3, employee.getSubordinates().get(1).getId());
    }

    @Test
    void testToStringNotNull() {
        employee.setId(1);
        employee.setFirstName("Jane");
        employee.setLastName("Smith");
        employee.setSalary(65000);
        employee.setManagerId(null);
        assertNotNull(employee.toString());
        assertTrue(employee.toString().contains("Jane"));
        assertTrue(employee.toString().contains("Smith"));
    }
}

