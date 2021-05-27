package com.realHenryLover.repository;

import com.realHenryLover.model.School;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class MysqlSchoolRepository implements SchoolRepository {
    private Connection connection;
    private Statement statement;
    private final String id = "beautifulLife";
    private final String password = "N72SXJdv<x";

    public MysqlSchoolRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fordatacompetition?serverTimezone=Asia/Seoul", id, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public School addNewSchool(School school) {
        return null;
    }

    @Override
    public List<School> findAll() {
        return null;
    }
}
