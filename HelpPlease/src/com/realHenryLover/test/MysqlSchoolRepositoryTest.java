package com.realHenryLover.test;

import com.realHenryLover.model.School;
import com.realHenryLover.repository.MysqlSchoolRepository;
import com.realHenryLover.repository.SchoolRepository;

import static org.junit.jupiter.api.Assertions.*;

class MysqlSchoolRepositoryTest {

    @org.junit.jupiter.api.Test
    void addNewSchool() {
        SchoolRepository repository = new MysqlSchoolRepository();

    }

    @org.junit.jupiter.api.Test
    void findAll() {
    }
}