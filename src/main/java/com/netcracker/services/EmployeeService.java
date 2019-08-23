package com.netcracker.services;

import com.netcracker.dto.Admin;
import com.netcracker.dao.EmployeeDAO;
import com.netcracker.dto.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeService {
    private EmployeeDAO dao = new EmployeeDAO();

    public int getEmployeeCount(){
        return dao.getEmployeeCount();
    }
    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> allEmployees = dao.getAllEmployees();
        return allEmployees;

    }

    public ArrayList<Employee> getNextOrPreviousSetOfEmployees(int offset,int limit) {
        return dao.getNextSetOfEmployees(offset,limit);
    }

    public String saveEmployeeDetails(Employee employee) throws SQLException {
        String result = dao.saveEmployeeDetails(employee);
        if (result.equals("true")) {
            return "true";
        }
        return result;
    }

    public Employee getEmployeeById(Employee employee) {
        Employee retrievedEmployee = dao.getEmployeeById(employee);
        if (retrievedEmployee != null) {
            return retrievedEmployee;
        }
        return retrievedEmployee;
    }

    public String updateEmployee(Employee employee) {
        String temp = dao.updateEmployee(employee);
        if (temp.equals("true")) {
            return "true";
        } else {
            return temp;
        }
    }
}
