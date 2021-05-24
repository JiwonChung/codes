package com.henrylover;

import com.henrylover.model.School;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<School> hotGoodBye = getSchoolNames();

        // test
        System.out.println(hotGoodBye.get(0).getSchoolName());

    }

    /**
     * 학교 리스트 생성 학과 수 추가해 줘야 함
     * @return School list
     */
    private static List<School> getSchoolNames() {
        List<School> returnValue = new ArrayList<>();
        try {
            FileInputStream hiveFile = new FileInputStream("testDataSet\\thirdClassDBTlqkf.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(hiveFile);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int maxRows = 1986;

            String schoolName = "";
            for (int rowIndex = 2; rowIndex <= maxRows; rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                String stringCellValue = row.getCell(1).getStringCellValue();

                if (!stringCellValue.equals(schoolName)) {
                    schoolName = stringCellValue;
                    School school = new School();
                    school.setSchoolName(schoolName);
                    returnValue.add(school);
                }
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return returnValue;
    }

    private static List<School> consummateData(List<School> schoolList) {

        List<School> schools = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("testDataSet\\secondClassDB.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int maxRows = 21279;

            for (int rowIndex = 14; rowIndex <= maxRows;  rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                School school;

                XSSFCell cell = row.getCell(8);
                // 학교이름으로 school 객체를 받아옵니다.
                String schoolName = cell.getStringCellValue();
                List<School> collect = schoolList
                        .stream()
                        .filter(
                                a -> a.getSchoolName().equals(schoolName)
                        ).collect(Collectors.toList());

                if (collect.size() == 1) {
                    // 특성화고 일 경우
                    school = collect.get(0);
                } else {
                    throw new IllegalStateException("같은 학교가 2개 이상 나옵니다. ");
                }


                cell = row.getCell(28);
                // 전체 학생 수
                long students_number = (int) cell.getNumericCellValue();
                school.setStudents_number(students_number);


                cell = row.getCell(29);
                // 여학생 수


                cell = row.getCell(55);
                // 일반 교사 수


                cell = row.getCell(56);
                // 여교사 수


                cell = row.getCell(53);
                // 보직교사 수


                cell = row.getCell(54);
                // 보직교사 여자 수


                cell = row.getCell(73);
                // 전출


                cell = row.getCell(74);
                // 전입


                cell = row.getCell(76);
                // 졸업자 수


                cell = row.getCell(77);
                // 진학자 수


                cell = row.getCell(80);
                // 취업자 수


                cell = row.getCell(81);
                // 입대자 수


                cell = row.getCell(82);
                // 기타


                cell = row.getCell(83);
                // 일반교실 수


                cell = row.getCell(85);
                // 특별교실 수


                cell = row.getCell(88);
                // 교지 면적


                cell = row.getCell(89);
                // 1인당 교지 면적


            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return schoolList;
    }
}






















