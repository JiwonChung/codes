package com.henrylover.model;

import org.jetbrains.annotations.NotNull;

// 설계 잘못함 다만 시간관계로 그냥 진행
// ㄹㅇ 꼬일 것 같지만 내가 봐준다. ㅆㅂ
public class School {
    /**
     * 학교 이름
     */
    // 학교 이름
    private String schoolName;
    // 이름 길이(? ㅋㅋㅋㅋㅋㅋㅋ)
    private long schoolNameLength; // setSchoolName 에서 추가
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
    // 학생 수
    private long students_number;

    // 여학생 수
    private long students_female_number;

    // 학생 남녀 성비 (여자 / 전체)
    private double students_sexRatio;

    // (학생 수 / 전출)
    private double students_moveOutRatio;

    // (학생 수 / 전입)
    private double students_moveInRatio;


    /**
     * 교사
     */
    // 일반교과 교사 수
            // 교장 교감 보건 특수 상담 사서 영양 제외
    private long generalTeacher_number;

    // 일반교과 교사 수, 학생 수 ratio (학생 수 / 교사 수) // 일반교과 교사 수 할 때 넣어주자 (0)
    private double generalTeacher_studentsRatio;

    // 일반교과 교사 중 여자교사 수
    private long generalTeacher_female_number;

    // 일반교과 교사 남녀 성비 (여교사 수 / 전체)
    private double generalTeacher_sexRatio;

    // 일반교과 남교사 수, 학생 수 ratio (학생 수 / 남교사 수)
    private double generalTeacher_male_studentsRatio;

    // 일반교과 여교사 수, 학생 수 ratio (학생 수 / 여교사 수)
    private double generalTeacher_female_studentsRatio;



    // 보직교사 -> position teacher
    private long positionTeacher_number;

    // 보직교사 교사 중 여자교사 수
    private long positionTeacher_female_number;

    // 보직교사 남녀 성비
    private double positionTeacher_sexRatio;





    /**
     * 학교 시설
     */
    // 학교 부지 크기
    private long schoolSize;

    // 학생 1인당 교지 면적
    private double schoolSize_studentsRatio; // 학교 크기 할 때 넣어주자

    // 학과 수
    private long numberOfDepartments;

    // 지역 평균소득 (--만)
    private long regionalAverageIncome;

    // 지역 취업률
    private double regionalEmploymentRate;

    // 일반교실 수
    private long generalClass_number;

    // 특별교실 수
    private long specialClass_number;

    // (일반교실 수 / 학생 수)
    private double generalClass_studentsRatio;

    // (특별교실 수 / 학생 수)
    private double specialClass_studentsRatio;



    /**
     * 종속변수
     */
    // 취업률
    private double employmentRate;

    // 진학률
    private double enrollmentRate;



    /**
     * 학교 이름
     */
    /**
     * set schoolName, schoolNameLength and isKeyWord
     */
    public void setSchoolName(@NotNull String schoolName) {
        this.schoolName = schoolName.trim();
        this.schoolNameLength = this.schoolName.length();

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

        // 속도개선 쌉가능 (그러나 일단 보류)
        if (this.schoolName.contains("정보")) {
            this.isKeyWord[1] = true;
        } else if (this.schoolName.contains("소프트웨어")) {
            this.isKeyWord[2] = true;
        } else if (this.schoolName.contains("인터넷")) {
            this.isKeyWord[3] = true;
        } else if (this.schoolName.contains("기게")) {
            this.isKeyWord[4] = true;
        } else if (this.schoolName.contains("공업")) {
            this.isKeyWord[5] = true;
        } else if (this.schoolName.contains("상업")) {
            this.isKeyWord[6] = true;
        } else if (this.schoolName.contains("항공")) {
            this.isKeyWord[7] = true;
        } else if (this.schoolName.contains("세무")) {
            this.isKeyWord[8] = true;
        } else if (this.schoolName.contains("컴퓨터")) {
            this.isKeyWord[0] = true;
        }
    }


    /**
     * 학생
     */
    public void setStudents_number(long students_number) {
        this.students_number = students_number;
    }

    public void setStudents_female_number(long students_female_number) {
        this.students_female_number = students_female_number;
        this.students_sexRatio = (double) this.students_female_number / this.students_number;
    }

    public void setStudents_moveOutRatio(double students_moveOutRatio) {
        this.students_moveOutRatio = students_moveOutRatio;
    }

    public void setStudents_moveInRatio(double students_moveInRatio) {
        this.students_moveInRatio = students_moveInRatio;
    }


    /**
     * 교사
     */
    /**
     * set generalTeacher_number and this.generalTeacher_studentsRatio
     */
    public void setGeneralTeacher_number(long generalTeacher_number) {
        this.generalTeacher_number = generalTeacher_number;
        this.generalTeacher_studentsRatio = (double) this.students_number / this.generalTeacher_number;
    }

