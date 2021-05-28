package com.realHenryLover.test;

import com.realHenryLover.model.School;
import com.realHenryLover.repository.MysqlSchoolRepository;
import com.realHenryLover.repository.SchoolRepository;

import static org.junit.jupiter.api.Assertions.*;

class MysqlSchoolRepositoryTest {

    @org.junit.jupiter.api.Test
    void addNewSchool() {
        SchoolRepository repository = new MysqlSchoolRepository();
        School sc = new School();
        sc.setSchoolName("지원스쿨");
        sc.setSchoolName_keyWord(-1);
        repository.addNewSchool(sc);

    }

    @org.junit.jupiter.api.Test
    void findAll() {
        String tmp_string = "5100만";
        String[] array = tmp_string.split("만");
        System.out.println(Long.parseLong(array[0]));
    }
}