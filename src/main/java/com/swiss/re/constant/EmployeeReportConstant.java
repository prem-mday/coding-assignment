package com.swiss.re.constant;

public final class EmployeeReportConstant {
    public static final String EMPLOYEE_INFO_CSV_FILE_PATH = "./src/main/resources/employee-info.csv";
    public static final String SALARY_VARIATION_MSG = "Manager: %s %s, earns %s than he should, and by: %,.2f.";
    public static final String SALARY_LESS = "less";
    public static final String SALARY_MORE = "more";
    public static final String LONG_REPORTING_LINE_MSG
            = "Employee: %s %s, has a reporting line which is too long, and by: %d.";
    public static final String SEPARATOR_SYMBOL
            = "================================================================================";
    public static final String SALARY_IMPROVEMENT_REPORT = "SALARY IMPROVEMENT REPORT";
    public static final String REPORTING_LINE_REPORT = "REPORTING LINE REPORT";
    public static final int REPORTING_LINE_THRESHOLD = 4;
    public static final int ID_COL_INDEX = 0;
    public static final int FIRST_NAME_COL_INDEX = 1;
    public static final int LAST_NAME_COL_INDEX = 2;
    public static final int SALARY_COL_INDEX = 3;
    public static final int MANAGER_COL_INDEX = 4;

    private EmployeeReportConstant() {
    }
}
