package com.topica.JDBC;

import com.topica.JDBC.Process.CreateTable;
import com.topica.JDBC.Process.InsertTable;
import com.topica.JDBC.Process.SelectData;
import com.topica.JDBC.User.Employee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CreateTable createTable = new CreateTable();
        InsertTable insertTable = new InsertTable();
        SelectData selectData = new SelectData();

        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee("A", "Bac Giang", "37418374"));
        list.add(new Employee("B", "Thanh Hoa", "38491852"));
        list.add(new Employee("C", "Thuong Tin", "53451352"));
        list.add(new Employee("D", "Ba Vi", "5462435234"));

        createTable.createTableFromProcedure();
        insertTable.insertTableFromProcedure(list);
        selectData.selectDatabase();

    }
}
