package com.topica.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Procedure_Callable {

    public static void main(String[] args) {
        Procedure_Callable procedure_callable = new Procedure_Callable();
        procedure_callable.createTableFromProcedure();
        procedure_callable.insertTableFromProcedure();
        procedure_callable.selectDataFromTable();
    }

    public Connection getConnection() {
        try {
            Class.forName(Constant.CLASS_NAME);
            return DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    public void selectDataFromTable() {
        String sql = "SELECT * FROM employees;";
        Connection connection = getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                callableStatement = connection.prepareCall(sql);
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

    public void createTableFromProcedure() {
        String sql = "CALL table_creat_procedure();";
        Connection connection = getConnection();
        CallableStatement callableStatement = null;
        if (connection != null) {
            try {
                callableStatement = connection.prepareCall(sql);
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

    public void insertTableFromProcedure() {
        String sql = "CALL insert_procedure(?,?,?,?);";
        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee(1, "kim anh", "Bac Giang", "37418374"));
        list.add(new Employee(2, "viet", "Thanh Hoa", "38491852"));
        list.add(new Employee(3, "my hanh", "Thuong Tin", "53451352"));
        list.add(new Employee(4, "kim vi", "Ba Vi", "5462435234"));
        Connection connection = getConnection();
        CallableStatement callableStatement = null;
        if (connection != null) {
            try {
                callableStatement = connection.prepareCall(sql);
                for (Employee employee : list) {
                    callableStatement.setInt(1, employee.getId());
                    callableStatement.setString(2, employee.getName());
                    callableStatement.setString(3, employee.getAddress());
                    callableStatement.setString(4, employee.getPhone());
                    callableStatement.execute();
                }
                System.out.println("Insert sucsess!");
            } catch (SQLException e) {
                System.out.println("Trung key!");
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
