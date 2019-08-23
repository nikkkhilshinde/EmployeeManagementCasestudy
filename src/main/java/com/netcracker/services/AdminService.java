package com.netcracker.services;

import com.netcracker.dao.AdminDAO;
import com.netcracker.dto.Admin;

import java.sql.SQLException;

public class AdminService {
    private AdminDAO adminDAO = new AdminDAO();
    public Boolean validateAdmin(Admin admin) throws SQLException {
        Boolean validate = false;
        if(adminDAO.getAdminByUsernameAndPassword(admin).getUsername()!=null){
            validate = true;
        }
        return validate;
    }
}
