package hello.hellospring.repository;

import hello.hellospring.domain.Member;


import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBMemberRepository implements MemberRepository {

    private Connection connection;
    private Statement statement;
    private final String id = "hello_spring_user";
    private final String password = "Wjdwldnjs00!";
    public DBMemberRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://1.235.203.128:3306/hello_spring?serverTimezone=Asia/Seoul", id, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public Member save(Member member) {
        String name = member.getName();
        try {
            statement.execute("insert into hello_spring.members(name) values ('" + name + "');");
            ResultSet resultSet = statement.executeQuery("select `id` from hello_spring.members where name='" + name + "';");
            if (resultSet.next()) {
                member.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return member;

    }

    @Override
    public Optional<Member> findById(long id) {
        try {

            ResultSet resultSet = statement.executeQuery("select * from hello_spring.members where `id`='" + id + "';");
            if (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getLong("id"));
                member.setName(resultSet.getString("name"));
                return Optional.of(member);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        try {
            ResultSet resultSet = statement.executeQuery("select * from hello_spring.members where `name`='" + name + "';");
            if (resultSet.next()) {
                Member member = new Member();
                member.setName(name);
                member.setId(resultSet.getLong("id"));
                return Optional.of(member);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        final List<Member> members = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from hello_spring.members order by `id`;");
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getLong("id"));
                member.setName(resultSet.getString("name"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
