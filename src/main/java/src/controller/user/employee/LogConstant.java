package src.controller.user.employee;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_EMPLOYEE = ANSI_BLUE + "Creating a new employee: " + ANSI_RESET + "{}";
    public static final String EMPLOYEE_SUCCESSFULLY_CREATED = ANSI_GREEN + "Employee successfully created " + ANSI_RESET;
    public static final String UPDATING_EMPLOYEE = ANSI_BLUE + "Updating employee: " + ANSI_RESET + "{}";
    public static final String EMPLOYEE_UPDATED = ANSI_GREEN + "Employee updated: " + ANSI_RESET + "{}";
    public static final String GETTING_ALL_EMPLOYEES = ANSI_BLUE + "Getting all employees" + ANSI_RESET;
    public static final String RETRIEVED_ALL_EMPLOYEES = ANSI_GREEN + "Retrieved all employees. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_EMPLOYEE_COUNT_BY_DELETED_STATE = ANSI_BLUE + "Getting employee count by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_EMPLOYEE_COUNT_BY_DELETED_STATE = ANSI_GREEN + "Retrieved employee count by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_EMPLOYEE_BY_ID = ANSI_BLUE + "Getting employee by id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_EMPLOYEE_BY_ID = ANSI_GREEN + "Retrieved employee by id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_EMPLOYEE_BY_PHONE_NUMBER = ANSI_BLUE + "Getting employee by phone number: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_EMPLOYEE_BY_PHONE_NUMBER = ANSI_GREEN + "Retrieved employee by phone number: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_EMPLOYEES_BY_SALARY_BETWEEN = ANSI_BLUE + "Getting employees by salary between: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + " and " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_EMPLOYEES_BY_SALARY_BETWEEN = ANSI_GREEN + "Retrieved employees by salary between. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_EMPLOYEES_BY_DELETED_STATE = ANSI_BLUE + "Getting employees by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_EMPLOYEES_BY_DELETED_STATE = ANSI_GREEN + "Retrieved employees by deleted state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_EMPLOYEE_WITH_ID = ANSI_BLUE + "Deleting employee with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + " , hard delete: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String EMPLOYEE_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Employee deleted successfully with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}
