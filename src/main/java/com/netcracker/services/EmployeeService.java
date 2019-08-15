package com.netcracker.services;

import com.netcracker.dto.Admin;
import com.netcracker.dao.EmployeeDAO;
import com.netcracker.dto.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeService {
    private EmployeeDAO dao = new EmployeeDAO();

    public ArrayList<Employee> getAllEmployees(){
        ArrayList<Employee> allEmployees =  dao.getAllEmployees();
        return allEmployees;

    }

    public Boolean saveEmployeeDetails(Employee employee) throws SQLException {
        Boolean result = false;
        if (dao.saveEmployeeDetails(employee)) {
            result = true;
        }
        return result;
    }
}
