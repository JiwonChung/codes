package com.realHenryLover.repository;

import com.realHenryLover.model.School;

import java.util.List;

public interface SchoolRepository {

    public School addNewSchool(School school);

    public List<School> findAll();

    public List<School> findBySchoolName_keyWord(long keyWordIndex);

}
