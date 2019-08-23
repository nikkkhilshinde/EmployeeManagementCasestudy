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
    private ArrayList<Employee> allEmployees = new ArrayList<>();
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

    public int getEmployeeCount(){
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.getEmployeeCount);
            ResultSet resultSet = preparedStatement.executeQuery();){


            int count = 0;
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
            return  count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public String updateEmployee(Employee employee){
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.updateEmployeeById);){


//
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
            String errorMessage = EmployeeDAO.getErrorMessage(String.valueOf(e.getErrorCode()));
            if(errorMessage!=null){
                return errorMessage;
            }
            else{
                return e.getMessage()+"Error code:"+e.getErrorCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public Employee getEmployeeById(Employee employee){
        Employee retrievedEmployee = null;

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.getEmployeeById);){

            preparedStatement.setInt(1,employee.getEmployeeId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Date dateOfJoining = resultSet.getDate(Constant.dateOfJoining);
                Date dateOfBirth = resultSet.getDate(Constant.dateOfBirth);
                Integer departmentId = resultSet.getInt(Constant.departmentId);
                Integer basePay = resultSet.getInt(Constant.basePay);

                retrievedEmployee = new Employee();
                retrievedEmployee.setEmployeeId(resultSet.getInt(Constant.employeeId));
                retrievedEmployee.setFirstName(resultSet.getString(Constant.firstName));
                retrievedEmployee.setLastName(resultSet.getString(Constant.lastName));
                retrievedEmployee.setDateOfJoining(dateOfJoining.toString());
                retrievedEmployee.setDateOfBirth(dateOfBirth.toString());
                retrievedEmployee.setDepartmentId(departmentId.toString());
                retrievedEmployee.setGrade(resultSet.getString(Constant.grade));
                retrievedEmployee.setDesignation(resultSet.getString(Constant.designation));
                retrievedEmployee.setGender(resultSet.getString(Constant.gender));
                retrievedEmployee.setBasePay(basePay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrievedEmployee;
    }

    public ArrayList<Employee> getNextSetOfEmployees(int offset,int limit){
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Constant.showNextOrPreviousEmployees);){

            preparedStatement.setInt(1,offset+limit);
            preparedStatement.setInt(2,offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            allEmployees.clear();

            Employee employee = null;
            while (resultSet.next()){
                Date dateOfJoining = resultSet.getDate(Constant.dateOfJoining);
                Date dateOfBirth = resultSet.getDate(Constant.dateOfBirth);
                Integer departmentId = resultSet.getInt(Constant.departmentId);
                Integer basePay = resultSet.getInt(Constant.basePay);

                employee = new Employee();
                employee.setEmployeeId(resultSet.getInt(Constant.employeeId));
                employee.setFirstName(resultSet.getString(Constant.firstName));
                employee.setLastName(resultSet.getString(Constant.lastName));
                employee.setDateOfJoining(dateOfJoining.toString());
                employee.setDateOfBirth(dateOfBirth.toString());
                employee.setDepartmentId(departmentId.toString());
                employee.setGrade(resultSet.getString(Constant.grade));
                employee.setDesignation(resultSet.getString(Constant.designation));
                employee.setGender(resultSet.getString(Constant.gender));
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

    public ArrayList<Employee> getAllEmployees(){
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Constant.getAllEmployees);){

            ResultSet resultSet = preparedStatement.executeQuery();
            allEmployees.clear();

            Employee employee = null;
            while (resultSet.next()){
                Date dateOfJoining = resultSet.getDate(Constant.dateOfJoining);
                Date dateOfBirth = resultSet.getDate(Constant.dateOfBirth);
                Integer departmentId = resultSet.getInt(Constant.departmentId);
                Integer basePay = resultSet.getInt(Constant.basePay);

                employee = new Employee();
                employee.setEmployeeId(resultSet.getInt(Constant.employeeId));
                employee.setFirstName(resultSet.getString(Constant.firstName));
                employee.setLastName(resultSet.getString(Constant.lastName));
                employee.setDateOfJoining(dateOfJoining.toString());
                employee.setDateOfBirth(dateOfBirth.toString());
                employee.setDepartmentId(departmentId.toString());
                employee.setGrade(resultSet.getString(Constant.grade));
                employee.setDesignation(resultSet.getString(Constant.designation));
                employee.setGender(resultSet.getString(Constant.gender));
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

    public String saveEmployeeDetails(Employee employee){
        Connection connection = ConnectionUtil.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(Constant.setEmployeeDetails);){


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

            int i = preparedStatement.executeUpdate();
            return "true";
        }
        catch (SQLException ae){
            String errorMessage = EmployeeDAO.getErrorMessage(String.valueOf(ae.getErrorCode()));
            if(errorMessage!=null){
                return errorMessage;
            }
            else{
                return ae.getMessage()+"Error code:"+ae.getErrorCode();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }
}
