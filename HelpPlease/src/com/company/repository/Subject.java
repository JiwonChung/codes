package com.company.repository;

public class Subject {
    private String subjectName;
    private long[] numberOfClassesByGrade = new long[3];

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public long[] getNumberOfClassesByGrade() {
        return numberOfClassesByGrade;
    }

    public void setNumberOfClassesByGrade(long[] numberOfClassesByGrade) {
        this.numberOfClassesByGrade = numberOfClassesByGrade;
    }
}
