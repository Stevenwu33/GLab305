package com.wu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class Insert_PreparedSt_Example {

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement prepState = null;
        ResultSet resultSet = null;
        String dburl = "jdbc:mysql://localhost:3306/classicmodels";
        String user = "root";
        String password = "password";

        try{
            con = DriverManager.getConnection(dburl, user, password);
            System.out.println("Connection established Successfully");

            // below insert one row with  sql query using "Prepared Statement"
            String sqlQuery = "INSERT INTO EMPLOYEES (officeCode, firstName, lastName, email, extension, reportsTo, vacationHours, employeeNumber, jobTitle) VALUES (?,?,?,?,?,?,?,?,?)";
            prepState = con.prepareStatement(sqlQuery);
            prepState.setInt(1, 6);
            prepState.setString(2, "Jamil");
            prepState.setString(3, "fink");
            prepState.setString(4, "JJ@gmail.com");
            prepState.setString(5, "2759");
            prepState.setInt(6, 1143);
            prepState.setInt(7, 9);
            prepState.setInt(8, 0003);
            prepState.setString(9, "Manager");
            int affectedRows = prepState.executeUpdate();
            System.out.println(affectedRows + "row(s) affected!");

            // pulling data from database for an inserted recorded

            prepState = con.prepareStatement("select * from employees where employeeNumber = ? ");
            prepState.setInt(1,0003);
            // execute select query
            resultSet = prepState.executeQuery();
            // display functions to show result set of the table
            while(resultSet.next()){
                System.out.println(resultSet.getString("firstName"));
                System.out.println(resultSet.getString("lastname"));
                System.out.println(resultSet.getString("email"));
                System.out.println(resultSet.getString("officeCode"));
            }


        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }
        try {
            prepState.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
