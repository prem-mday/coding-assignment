package com.swiss.re.service;

import com.swiss.re.model.Employee;

import java.util.List;

/**
 * Service interface for reading employee information from a data source.
 * <p>
 * Implementations of this interface are responsible for loading employee data,
 * such as from a CSV file, database, or other external source.
 */
public interface EmployeeInfoReaderService {
    /**
     * Reads employee information from the given file path and returns it as a list of {@link Employee} objects.
     *
     * @param filePath The path to the employee data file (e.g., CSV)
     * @return A list of {@link Employee} instances parsed from the file; may return an empty list if no data is found
     */
    List<Employee> readEmployeeInfo(String filePath);
}
