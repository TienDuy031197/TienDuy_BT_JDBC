package com.topica.JDBC.Process;

import com.topica.JDBC.Connect.ConnectDatabase;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectData {
    private static final String STRING_SQL = "CALL select_procedure();";

    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    public SelectData() {
        ConnectDatabase connectDatabase = new ConnectDatabase();
        connection = connectDatabase.getConnection();
    }

    public void selectDatabase() {
        if (connection != null) {
            try {
                callableStatement = connection.prepareCall(STRING_SQL);
                resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println("id: " + resultSet.getInt(1) +
                            ", name: " + resultSet.getString(2) +
                            ", address: " + resultSet.getString(3) +
                            ", phone: " + resultSet.getString(4));
                }
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
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
