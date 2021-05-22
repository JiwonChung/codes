package com.company.repository;

import java.util.ArrayList;
import java.util.List;

public class School {
    private String school_name;
    private ArrayList<Subject> subjects = new ArrayList<>();
    private boolean boy = false;
    private boolean girl = false;
    private long totalStudents = 0;

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubjects(Subject subject) {
        this.subjects.add(subject);
        setTotalStudents();
    }

    public boolean isBoy() {
        return boy;
    }

    public void setBoy(boolean boy) {
        this.boy = boy;
    }

    public boolean isGirl() {
        return girl;
    }

    public void setGirl(boolean girl) {
        this.girl = girl;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    private void setTotalStudents() {
        long[][] numberOfClassesByGrade = new long[subjects.size()][3];
        for (int i = 0; i < subjects.size(); i++) {
             numberOfClassesByGrade[i] = subjects.get(i).getNumberOfClassesByGrade();
        }
        for (int i = 0; i < subjects.size(); i++) {
            totalStudents += numberOfClassesByGrade[i][0] + numberOfClassesByGrade[i][1] + numberOfClassesByGrade[i][2];
        }
    }


}
