package com.realHenryLover.repository;

import com.realHenryLover.model.School;

import java.sql.*;
import java.util.ArrayList;
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
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public School addNewSchool(School school) {
        String schoolName = school.getSchoolName();
        long schoolName_keyWord = school.getSchoolName_keyWord();
        long students_number = school.getStudents_number();
        long students_female_number = school.getStudents_female_number();
        long students_moveOut_number = school.getStudents_moveOut_number();
        long students_moveIn_number = school.getStudents_moveIn_number();
        long generalTeacher_number = school.getGeneralTeacher_number();
        long generalTeacher_female_number = school.getGeneralTeacher_female_number();
        long positionTeacher_number = school.getPositionTeacher_number();
        long positionTeacher_female_number = school.getPositionTeacher_female_number();
        long schoolSize = school.getSchoolSize();
        long numberOfDepartment = school.getNumberOfDepartments();
        long generalClass_number = school.getGeneralClass_number();
        long specialClass_number = school.getSpecialClass_number();
        long regionalAverageIncome = school.getRegionalAverageIncome();
        long regionalPricePerPy = school.getRegionalPricePerPy();
        double regionalPriceMomentum = Math.round(school.getRegionalPriceMomentum() * 1000) / 1000.0;
        // 지역 특화 생산물 생략
        long book_total = school.getBook_total();
        long book_yearlyUser = school.getBook_yearlyUser();
        long book_yearlyBook = school.getBook_yearlyBook();
        double employmentRate = Math.round(school.getEmploymentRate() * 1000) / 1000.0;
        double enrollmentRate = Math.round(school.getEnrollmentRate() * 1000) / 1000.0;

        try {
            statement.execute("INSERT INTO `fordatacompetition`.`schools` (`schoolName`, `schoolName_keyWord`, `students_number`, `students_female_number`, `students_moveOut_number`, `students_moveIn_number`, `generalTeacher_number`, `generalTeacher_female_number`, `positionTeacher_number`, `positionTeacher_female_number`, `schoolSize`, `numberOfDepartments`, `generalClassNumber`, `specialClassNumber`, `regionalAverageIncome`, `regionalPricePerPy`, `regionalPriceMomentum`, `book_total`, `book_yearlyUser`, `book_yearlyBook`, `employmentRate`, `enrollmentRate`)" +
                    " VALUES('" + schoolName + "', '" + schoolName_keyWord + "', '" + students_number + "', '" + students_female_number + "', '" + students_moveOut_number + "', '" + students_moveIn_number + "', '" + generalTeacher_number + "', '" + generalTeacher_female_number + "', '" + positionTeacher_number + "', '" + positionTeacher_female_number + "', '" + schoolSize + "', '" + numberOfDepartment + "', '" + generalClass_number + "', '" + specialClass_number + "', '" + regionalAverageIncome + "', '" + regionalPricePerPy + "', '" + regionalPriceMomentum + "', '" + book_total + "', '" + book_yearlyUser + "', '" + book_yearlyBook + "', '" + employmentRate + "', '" + enrollmentRate + "');");
            ResultSet set = statement.executeQuery("select `index` from `fordatacompetition`.`schools` where 'schoolName' = '" + schoolName + "';");
            if (set.next()) {
                school.setIndex(set.getLong("id"));
                return school;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return school;
    }

    @Override
    public List<School> findAll() {
        List<School> returnValue = new ArrayList<>();
        try {
            statement.execute("select * from `fordatacompetition`.`schools`;");
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                School school = new School();
                school.setIndex(set.getLong("index"));
                school.setEnrollmentRate(set.getDouble("employmentRate"));
                school.setEnrollmentRate(set.getDouble("enrollmentRate"));
                returnValue.add(school);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnValue;

    }

    @Override
    public List<School> findBySchoolName_keyWord(long keyWordIndex) {
        List<School> returnValue = new ArrayList<>();
        try {
            statement.execute("select * from `fordatacompetition`.`schools`;");
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                School school = new School();
                school.setIndex(set.getLong("index"));
                school.setEnrollmentRate(set.getDouble("employmentRate"));
                school.setEnrollmentRate(set.getDouble("enrollmentRate"));
                returnValue.add(school);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnValue;
    }
}
