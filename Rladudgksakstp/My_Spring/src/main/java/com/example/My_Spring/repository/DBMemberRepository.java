package com.example.My_Spring.repository;


import com.example.My_Spring.domain.Member;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class DBMemberRepository implements MemberRepository {
    private Connection connection;
    private Statement statement;
    private final String url = "jdbc:mysql://localhost:3306/My_spring?serverTimezone=Asia/Seoul";
    private final String id = "My_spring_user";
    private final String password = "Wjdwldnjs00!";

    public DBMemberRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, id, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Member save(Member member) {
        String name = member.getName();
        try {
            statement.execute("insert into My_spring.members(_name) values ('" + name + "')");
            ResultSet resultSet = statement.executeQuery("select '_id' from My_spring.members where name = '" + name + "'");
            if (resultSet.next()) {
                member.setId(resultSet.getLong("_id"));
            }

        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
