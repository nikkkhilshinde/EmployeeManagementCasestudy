package com.netcracker.utility;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    private Constant(){}
    public static String allEmployees = "allEmployees";

    public static String offset = "offset";
    public static String limit = "limit";

    public static String searchEmployeePage = "searchEmployee.jsp";
    public static String adminHomepage = "adminHomepage.jsp";
    public static String inputDetailsPage = "inputDetails.jsp";
    public static String showAllEmployeePage = "showAllEmployee.jsp";
    public static String editEmployeePage = "editEmployee.jsp";

    public static String errorMessage = "errorMessage";
    public static String successMessage = "successMessage";

    public static String employeeId = "employee_id";
    public static String firstName = "first_name";
    public static String lastName = "last_name";
    public static String dateOfJoining = "date_of_joining";
    public static String dateOfBirth = "date_of_birth";
    public static String departmentId = "department_id";
    public static String grade = "grade";
    public static String designation = "designation";
    public static String gender = "gender";
    public static String basePay = "base_pay";

    public static String getEmployeeCount = "SELECT count(*) FROM EMPTABLE";
    public static String setEmployeeDetails = "INSERT INTO EMPTABLE VALUES(?,?,?,?,?,?,?,?,?,?)";
    public static String getAdminByUsernameAndPassword = "SELECT * FROM admin WHERE USERNAME = ? AND PASSWORD = ?";
    public static String getAllEmployees = "SELECT * FROM EMPTABLE";
    public static String getEmployeeById = "SELECT * FROM EMPTABLE WHERE employee_id = ?";
    public static String updateEmployeeById = "UPDATE EMPTABLE" + " SET first_name = ?," +
            "last_name = ?," +
            "date_of_joining = ?," +
            "date_of_birth = ?," +
            "department_id = ?," +
            "grade = ?," +
            "designation = ?," +
            "gender = ?," +
            "base_pay = ? " +
            "WHERE employee_id = ?";

    public static String showNextOrPreviousEmployees = "SELECT *\n" +
            "FROM   (SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DATE_OF_JOINING, DATE_OF_BIRTH, DEPARTMENT_ID, GRADE, DESIGNATION, GENDER, BASE_PAY, rownum AS rnum\n" +
            "        FROM   (SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DATE_OF_JOINING, DATE_OF_BIRTH, DEPARTMENT_ID, GRADE, DESIGNATION, GENDER, BASE_PAY\n" +
            "                FROM   EMPTABLE\n" +
            "                ORDER BY FIRST_NAME)\n" +
            "        WHERE rownum <= ?)\n" +
            "WHERE  rnum > ?";
}
