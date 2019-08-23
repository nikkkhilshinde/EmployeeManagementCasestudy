package com.netcracker.utility;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionUtil {
    private static Connection connection;

    private ConnectionUtil(){}
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Context context = new InitialContext();
                Context envContext = (Context) context.lookup("java:/comp/env");
                DataSource dataSource = (DataSource) envContext.lookup("jdbc/xe");
                connection = dataSource.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return connection;
    }
}