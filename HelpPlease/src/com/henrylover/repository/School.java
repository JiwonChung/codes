package com.henrylover.repository;

public class School {
    /**
     * 학교 이름
     */
    // 학교 이름 (0)
    private String schoolName;

    // 이름 길이(? ㅋㅋㅋㅋㅋㅋㅋ)
    private long schoolNameLength; // setSchoolName 에서 추가


    // 학교이름 키워드 여부
    /**
     * 1 : 정보
     * 2 : 소프트웨어
     * 3 : 인터넷
     * 4 : 기계
     * 5 : 공업
     * 6 : 상업
     * 7 : 항공
     * 8 : 세무
     * 0 : 컴퓨터
     */
    private boolean[] isKeyWord = new boolean[9]; // setSchoolName 에서 추가

    /**
     * 학생
     */
    // 학생 수(0)
    private long students_number;

    // 학생 남녀 성비 (전체 / 여자)(0)
    private double students_sexRatio;

    // (학생 수 / 전출)(0)
    private double students_moveOutRatio;

    // (학생 수 / 전입)(0)
    private double students_moveInRatio;

    // (학생 수 / 특별교실 수)(0)
    private double students_specialClassRatio;

    // (학생 수 / 교실 수)(0)
    private double students_classRoomsRatio;

    /**
     * 교사
     */
    // 일반교과 교사 수(0)
            // 교장 교감 보건 특수 상담 사서 영양 제외
    private long generalTeacher_number;

    // 일반교과 교사 수, 학생 수 ratio (학생 수 / 교사 수) // 일반교과 교사 수 할 때 넣어주자
    private double generalTeacher_studentsRatio;

    // 일반교과 교사 남녀 성비 (전체 / 여자) (0)
    private double generalTeacher_sexRatio;


    // 전문교과 교사 수(0)
    private long professionalTeacher_number;

    // 전문교과 교사 수, 학생 수 ratio (학생 수 / 교사 수) // 전문교과 교사 수 할 때 넣어주자
    private long professionalTeacher_studentsRatio;

    // 전문교과 교사 남녀 성비 (전체 / 여자)
    private long professionalTeacher_sexRatio;


    // 전체 교사 수
    private long teacher_number;

    // 전체 교사 수, 학생 수 ratio (학생 수 / 교사 수)
    private double teacher_studentsRatio;

    // 전체 교사 수 남녀 성비 (전체 / 여자) <-- 별로 의미 없을 것이라 생각함
    private double teacher_sexRatio;


    /**
     * 학교 시설
     * ---------------------
     * 학교 운동장 크기는 뺐다. 왜냐하면 설령 유의미한 결과가 있다 하더라도 개선방안이 없기 때문이다.
     */
    // 학교 크기 (0)
    private double schoolSize;

    public long getNumberOfDepartments() {
        return numberOfDepartments;
    }

    public void setNumberOfDepartments(long numberOfDepartments) {
        this.numberOfDepartments = numberOfDepartments;
    }

    public long getRegionalAverageIncome() {
        return regionalAverageIncome;
    }

    public void setRegionalAverageIncome(long regionalAverageIncome) {
        this.regionalAverageIncome = regionalAverageIncome;
    }

    public double getRegionalEmploymentRate() {
        return regionalEmploymentRate;
    }

    public void setRegionalEmploymentRate(double regionalEmploymentRate) {
        this.regionalEmploymentRate = regionalEmploymentRate;
    }

    public double getEmploymentRate() {
        return employmentRate;
    }

    public void setEmploymentRate(double employmentRate) {
        this.employmentRate = employmentRate;
    }

    public double getEnrollmentRate() {
        return enrollmentRate;
    }

    public void setEnrollmentRate(double enrollmentRate) {
        this.enrollmentRate = enrollmentRate;
    }

    // 학생 1인당 교지 면적
    private double schoolSize_studentsRatio; // 하교 크기 할 때 넣어주자

    // 학과 수 (0)
    private long numberOfDepartments;

    // 지역 평균소득 (만) (0)
    private long regionalAverageIncome;

    // 지역 취업률 (0)
    private double regionalEmploymentRate;


    /**
     * 종속변수
     */
    // 취업률 (0)
    private double employmentRate;

    // 진학률 (0)
    private double enrollmentRate;



    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public long getStudents_number() {
        return students_number;
    }

    public void setStudents_number(long students_number) {
        this.students_number = students_number;
    }

    public double getStudents_sexRatio() {
        return students_sexRatio;
    }

    public void setStudents_sexRatio(double students_sexRatio) {
        this.students_sexRatio = students_sexRatio;
    }

    public double getStudents_moveOutRatio() {
        return students_moveOutRatio;
    }

    public void setStudents_moveOutRatio(double students_moveOutRatio) {
        this.students_moveOutRatio = students_moveOutRatio;
    }

    public double getStudents_moveInRatio() {
        return students_moveInRatio;
    }

    public void setStudents_moveInRatio(double students_moveInRatio) {
        this.students_moveInRatio = students_moveInRatio;
    }

    public double getStudents_specialClassRatio() {
        return students_specialClassRatio;
    }

    public void setStudents_specialClassRatio(double students_specialClassRatio) {
        this.students_specialClassRatio = students_specialClassRatio;
    }

    public double getStudents_classRoomsRatio() {
        return students_classRoomsRatio;
    }

    public void setStudents_classRoomsRatio(double students_classRoomsRatio) {
        this.students_classRoomsRatio = students_classRoomsRatio;
    }


    public long getGeneralTeacher_number() {
        return generalTeacher_number;
    }

    public void setGeneralTeacher_number(long generalTeacher_number) {
        this.generalTeacher_number = generalTeacher_number;
    }

    public double getGeneralTeacher_sexRatio() {
        return generalTeacher_sexRatio;
    }

    public void setGeneralTeacher_sexRatio(double generalTeacher_sexRatio) {
        this.generalTeacher_sexRatio = generalTeacher_sexRatio;
    }

    public long getProfessionalTeacher_number() {
        return professionalTeacher_number;
    }

    public void setProfessionalTeacher_number(long professionalTeacher_number) {
        this.professionalTeacher_number = professionalTeacher_number;
    }

    public long getProfessionalTeacher_sexRatio() {
        return professionalTeacher_sexRatio;
    }

    public void setProfessionalTeacher_sexRatio(long professionalTeacher_sexRatio) {
        this.professionalTeacher_sexRatio = professionalTeacher_sexRatio;
    }

    public double getSchoolSize() {
        return schoolSize;
    }

    public void setSchoolSize(double schoolSize) {
        this.schoolSize = schoolSize;
    }
}
































