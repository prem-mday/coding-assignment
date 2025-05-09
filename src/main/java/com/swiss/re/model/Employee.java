package com.swiss.re.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an employee in the organization.
 * <p>
 * An employee has an ID, a name (first and last), a salary, and a manager ID.
 * Additionally, each employee can have a list of subordinates, representing employees
 * that report directly to them.
 */
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private double salary;
    private Integer managerId;
    private List<Employee> subordinates = new ArrayList<>();

    /**
     * Gets the ID of the employee.
     *
     * @return the ID of the employee
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the employee.
     *
     * @param id The ID to set for the employee
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the employee.
     *
     * @return the first name of the employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee.
     *
     * @param firstName The first name to set for the employee
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the employee.
     *
     * @return the last name of the employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee.
     *
     * @param lastName The last name to set for the employee
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the salary of the employee.
     *
     * @return the salary of the employee
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the salary of the employee.
     *
     * @param salary The salary to set for the employee
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Gets the manager ID of the employee.
     *
     * @return the manager ID of the employee, or null if the employee does not have a manager
     */
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * Sets the manager ID of the employee.
     *
     * @param managerId The manager ID to set for the employee
     */
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    /**
     * Gets the list of subordinates for this employee.
     *
     * @return the list of subordinates
     */
    public List<Employee> getSubordinates() {
        return this.subordinates;
    }

    /**
     * Sets the list of subordinates for this employee.
     *
     * @param subordinates The list of subordinates to set for this employee
     */
    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    /**
     * Returns a string representation of the employee, including their ID, name, salary, manager ID, and subordinates.
     *
     * @return a string representation of the employee
     */
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", managerId=" + managerId +
                ", subordinates=" + subordinates +
                '}';
    }
}
