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
import java.util.HashMap;
import java.util.Map;

public class EmployeeDAO {
    private static Map<String,String> errorMap = new HashMap<>();

    public EmployeeDAO(){
        errorMap.put("20001","Employee should be atleast 18 year old");
        errorMap.put("02290","Employee Id should be 6 digits only and should not start with 0");
        errorMap.put("02291","Department Not found");
        errorMap.put("01438","Employee Id must be 6 digits");
        errorMap.put("00001","Employee already exist");
        errorMap.put("2291","Department Not found");
        errorMap.put("2290","Employee Id should be 6 digits only and should not start with 0");

    }

    public static String getErrorMessage(String errorCode){
        return errorMap.get(errorCode);
    }

    public String updateEmployee(Employee employee){
        try(Connection connection = ConnectionUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.updateEmployeeById);

            Date dateOfjoining = new SimpleDateFormat("yyyy-MM-dd").parse(employee.getDateOfJoining());
            Date dateOfBith = new SimpleDateFormat("yyyy-MM-dd").parse(employee.getDateOfBirth());

            preparedStatement.setInt(10,employee.getEmployeeId());
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(employee.getDateOfJoining().toString()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(employee.getDateOfBirth().toString()));
            preparedStatement.setString(5,employee.getDepartmentId());
            preparedStatement.setString(6,employee.getGrade());
            preparedStatement.setString(7,employee.getDesignation());
            preparedStatement.setString(8,employee.getGender());
            preparedStatement.setInt(9,employee.getBasePay());

            int i = preparedStatement.executeUpdate();

            return "true";
        } catch (SQLException e) {
//            e.printStackTrace();
//            return EmployeeDAO.getErrorMessage(String.valueOf(e.getErrorCode()));
            String errorMessage = EmployeeDAO.getErrorMessage(String.valueOf(e.getErrorCode()));
            if(errorMessage!=null){
                return errorMessage;
            }
            else{
                return e.getMessage()+"Error code:"+e.getErrorCode();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public Employee getEmployeeById(Employee employee){
        Employee retrievedEmployee = null;

        try(Connection connection = ConnectionUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.getEmployeeById);
            preparedStatement.setInt(1,employee.getEmployeeId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Date dateOfJoining = resultSet.getDate("date_of_joining");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                Integer departmentId = resultSet.getInt("department_id");
                Integer basePay = resultSet.getInt("base_pay");

                retrievedEmployee = new Employee();
                retrievedEmployee.setEmployeeId(resultSet.getInt("employee_id"));
                retrievedEmployee.setFirstName(resultSet.getString("first_name"));
                retrievedEmployee.setLastName(resultSet.getString("last_name"));
                retrievedEmployee.setDateOfJoining(dateOfJoining.toString());
                retrievedEmployee.setDateOfBirth(dateOfBirth.toString());
                retrievedEmployee.setDepartmentId(departmentId.toString());
                retrievedEmployee.setGrade(resultSet.getString("grade"));
                retrievedEmployee.setDesignation(resultSet.getString("designation"));
                retrievedEmployee.setGender(resultSet.getString("gender"));
                retrievedEmployee.setBasePay(basePay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrievedEmployee;
    }





    ArrayList<Employee> allEmployees = new ArrayList<>();
    public ArrayList<Employee> getAllEmployees(){
        try(Connection connection = ConnectionUtil.getConnection()){
            PreparedStatement preparedStatement=connection.prepareStatement(Constant.getAllEmployees);
            ResultSet resultSet = preparedStatement.executeQuery();
            allEmployees.clear();

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
            //return EmployeeDAO.getErrorMessage(String.valueOf(ae.getErrorCode()));
            String errorMessage = EmployeeDAO.getErrorMessage(String.valueOf(ae.getErrorCode()));
            if(errorMessage!=null){
                return errorMessage;
            }
            else{
                return ae.getMessage()+"Errorcode:"+ae.getErrorCode();
            }
            //return EmployeeDAO.getErrorMessage(String.valueOf(ae.getErrorCode()));
        } catch (ParseException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }
}
