# Employee Report Generator

This application is designed to generate an employee report with detailed analysis of employee information, including
salary variations and reporting line structures. It reads employee data from a CSV file, constructs a hierarchy, and
then performs analysis on salaries and reporting lines.

## Features

- **Employee Data Reading**: The application reads employee data from a CSV file, including employee ID, first name,
  last name, salary, and manager ID.
- **Hierarchy Creation**: The application constructs an employee hierarchy based on the manager-subordinate
  relationships.
- **Salary Analysis**: The application identifies salary discrepancies based on the direct subordinates' average salaries.
- **Reporting Line Analysis**: The application identifies reporting lines that exceed the organizational depth
  threshold.
- **Console Output**: The final report is printed to the console.

## Project Structure

### com.swiss.re.model

- **Employee**: Represents an employee in the organization, including attributes
  like `id`, `firstName`, `lastName`, `salary`, `managerId`, and a list of `subordinates`.

### com.swiss.re.service

- **EmployeeInfoReaderService**: Interface for reading employee data from external sources.
- **EmployeeInfoCSVReaderServiceImpl**: Implementation of `EmployeeInfoReaderService` that reads employee data from a
  CSV file.
- **EmployeeInfoAnalyzerService**: Interface for analyzing employee data, such as creating the hierarchy, calculating
  average salaries, and analyzing reporting lines.
- **EmployeeInfoAnalyzerServiceImpl**: Implementation of `EmployeeInfoAnalyzerService` that handles the creation of
  employee hierarchy, calculation of average salaries, and analysis of reporting lines.
- **EmployeeReportGeneratorService**: Interface for generating employee reports.
- **EmployeeReportGeneratorServiceImpl**: Implementation of `EmployeeReportGeneratorService` that coordinates the report
  generation by reading data, performing analysis, and printing the report.

### com.swiss.re

- **EmployeeReportGeneratorApp**: The entry point of the application that triggers the generation of the employee
  report.

## Setup and Installation

1. Clone the repository or download the source code.

    ```bash
    git clone https://github.com/prem-mday/coding-assignment.git
    cd employee-report-generator
    ```

2. Ensure that you have Java 8 or higher installed on your system.

3. Build and run the application using your preferred IDE or command line.

    ```bash
    # For Maven users
    mvn clean install
    ```

4. Place your employee data CSV file in the correct path as defined in the `EmployeeInfoCSVReaderServiceImpl`
   class (`EMPLOYEE_INFO_CSV_FILE_PATH`).

- **The application will generate output like the following:**
   ```
   =========================
   SALARY IMPROVEMENT REPORT
   =========================
   Manager: Martin Chekov, earns less than he should, and by: 15,000.00.
   Manager: Brett Hardleaf, earns more than he should, and by: 4,000.00.
   ```

   ```
   =====================
   REPORTING LINE REPORT
   =====================
   Employee: Sachin Tendulkar, has a reporting line which is too long, and by: 1.
   ```


