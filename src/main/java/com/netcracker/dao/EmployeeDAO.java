package com.netcracker.dao;

import com.netcracker.dto.Employee;
import com.netcracker.utility.ConnectionUtil;
import com.netcracker.utility.Constant;
import oracle.jdbc.driver.Const;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeDAO {
    ArrayList<Employee> allEmployees = new ArrayList<>();
    public ArrayList<Employee> getAllEmployees(){
        try(Connection connection = ConnectionUtil.getConnection()){
            PreparedStatement preparedStatement=connection.prepareStatement(Constant.getAllEmployees);
            ResultSet resultSet = preparedStatement.executeQuery();


            Employee employee = null;
            while (resultSet.next()){
                Date dateOfJoining = resultSet.getDate("date_of_joining");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                Integer departmentId = resultSet.getInt("department_id");
                Integer basePay = resultSet.getInt("base_pay");

                employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setDateOfJoining(dateOfJoining.toString());
                employee.setDateOfBirth(dateOfBirth.toString());
                employee.setDepartmentId(departmentId.toString());
                employee.setGrade(resultSet.getString("grade"));
                employee.setDesignation(resultSet.getString("designation"));
                employee.setGender(resultSet.getString("gender"));
                employee.setBasePay(basePay);

                allEmployees.add(employee);
                employee = null;
            }

            return allEmployees;
        } catch (SQLException e) {
            e.printStackTrace();
            return allEmployees;
        }
    }










//    public Employee getEmployeeByUsernameAndPassword(Employee employee){
//        Employee result = null;
//        Connection connection = ConnectionUtil.getConnection();
//        try{
//            PreparedStatement preparedStatement = connection.prepareStatement(Constant.getEmployeeByUsernameAndPassword);
//            preparedStatement.setString(1,employee.getUsername());
//            preparedStatement.setString(2,employee.getPassword());
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()){
//                result =new Employee();
//                result.setUsername(rs.getString("username"));
//                result.setPassword(rs.getString("password"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    public String saveEmployeeDetails(Employee employee){
        Connection connection = ConnectionUtil.getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.setEmployeeDetails);

            Date dateOfjoining = new SimpleDateFormat("yyyy-MM-dd").parse(employee.getDateOfJoining());
            Date dateOfBith = new SimpleDateFormat("yyyy-MM-dd").parse(employee.getDateOfBirth());

            preparedStatement.setInt(1,employee.getEmployeeId());
            preparedStatement.setString(2,employee.getFirstName());
            preparedStatement.setString(3,employee.getLastName());
            preparedStatement.setDate(4, java.sql.Date.valueOf(employee.getDateOfJoining().toString()));
            preparedStatement.setDate(5, java.sql.Date.valueOf(employee.getDateOfBirth().toString()));
            preparedStatement.setString(6,employee.getDepartmentId());
            preparedStatement.setString(7,employee.getGrade());
            preparedStatement.setString(8,employee.getDesignation());
            preparedStatement.setString(9,employee.getGender());
            preparedStatement.setInt(10,employee.getBasePay());

            System.out.println(java.sql.Date.valueOf("2013-09-04"));

            int i = preparedStatement.executeUpdate();
            return "true";
        }
        catch (SQLException ae){
            ae.printStackTrace();
            return "false";
        } catch (ParseException e) {
            e.printStackTrace();
            return "false";
        }
    }
}
