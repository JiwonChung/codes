package com.henrylover.repository;

import org.jetbrains.annotations.NotNull;

public class School {
    /**
     * 학교 이름
     */
    // 학교 이름
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
    // 학생 수
    private long students_number;

    // 여학생 수
    private long students_female_number;

    // 학생 남녀 성비 (전체 / 여자)
    private double students_sexRatio;

    // (학생 수 / 전출)
    private double students_moveOutRatio;

    // (학생 수 / 전입)
    private double students_moveInRatio;

    // (학생 수 / 특별교실 수)
    private double students_specialClassRatio;

    // (학생 수 / 교실 수)
    private double students_classRoomsRatio;

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

    // 일반교과 교사 남녀 성비 (전체 / 여자)
    private double generalTeacher_sexRatio;

    // 일반교과 남교사 수, 학생 수 ratio (학생 수 / 남교사 수)
    private double generalTeacher_male_studentsRatio;

    // 일반교과 여교사 수, 학생 수 ratio (학생 수 / 여교사 수)
    private double generalTeacher_female_studentsRatio;



    // 전문교과 교사 수
    private long professionalTeacher_number;

    // 전문교과 교사 수, 학생 수 ratio (학생 수 / 교사 수) // 전문교과 교사 수 할 때 넣어주자
    private double professionalTeacher_studentsRatio;

    // 전문교과 교사 중 여자교사 수
    private long professionalTeacher_female_number;

    // 전문교과 교사 남녀 성비 (전체 / 여자)
    private long professionalTeacher_sexRatio;

    // 전문교과 교사 남교사 수, 학생 수 ratio
    private long professionalTeacher_male_studentsRatio;

    // 전문교과 교사 여교사 수, 학생 수 ratio
    private long professionalTeacher_female_studentsRatio;



    // 전체 교사 수
    private long teacher_number;

    // 전체 교사 수, 학생 수 ratio (학생 수 / 교사 수) <-- 무의미 할 것 같음
    private double teacher_studentsRatio;

    // 전체 교사 수 남녀 성비 (전체 / 여자) <-- 별로 의미 없을 것이라 생각함
    private double teacher_sexRatio;


    /**
     * 학교 시설
     * ---------------------
     */
    // 학교 부지 크기
    private double schoolSize;

    // 학생 1인당 교지 면적
    private double schoolSize_studentsRatio; // 하교 크기 할 때 넣어주자

    // 학과 수
    private long numberOfDepartments;

    // 지역 평균소득 (--만)
    private long regionalAverageIncome;

    // 지역 취업률
    private double regionalEmploymentRate;


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
     * set this.schoolName, this.schoolNameLength and this.isKeyWord
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
        if (schoolName.contains("정보")) {
            this.isKeyWord[1] = true;
        } else if (schoolName.contains("소프트웨어")) {
            this.isKeyWord[2] = true;
        } else if (schoolName.contains("인터넷")) {
            this.isKeyWord[3] = true;
        } else if (schoolName.contains("기게")) {
            this.isKeyWord[4] = true;
        } else if (schoolName.contains("공업")) {
            this.isKeyWord[5] = true;
        } else if (schoolName.contains("상업")) {
            this.isKeyWord[6] = true;
        } else if (schoolName.contains("항공")) {
            this.isKeyWord[7] = true;
        } else if (schoolName.contains("세무")) {
            this.isKeyWord[8] = true;
        } else if (schoolName.contains("컴퓨터")) {
            this.isKeyWord[0] = true;
        }
    }



    /**
     * 학생
     */






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
     * set generalTeacher_number and this.generalTeacher_studentsRatio
     */
    public void setProfessionalTeacher_number(long professionalTeacher_number) {
        this.professionalTeacher_number = professionalTeacher_number;
        this.professionalTeacher_studentsRatio = (double) this.students_number / this.professionalTeacher_number;
    }






}
































