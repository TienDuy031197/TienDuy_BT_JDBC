package com.topica.JDBC.Process;

import com.topica.JDBC.Connect.ConnectDatabase;
import com.topica.JDBC.User.Employee;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InsertTable {
    private static final String STRING_SQL = "CALL insert_procedure(?,?,?);";

    Connection connection = null;
    CallableStatement callableStatement = null;

    public InsertTable() {
        ConnectDatabase connectDatabase = new ConnectDatabase();
        connection = connectDatabase.getConnection();
    }

    public void insertTableFromProcedure(List<Employee> list) {
        if (connection != null) {
            try {
                callableStatement = connection.prepareCall(STRING_SQL);
                for (Employee employee : list) {
                    callableStatement.setString(1, employee.getName());
                    callableStatement.setString(2, employee.getAddress());
                    callableStatement.setString(3, employee.getPhone());
                    callableStatement.execute();
                }
                System.out.println("Insert sucsess!");
            } catch (SQLException e) {
                System.out.println("Lock!");
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (callableStatement != null) {
                        callableStatement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
