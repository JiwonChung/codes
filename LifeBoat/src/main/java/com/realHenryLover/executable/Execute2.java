package com.realHenryLover.executable;

import com.realHenryLover.repository.MysqlSchoolRepository;
import com.realHenryLover.repository.SchoolRepository;



public class Execute2 {
    public static void main(String[] args) {
        SchoolRepository schoolRepository = new MysqlSchoolRepository();
        System.out.println((schoolRepository.findAll()));
    }
}
