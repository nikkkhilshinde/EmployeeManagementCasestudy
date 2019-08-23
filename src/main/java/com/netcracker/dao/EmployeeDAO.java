package com.netcracker.dao;

import com.netcracker.dto.Employee;
import com.netcracker.utility.ConnectionUtil;
import com.netcracker.utility.Constant;


import java.sql.*;

import java.sql.Date;
import java.util.*;
import java.util.logging.Logger;


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
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_COUNT_FROM_EMPTABLE);
            ResultSet resultSet = preparedStatement.executeQuery();){


            int count = 0;
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
            return  count;
        } catch (SQLException e) {
            return 0;
        }
    }
    public String updateEmployee(Employee employee){
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.UPDATE_EMPLOYEE_BY_ID);){


//
            preparedStatement.setInt(10,employee.getEmployeeId());
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(employee.getDateOfJoining()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setString(5,employee.getDepartmentId());
            preparedStatement.setString(6,employee.getGrade());
            preparedStatement.setString(7,employee.getDesignation());
            preparedStatement.setString(8,employee.getGender());
            preparedStatement.setInt(9,employee.getBasePay());
            preparedStatement.executeUpdate();
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

            return e.getMessage();
        }

    }

    public Employee getEmployeeById(Employee employee){
        Employee retrievedEmployee = null;

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_FROM_EMPTABLE_WHERE_EMPLOYEE_ID);){

            preparedStatement.setInt(1,employee.getEmployeeId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Date dateOfJoining = resultSet.getDate(Constant.DATE_OF_JOINING);
                Date dateOfBirth = resultSet.getDate(Constant.DATE_OF_BIRTH);
                Integer departmentId = resultSet.getInt(Constant.DEPARTMENT_ID);
                Integer basePay = resultSet.getInt(Constant.BASE_PAY);

                retrievedEmployee = new Employee();
                retrievedEmployee.setEmployeeId(resultSet.getInt(Constant.EMPLOYEE_ID));
                retrievedEmployee.setFirstName(resultSet.getString(Constant.FIRST_NAME));
                retrievedEmployee.setLastName(resultSet.getString(Constant.LAST_NAME));
                retrievedEmployee.setDateOfJoining(dateOfJoining.toString());
                retrievedEmployee.setDateOfBirth(dateOfBirth.toString());
                retrievedEmployee.setDepartmentId(departmentId.toString());
                retrievedEmployee.setGrade(resultSet.getString(Constant.GRADE));
                retrievedEmployee.setDesignation(resultSet.getString(Constant.DESIGNATION));
                retrievedEmployee.setGender(resultSet.getString(Constant.GENDER));
                retrievedEmployee.setBasePay(basePay);

            }
        } catch (Exception ae) {
            Logger.getLogger("something");
        }
        return retrievedEmployee;
    }

    public ArrayList<Employee> getNextSetOfEmployees(int offset, int limit){
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Constant.SHOW_NEXT_OR_PREVIOUS_EMPLOYEES);){

            preparedStatement.setInt(1,offset+limit);
            preparedStatement.setInt(2,offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            allEmployees.clear();

            Employee employee = null;
            while (resultSet.next()){
                Date dateOfJoining = resultSet.getDate(Constant.DATE_OF_JOINING);
                Date dateOfBirth = resultSet.getDate(Constant.DATE_OF_BIRTH);
                Integer departmentId = resultSet.getInt(Constant.DEPARTMENT_ID);
                Integer basePay = resultSet.getInt(Constant.BASE_PAY);

                employee = new Employee();
                employee.setEmployeeId(resultSet.getInt(Constant.EMPLOYEE_ID));
                employee.setFirstName(resultSet.getString(Constant.FIRST_NAME));
                employee.setLastName(resultSet.getString(Constant.LAST_NAME));
                employee.setDateOfJoining(dateOfJoining.toString());
                employee.setDateOfBirth(dateOfBirth.toString());
                employee.setDepartmentId(departmentId.toString());
                employee.setGrade(resultSet.getString(Constant.GRADE));
                employee.setDesignation(resultSet.getString(Constant.DESIGNATION));
                employee.setGender(resultSet.getString(Constant.GENDER));
                employee.setBasePay(basePay);

                allEmployees.add(employee);
                employee = null;

            }
            return allEmployees;
        } catch (SQLException e) {

            return allEmployees;
        }
    }

    public ArrayList<Employee> getAllEmployees(){
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Constant.SELECT_FROM_EMPTABLE);){

            ResultSet resultSet = preparedStatement.executeQuery();
            allEmployees.clear();

            Employee employee = null;
            while (resultSet.next()){
                Date dateOfJoining = resultSet.getDate(Constant.DATE_OF_JOINING);
                Date dateOfBirth = resultSet.getDate(Constant.DATE_OF_BIRTH);
                Integer departmentId = resultSet.getInt(Constant.DEPARTMENT_ID);
                Integer basePay = resultSet.getInt(Constant.BASE_PAY);

                employee = new Employee();
                employee.setEmployeeId(resultSet.getInt(Constant.EMPLOYEE_ID));
                employee.setFirstName(resultSet.getString(Constant.FIRST_NAME));
                employee.setLastName(resultSet.getString(Constant.LAST_NAME));
                employee.setDateOfJoining(dateOfJoining.toString());
                employee.setDateOfBirth(dateOfBirth.toString());
                employee.setDepartmentId(departmentId.toString());
                employee.setGrade(resultSet.getString(Constant.GRADE));
                employee.setDesignation(resultSet.getString(Constant.DESIGNATION));
                employee.setGender(resultSet.getString(Constant.GENDER));
                employee.setBasePay(basePay);

                allEmployees.add(employee);
                employee = null;
            }

            return allEmployees;
        } catch (SQLException e) {

            return allEmployees;
        }
    }

    public String saveEmployeeDetails(Employee employee){
        Connection connection = ConnectionUtil.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(Constant.INSERT_INTO_EMPTABLE_VALUES);){


            preparedStatement.setInt(1,employee.getEmployeeId());
            preparedStatement.setString(2,employee.getFirstName());
            preparedStatement.setString(3,employee.getLastName());
            preparedStatement.setDate(4, java.sql.Date.valueOf(employee.getDateOfJoining()));
            preparedStatement.setDate(5, java.sql.Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setString(6,employee.getDepartmentId());
            preparedStatement.setString(7,employee.getGrade());
            preparedStatement.setString(8,employee.getDesignation());
            preparedStatement.setString(9,employee.getGender());
            preparedStatement.setInt(10,employee.getBasePay());

            preparedStatement.executeUpdate();
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
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }
}
