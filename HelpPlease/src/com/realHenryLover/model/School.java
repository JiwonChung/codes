package com.realHenryLover.model;

public class School {

    private long index;

    private String schoolName;

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
    private long schoolName_keyWord;

    /**
     * 학생
     */
    // 학생 수
    private long students_number;

    // 여학생 수
    private long students_female_number;

    // 전출 학생 수
    private long students_moveOut_number;

    // 전입 학생 수
    private long students_moveIn_number;


    /**
     * 교사
     */
    // 전체 교사 수
    private long generalTeacher_number;

    // 여교사 수
    private long generalTeacher_female_number;

    // 보직교사 수 -> position teacher number
    private long positionTeacher_number;

    // 여보직교사 수
    private long positionTeacher_female_number;


    /**
     * 학교 시설
     */
    // 학교 부지 크기 square
    private long schoolSize;

    // 학과 수
    private long numberOfDepartments;

    // 일반교실 수
    private long generalClass_number;

    // 특수교실 수
    private long specialClass_number;


    /**
     * 지역
     */
    // 지역 평균 소득
    private long regionalAverageIncome;

    // 지역 평당가
    private long regionalPricePerPy;

    // 가격 변동 모멘텀 (21년 - 15년 변화량)
    private double regionalPriceMomentum;

    // 지역특화 산업 // 솔직히 자신 없음.
    private String regionalSpecializedIndustry;


    /**
     * 도서
     */
    // 장서수 계
    private long book_total;

    // 연간 이용자
    private long book_yearlyUser;

    // 연간 이용 책
    private long book_yearlyBook;




    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public long getSchoolName_keyWord() {
        return schoolName_keyWord;
    }

    public void setSchoolName_keyWord(long schoolName_keyWord) {
        this.schoolName_keyWord = schoolName_keyWord;
    }

    public long getStudents_number() {
        return students_number;
    }

    public void setStudents_number(long students_number) {
        this.students_number = students_number;
    }

    public long getStudents_female_number() {
        return students_female_number;
    }

    public void setStudents_female_number(long students_female_number) {
        this.students_female_number = students_female_number;
    }

    public long getStudents_moveOut_number() {
        return students_moveOut_number;
    }

    public void setStudents_moveOut_number(long students_moveOut_number) {
        this.students_moveOut_number = students_moveOut_number;
    }

    public long getStudents_moveIn_number() {
        return students_moveIn_number;
    }

    public void setStudents_moveIn_number(long students_moveIn_number) {
        this.students_moveIn_number = students_moveIn_number;
    }

    public long getGeneralTeacher_number() {
        return generalTeacher_number;
    }

    public void setGeneralTeacher_number(long generalTeacher_number) {
        this.generalTeacher_number = generalTeacher_number;
    }

    public long getGeneralTeacher_female_number() {
        return generalTeacher_female_number;
    }

    public void setGeneralTeacher_female_number(long generalTeacher_female_number) {
        this.generalTeacher_female_number = generalTeacher_female_number;
    }

    public long getPositionTeacher_number() {
        return positionTeacher_number;
    }

    public void setPositionTeacher_number(long positionTeacher_number) {
        this.positionTeacher_number = positionTeacher_number;
    }

    public long getPositionTeacher_female_number() {
        return positionTeacher_female_number;
    }

    public void setPositionTeacher_female_number(long positionTeacher_female_number) {
        this.positionTeacher_female_number = positionTeacher_female_number;
    }

    public long getSchoolSize() {
        return schoolSize;
    }

    public void setSchoolSize(long schoolSize) {
        this.schoolSize = schoolSize;
    }

    public long getNumberOfDepartments() {
        return numberOfDepartments;
    }

    public void setNumberOfDepartments(long numberOfDepartments) {
        this.numberOfDepartments = numberOfDepartments;
    }

    public long getGeneralClass_number() {
        return generalClass_number;
    }

    public void setGeneralClass_number(long generalClass_number) {
        this.generalClass_number = generalClass_number;
    }

    public long getSpecialClass_number() {
        return specialClass_number;
    }

    public void setSpecialClass_number(long specialClass_number) {
        this.specialClass_number = specialClass_number;
    }

    public long getRegionalAverageIncome() {
        return regionalAverageIncome;
    }

    public void setRegionalAverageIncome(long regionalAverageIncome) {
        this.regionalAverageIncome = regionalAverageIncome;
    }

    public long getRegionalPricePerPy() {
        return regionalPricePerPy;
    }

    public void setRegionalPricePerPy(long regionalPricePerPy) {
        this.regionalPricePerPy = regionalPricePerPy;
    }

    public double getRegionalPriceMomentum() {
        return regionalPriceMomentum;
    }

    public void setRegionalPriceMomentum(double regionalPriceMomentum) {
        this.regionalPriceMomentum = regionalPriceMomentum;
    }

    public String getRegionalSpecializedIndustry() {
        return regionalSpecializedIndustry;
    }

    public void setRegionalSpecializedIndustry(String regionalSpecializedIndustry) {
        this.regionalSpecializedIndustry = regionalSpecializedIndustry;
    }

    public long getBook_total() {
        return book_total;
    }

    public void setBook_total(long book_total) {
        this.book_total = book_total;
    }

    public long getBook_yearlyUser() {
        return book_yearlyUser;
    }

    public void setBook_yearlyUser(long book_yearlyUser) {
        this.book_yearlyUser = book_yearlyUser;
    }

    public long getBook_yearlyBook() {
        return book_yearlyBook;
    }

    public void setBook_yearlyBook(long book_yearlyBook) {
        this.book_yearlyBook = book_yearlyBook;
    }
}
