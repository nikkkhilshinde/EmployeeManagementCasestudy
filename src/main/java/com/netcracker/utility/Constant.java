package com.netcracker.utility;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    private Constant() {
    }

    public static final String PAGE_NOT_FOUND = "page not found";

    public static final String ALL_EMPLOYEES = "allEmployees";

    public static final String OFFSET = "offset";
    public static final String LIMIT = "limit";

    public static final String SEARCH_EMPLOYEE_JSP = "searchEmployee.jsp";
    public static final String ADMIN_HOMEPAGE_JSP = "adminHomepage.jsp";
    public static final String INPUT_DETAILS_JSP = "inputDetails.jsp";
    public static final String SHOW_ALL_EMPLOYEE_JSP = "showAllEmployee.jsp";
    public static final String EDIT_EMPLOYEE_JSP = "editEmployee.jsp";

    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String SUCCESS_MESSAGE = "successMessage";

    public static final String EMPLOYEE_ID = "employee_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String DATE_OF_JOINING = "date_of_joining";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String DEPARTMENT_ID = "department_id";
    public static final String GRADE = "grade";
    public static final String DESIGNATION = "designation";
    public static final String GENDER = "gender";
    public static final String BASE_PAY = "base_pay";

    public static final String SELECT_COUNT_FROM_EMPTABLE = "SELECT count(*) FROM EMPTABLE";
    public static final String INSERT_INTO_EMPTABLE_VALUES = "INSERT INTO EMPTABLE VALUES(?,?,?,?,?,?,?,?,?,?)";
    public static final String SELECT = "SELECT * FROM admin WHERE USERNAME = ? AND PASSWORD = ?";
    public static final String SELECT_FROM_EMPTABLE = "SELECT * FROM EMPTABLE";
    public static final String SELECT_FROM_EMPTABLE_WHERE_EMPLOYEE_ID = "SELECT * FROM EMPTABLE WHERE employee_id = ?";
    public static final String UPDATE_EMPLOYEE_BY_ID = "UPDATE EMPTABLE" + " SET first_name = ?," +
            "last_name = ?," +
            "date_of_joining = ?," +
            "date_of_birth = ?," +
            "department_id = ?," +
            "grade = ?," +
            "designation = ?," +
            "gender = ?," +
            "base_pay = ? " +
            "WHERE employee_id = ?";

    public static final String SHOW_NEXT_OR_PREVIOUS_EMPLOYEES = "SELECT *\n" +
            "FROM   (SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DATE_OF_JOINING, DATE_OF_BIRTH, DEPARTMENT_ID, GRADE, DESIGNATION, GENDER, BASE_PAY, rownum AS rnum\n" +
            "        FROM   (SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DATE_OF_JOINING, DATE_OF_BIRTH, DEPARTMENT_ID, GRADE, DESIGNATION, GENDER, BASE_PAY\n" +
            "                FROM   EMPTABLE\n" +
            "                ORDER BY FIRST_NAME)\n" +
            "        WHERE rownum <= ?)\n" +
            "WHERE  rnum > ?";
}
