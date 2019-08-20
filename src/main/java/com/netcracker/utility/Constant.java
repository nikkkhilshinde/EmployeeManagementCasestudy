package com.netcracker.utility;

import java.util.HashMap;
import java.util.Map;

public class Constant {
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
