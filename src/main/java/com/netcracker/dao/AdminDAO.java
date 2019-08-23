package com.netcracker.dao;

import com.netcracker.dto.Admin;
import com.netcracker.utility.ConnectionUtil;
import com.netcracker.utility.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    public Admin getAdminByUsernameAndPassword(Admin admin) throws SQLException {

        ResultSet rs = null;
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.getAdminByUsernameAndPassword);) {
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            rs = preparedStatement.executeQuery();
            Admin result = new Admin();
            while (rs.next()) {
                result.setPassword(rs.getString("password"));
                result.setUsername(rs.getString("username"));
            }
            return result;
        } catch (Exception ae) {
            return null;
        }finally {
            rs.close();
        }
    }
}
