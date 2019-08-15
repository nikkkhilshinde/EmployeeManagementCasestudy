package com.netcracker.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String dateOfJoining;
    private String dateOfBirth;
    private String departmentId;
    private String grade;
    private String designation;
    private String gender;
    private int basePay;
}
