package com.netcracker.services;

import com.netcracker.dao.EmployeeDAO;
import com.netcracker.dto.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private EmployeeDAO dao = new EmployeeDAO();

    public int getEmployeeCount(){
        return dao.getEmployeeCount();
    }
    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();

    }

    public ArrayList<Employee> getNextOrPreviousSetOfEmployees(int offset, int limit) {
        return dao.getNextSetOfEmployees(offset,limit);
    }

    public String saveEmployeeDetails(Employee employee) {
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
