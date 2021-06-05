package com.realHenryLover.repository;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

class MysqlSchoolRepositoryTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void addNewSchool() throws FileNotFoundException {
        SchoolRepository repository = new MysqlSchoolRepository();
    }

    @org.junit.jupiter.api.Test
    void findAll() {
    }
}