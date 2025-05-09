package com.swiss.re.service;

import com.swiss.re.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeInfoAnalyzerServiceImplTest {
    @Test
    void testCreateEmployeeHierarchy_givenEmployeeFirstNameIsJohn() {
        // Arrange
        EmployeeInfoAnalyzerServiceImpl employeeInfoAnalyzerServiceImpl = new EmployeeInfoAnalyzerServiceImpl();

        Employee employee = new Employee();
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        employee.setManagerId(null);
        employee.setSalary(10.0d);
        employee.setSubordinates(new ArrayList<>());

        Employee employee2 = new Employee();
        employee2.setFirstName("John");
        employee2.setId(2);
        employee2.setLastName("Smith");
        employee2.setManagerId(1);
        employee2.setSalary(0.5d);
        employee2.setSubordinates(new ArrayList<>());

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee2);
        employees.add(employee);

        // Act
        employeeInfoAnalyzerServiceImpl.createEmployeeHierarchy(employees);
    }

    @Test
    void testCreateEmployeeHierarchy_givenEmployeeFirstNameIsJohn_thenArrayListSizeIsTwo() {
        // Arrange
        EmployeeInfoAnalyzerServiceImpl employeeInfoAnalyzerServiceImpl = new EmployeeInfoAnalyzerServiceImpl();

        Employee employee = new Employee();
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        employee.setManagerId(1);
        employee.setSalary(10.0d);
        ArrayList<Employee> subordinates = new ArrayList<>();
        employee.setSubordinates(subordinates);

        Employee employee2 = new Employee();
        employee2.setFirstName("John");
        employee2.setId(2);
        employee2.setLastName("Smith");
        employee2.setManagerId(2);
        employee2.setSalary(0.5d);
        ArrayList<Employee> subordinates2 = new ArrayList<>();
        employee2.setSubordinates(subordinates2);

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee2);
        employees.add(employee);

        // Act
        employeeInfoAnalyzerServiceImpl.createEmployeeHierarchy(employees);

        // Assert
        assertEquals(2, employees.size());
        List<Employee> subordinates3 = employees.get(0).getSubordinates();
        assertEquals(1, subordinates3.size());
        List<Employee> subordinates4 = employees.get(1).getSubordinates();
        assertEquals(1, subordinates4.size());
        assertSame(employee2, subordinates3.get(0));
        assertSame(employee, subordinates4.get(0));
        assertSame(subordinates2, subordinates3);
        assertSame(subordinates, subordinates4);
    }

    @Test
    void testCreateEmployeeHierarchy_givenEmployeeIdIsOne_thenArrayListSizeIsOne() {
        // Arrange
        EmployeeInfoAnalyzerServiceImpl employeeInfoAnalyzerServiceImpl = new EmployeeInfoAnalyzerServiceImpl();

        Employee employee = new Employee();
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        employee.setManagerId(1);
        employee.setSalary(10.0d);
        ArrayList<Employee> subordinates = new ArrayList<>();
        employee.setSubordinates(subordinates);

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);

        // Act
        employeeInfoAnalyzerServiceImpl.createEmployeeHierarchy(employees);

        // Assert
        assertEquals(1, employees.size());
        List<Employee> subordinates2 = employees.get(0).getSubordinates();
        assertEquals(1, subordinates2.size());
        assertSame(employee, subordinates2.get(0));
        assertSame(subordinates, subordinates2);
    }

    @Test
    void testCreateEmployeeHierarchy_givenEmployeeIdIsTwo_whenArrayListAddEmployee() {
        // Arrange
        EmployeeInfoAnalyzerServiceImpl employeeInfoAnalyzerServiceImpl = new EmployeeInfoAnalyzerServiceImpl();
        Employee employee = new Employee();
        employee.setFirstName("Jane");
        employee.setId(2);
        employee.setLastName("Doe");
        employee.setManagerId(null);
        employee.setSalary(10.0d);
        employee.setSubordinates(new ArrayList<>());
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        // Act
        Employee employeeHierarchy = employeeInfoAnalyzerServiceImpl.createEmployeeHierarchy(employees);

        //verify
        assertNotNull(employeeHierarchy);
    }

    @Test
    void testCreateEmployeeHierarchy_whenArrayList_thenReturnNull() {
        // Arrange
        EmployeeInfoAnalyzerServiceImpl employeeInfoAnalyzerServiceImpl = new EmployeeInfoAnalyzerServiceImpl();

        // Act and Assert
        assertNull(employeeInfoAnalyzerServiceImpl.createEmployeeHierarchy(new ArrayList<>()));
    }

    @Test
    void testCreatingDirectSubordinatesAvgSalariesMapping_givenArrayList_thenReturnEmpty() {
        // Arrange
        EmployeeInfoAnalyzerServiceImpl employeeInfoAnalyzerServiceImpl = new EmployeeInfoAnalyzerServiceImpl();

        Employee ceo = new Employee();
        ceo.setFirstName("Jane");
        ceo.setId(1);
        ceo.setLastName("Doe");
        ceo.setManagerId(1);
        ceo.setSalary(10.0d);
        ceo.setSubordinates(new ArrayList<>());

        // Act and Assert
        assertTrue(employeeInfoAnalyzerServiceImpl.creatingDirectSubordinatesAvgSalariesMapping(ceo).isEmpty());
    }

    @Test
    void testCreatingDirectSubordinatesAvgSalariesMapping_thenReturnSizeIsOne() {
        // Arrange
        EmployeeInfoAnalyzerServiceImpl employeeInfoAnalyzerServiceImpl = new EmployeeInfoAnalyzerServiceImpl();
        Employee employee = new Employee();
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        employee.setManagerId(1);
        employee.setSalary(10.0d);
        employee.setSubordinates(new ArrayList<>());
        ArrayList<Employee> subordinates = new ArrayList<>();
        subordinates.add(employee);
        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setId(1);
        employee2.setLastName("Doe");
        employee2.setManagerId(1);
        employee2.setSalary(10.0d);
        employee2.setSubordinates(subordinates);
        ArrayList<Employee> subordinates2 = new ArrayList<>();
        subordinates2.add(employee2);
        Employee employee3 = new Employee();
        employee3.setFirstName("Jane");
        employee3.setId(1);
        employee3.setLastName("Doe");
        employee3.setManagerId(1);
        employee3.setSalary(10.0d);
        employee3.setSubordinates(subordinates2);
        ArrayList<Employee> subordinates3 = new ArrayList<>();
        subordinates3.add(employee3);
        Employee employee4 = new Employee();
        employee4.setFirstName("Jane");
        employee4.setId(1);
        employee4.setLastName("Doe");
        employee4.setManagerId(1);
        employee4.setSalary(10.0d);
        employee4.setSubordinates(subordinates3);
        ArrayList<Employee> subordinates4 = new ArrayList<>();
        subordinates4.add(employee4);
        Employee employee5 = new Employee();
        employee5.setFirstName("Jane");
        employee5.setId(1);
        employee5.setLastName("Doe");
        employee5.setManagerId(1);
        employee5.setSalary(10.0d);
        employee5.setSubordinates(subordinates4);
        ArrayList<Employee> subordinates5 = new ArrayList<>();
        subordinates5.add(employee5);
        Employee employee6 = new Employee();
        employee6.setFirstName("Jane");
        employee6.setId(1);
        employee6.setLastName("Doe");
        employee6.setManagerId(1);
        employee6.setSalary(10.0d);
        employee6.setSubordinates(subordinates5);
        ArrayList<Employee> subordinates6 = new ArrayList<>();
        subordinates6.add(employee6);
        Employee employee7 = new Employee();
        employee7.setFirstName("Jane");
        employee7.setId(1);
        employee7.setLastName("Doe");
        employee7.setManagerId(1);
        employee7.setSalary(10.0d);
        employee7.setSubordinates(subordinates6);
        ArrayList<Employee> subordinates7 = new ArrayList<>();
        subordinates7.add(employee7);
        Employee employee8 = new Employee();
        employee8.setFirstName("Jane");
        employee8.setId(1);
        employee8.setLastName("Doe");
        employee8.setManagerId(1);
        employee8.setSalary(10.0d);
        employee8.setSubordinates(subordinates7);
        ArrayList<Employee> subordinates8 = new ArrayList<>();
        subordinates8.add(employee8);
        Employee ceo = new Employee();
        ceo.setFirstName("Jane");
        ceo.setId(1);
        ceo.setLastName("Doe");
        ceo.setManagerId(1);
        ceo.setSalary(10.0d);
        ceo.setSubordinates(subordinates8);
        // Act
        Map<Integer, Double> actualCreatingDirectSubordinatesAvgSalariesMappingResult = employeeInfoAnalyzerServiceImpl
                .creatingDirectSubordinatesAvgSalariesMapping(ceo);
        // Assert
        assertEquals(1, actualCreatingDirectSubordinatesAvgSalariesMappingResult.size());
        assertEquals(10.0d, actualCreatingDirectSubordinatesAvgSalariesMappingResult.get(1).doubleValue());
    }

    @Test
    void testAnalyzeSalaries_givenArrayList() {
        // Arrange
        EmployeeInfoAnalyzerServiceImpl employeeInfoAnalyzerServiceImpl = new EmployeeInfoAnalyzerServiceImpl();

        Employee employee = new Employee();
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        employee.setManagerId(1);
        employee.setSalary(10.0d);
        employee.setSubordinates(new ArrayList<>());
        HashMap<Integer, Double> subordinatesAvgSalaries = new HashMap<>();

        // Act
        employeeInfoAnalyzerServiceImpl.analyzeSalaries(employee, subordinatesAvgSalaries, new StringBuilder("foo"));
    }

    @Test
    void testAnalyzeReportingLine_givenArrayList_thenStringBuilderWithFooToStringIsFoo() {
        // Arrange
        EmployeeInfoAnalyzerServiceImpl employeeInfoAnalyzerServiceImpl = new EmployeeInfoAnalyzerServiceImpl();

        Employee employee = new Employee();
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        employee.setManagerId(1);
        employee.setSalary(10.0d);
        employee.setSubordinates(new ArrayList<>());
        StringBuilder reportingLineMsg = new StringBuilder("foo");

        // Act
        employeeInfoAnalyzerServiceImpl.analyzeReportingLine(employee, 2, reportingLineMsg);

        // Assert that nothing has changed
        assertEquals("foo", reportingLineMsg.toString());
    }

    @Test
    void testAnalyzeReportingLine_thenStringBuilderWithFooToStringIsAString() {
        // Arrange
        EmployeeInfoAnalyzerServiceImpl employeeInfoAnalyzerServiceImpl = new EmployeeInfoAnalyzerServiceImpl();

        Employee employee = new Employee();
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        employee.setManagerId(1);
        employee.setSalary(10.0d);
        employee.setSubordinates(new ArrayList<>());

        ArrayList<Employee> subordinates = new ArrayList<>();
        subordinates.add(employee);

        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setId(1);
        employee2.setLastName("Doe");
        employee2.setManagerId(1);
        employee2.setSalary(10.0d);
        employee2.setSubordinates(subordinates);

        ArrayList<Employee> subordinates2 = new ArrayList<>();
        subordinates2.add(employee2);

        Employee employee3 = new Employee();
        employee3.setFirstName("Jane");
        employee3.setId(1);
        employee3.setLastName("Doe");
        employee3.setManagerId(1);
        employee3.setSalary(10.0d);
        employee3.setSubordinates(subordinates2);

        ArrayList<Employee> subordinates3 = new ArrayList<>();
        subordinates3.add(employee3);

        Employee employee4 = new Employee();
        employee4.setFirstName("Jane");
        employee4.setId(1);
        employee4.setLastName("Doe");
        employee4.setManagerId(1);
        employee4.setSalary(10.0d);
        employee4.setSubordinates(subordinates3);

        ArrayList<Employee> subordinates4 = new ArrayList<>();
        subordinates4.add(employee4);

        Employee employee5 = new Employee();
        employee5.setFirstName("Jane");
        employee5.setId(1);
        employee5.setLastName("Doe");
        employee5.setManagerId(1);
        employee5.setSalary(10.0d);
        employee5.setSubordinates(subordinates4);

        ArrayList<Employee> subordinates5 = new ArrayList<>();
        subordinates5.add(employee5);

        Employee employee6 = new Employee();
        employee6.setFirstName("Jane");
        employee6.setId(1);
        employee6.setLastName("Doe");
        employee6.setManagerId(1);
        employee6.setSalary(10.0d);
        employee6.setSubordinates(subordinates5);

        ArrayList<Employee> subordinates6 = new ArrayList<>();
        subordinates6.add(employee6);

        Employee employee7 = new Employee();
        employee7.setFirstName("Jane");
        employee7.setId(1);
        employee7.setLastName("Doe");
        employee7.setManagerId(1);
        employee7.setSalary(10.0d);
        employee7.setSubordinates(subordinates6);

        ArrayList<Employee> subordinates7 = new ArrayList<>();
        subordinates7.add(employee7);

        Employee employee8 = new Employee();
        employee8.setFirstName("Jane");
        employee8.setId(1);
        employee8.setLastName("Doe");
        employee8.setManagerId(1);
        employee8.setSalary(10.0d);
        employee8.setSubordinates(subordinates7);

        ArrayList<Employee> subordinates8 = new ArrayList<>();
        subordinates8.add(employee8);

        Employee employee9 = new Employee();
        employee9.setFirstName("Jane");
        employee9.setId(1);
        employee9.setLastName("Doe");
        employee9.setManagerId(1);
        employee9.setSalary(10.0d);
        employee9.setSubordinates(subordinates8);

        ArrayList<Employee> subordinates9 = new ArrayList<>();
        subordinates9.add(employee9);

        Employee employee10 = new Employee();
        employee10.setFirstName("Jane");
        employee10.setId(1);
        employee10.setLastName("Doe");
        employee10.setManagerId(1);
        employee10.setSalary(10.0d);
        employee10.setSubordinates(subordinates9);
        StringBuilder reportingLineMsg = new StringBuilder("foo");

        // Act
        employeeInfoAnalyzerServiceImpl.analyzeReportingLine(employee10, 4, reportingLineMsg);

        // Assert
        assertEquals("fooEmployee: Jane Doe, has a reporting line which is too long, and by: 1.\n"
                + "Employee: Jane Doe, has a reporting line which is too long, and by: 2.\n"
                + "Employee: Jane Doe, has a reporting line which is too long, and by: 3.\n"
                + "Employee: Jane Doe, has a reporting line which is too long, and by: 4.\n"
                + "Employee: Jane Doe, has a reporting line which is too long, and by: 5.\n"
                + "Employee: Jane Doe, has a reporting line which is too long, and by: 6.\n"
                + "Employee: Jane Doe, has a reporting line which is too long, and by: 7.\n"
                + "Employee: Jane Doe, has a reporting line which is too long, and by: 8.\n"
                + "Employee: Jane Doe, has a reporting line which is too long, and by: 9.\n", reportingLineMsg.toString());
    }
}
