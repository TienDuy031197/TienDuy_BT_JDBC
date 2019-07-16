package com.topica.JDBC.Process;

import com.topica.JDBC.Connect.ConnectDatabase;
import com.topica.JDBC.User.Employee;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CreateTable {
    private static final String STRING_SQL = "CALL table_creat_procedure();";

    Connection connection = null;
    CallableStatement callableStatement = null;

    public CreateTable(){
        ConnectDatabase connectDatabase = new ConnectDatabase();
        connection = connectDatabase.getConnection();
    }

    public void createTableFromProcedure(){
        if(connection != null){
            try {
                callableStatement = connection.prepareCall(STRING_SQL);
                callableStatement.execute();
                System.out.println("Create table success!");
            } catch (SQLException e) {
                e.printStackTrace();
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
