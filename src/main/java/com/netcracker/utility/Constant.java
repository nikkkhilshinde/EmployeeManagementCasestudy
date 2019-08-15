package com.netcracker.utility;

public class Constant {
    //public static String getEmployeeByUsernameAndPassword = "SELECT * FROM temp WHERE USERNAME = ? AND PASSWORD = ?";
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
}
