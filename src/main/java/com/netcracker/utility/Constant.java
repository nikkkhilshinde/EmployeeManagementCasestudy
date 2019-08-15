package com.netcracker.utility;

public class Constant {
    //public static String getEmployeeByUsernameAndPassword = "SELECT * FROM temp WHERE USERNAME = ? AND PASSWORD = ?";
    public static String setEmployeeDetails = "INSERT INTO EMPTABLE VALUES(?,?,?,?,?,?,?,?,?,?)";
    public static String getAdminByUsernameAndPassword = "SELECT * FROM admin WHERE USERNAME = ? AND PASSWORD = ?";
    public static String getAllEmployees = "SELECT * FROM EMPTABLE";
}