    /**
     * set generalTeacher_female_number, generalTeacher_sexRatio, generalTeacher_male_studentsRatio, and generalTeacher_female_studentsRatio
     */
    public void setGeneralTeacher_female_number(long generalTeacher_female_number) {
        this.generalTeacher_female_number = generalTeacher_female_number;
        this.generalTeacher_sexRatio = (double) this.generalTeacher_female_number / this.generalTeacher_number;
        this.generalTeacher_male_studentsRatio = (double) this.students_number /
                (this.generalTeacher_number - this.generalTeacher_female_number);
        this.generalTeacher_female_studentsRatio = (double) this.students_number /
                this.generalTeacher_female_number;
    }

    public void setPositionTeacher_number(long positionTeacher_number) {
        this.positionTeacher_number = positionTeacher_number;
    }

    public void setPositionTeacher_female_number(long positionTeacher_female_number) {
        this.positionTeacher_female_number = positionTeacher_female_number;
        this.positionTeacher_sexRatio = (double) this.positionTeacher_female_number / this.positionTeacher_number;
    }


    /**
     * 학교 시설
     */
    /**
     * set not only schoolSize but also schoolSize_studentsRatio
     */
    public void setSchoolSize(long schoolSize) {
        this.schoolSize = schoolSize;
        this.schoolSize_studentsRatio = (double) this.schoolSize / this.students_number;
    }

    public void setNumberOfDepartments(long numberOfDepartments) {
        this.numberOfDepartments = numberOfDepartments;
    }

    public void setRegionalAverageIncome(long regionalAverageIncome) {
        this.regionalAverageIncome = regionalAverageIncome;
    }

    public void setRegionalEmploymentRate(double regionalEmploymentRate) {
        this.regionalEmploymentRate = regionalEmploymentRate;
    }

    public void setGeneralClass_number(long generalClass_number) {
        this.generalClass_number = generalClass_number;
        this.generalClass_studentsRatio = (double) this.students_number / this.generalClass_number;
    }

    public void setSpecialClass_number(long specialClass_number) {
        this.specialClass_number = specialClass_number;
        this.specialClass_studentsRatio = (double) this.students_number / this.specialClass_number;
    }



    /**
     * 종속변수
     */
    public void setEmploymentRate(double employmentRate) {
        this.employmentRate = employmentRate;
    }

    public void setEnrollmentRate(double enrollmentRate) {
        this.enrollmentRate = enrollmentRate;
    }





    /**
     * getters
     */

    public String getSchoolName() {
        return schoolName;
    }

    public long getSchoolNameLength() {
        return schoolNameLength;
    }

    public boolean[] getIsKeyWord() {
        return isKeyWord;
    }

    public long getStudents_number() {
        return students_number;
    }

    public long getStudents_female_number() {
        return students_female_number;
    }

    public double getStudents_sexRatio() {
        return students_sexRatio;
    }

    public double getStudents_moveOutRatio() {
        return students_moveOutRatio;
    }

    public double getStudents_moveInRatio() {
        return students_moveInRatio;
    }

    public long getGeneralTeacher_number() {
        return generalTeacher_number;
    }

    public double getGeneralTeacher_studentsRatio() {
        return generalTeacher_studentsRatio;
    }

    public long getGeneralTeacher_female_number() {
        return generalTeacher_female_number;
    }

    public double getGeneralTeacher_sexRatio() {
        return generalTeacher_sexRatio;
    }

    public double getGeneralTeacher_male_studentsRatio() {
        return generalTeacher_male_studentsRatio;
    }

    public double getGeneralTeacher_female_studentsRatio() {
        return generalTeacher_female_studentsRatio;
    }


    public double getSchoolSize() {
        return schoolSize;
    }

    public double getSchoolSize_studentsRatio() {
        return schoolSize_studentsRatio;
    }

    public long getNumberOfDepartments() {
        return numberOfDepartments;
    }

    public long getRegionalAverageIncome() {
        return regionalAverageIncome;
    }

    public double getRegionalEmploymentRate() {
        return regionalEmploymentRate;
    }

    public double getEmploymentRate() {
        return employmentRate;
    }

    public double getEnrollmentRate() {
        return enrollmentRate;
    }

    public long getPositionTeacher_number() {
        return positionTeacher_number;
    }

    public long getPositionTeacher_female_number() {
        return positionTeacher_female_number;
    }

    public double getPositionTeacher_sexRatio() {
        return positionTeacher_sexRatio;
    }

    public long getGeneralClass_number() {
        return generalClass_number;
    }

    public long getSpecialClass_number() {
        return specialClass_number;
    }

    public double getGeneralClass_studentsRatio() {
        return generalClass_studentsRatio;
    }

    public double getSpecialClass_studentsRatio() {
        return specialClass_studentsRatio;
    }
}