package com.company;

import java.sql.*;

public class DBManager {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost/client?&useSSL=false";

    private final String USER_NAME = "root";
    private final String PASSWORD = "7550";

    public DBManager() {
        Connection connection = null;
        Statement state = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("MySQL connection \n");
            state = connection.createStatement();

            // 명령어
            String sql;
            sql = "SELECT * FROM clients";
            ResultSet resultSet = state.executeQuery(sql);
            

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String pw = resultSet.getString("pw");

                System.out.println("id: " + id);
                System.out.println("pw: " + pw);
                System.out.println("-----------------------------");
            }
            resultSet.close();
            state.close();
            connection.close();
        } catch (Exception ignored) {}
        finally {
            try {
                if (state != null)
                    state.close();
            } catch (SQLException exception) {}
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {}
        }
        System.out.println("MySQL Close");
    }

}
