package com.henrylover;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henrylover.model.School;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        List<School> hotGoodBye = consummateData(getSchoolNames());
        ObjectMapper objectMapper = new ObjectMapper();

        // test
        System.out.println(objectMapper.writeValueAsString(hotGoodBye));

    }

    /**
     * 학교 리스트 생성 학과 수 추가해 줘야 함
     * @return School list
     */
    private static Queue<String> getSchoolNames() {
        Queue<String> returnValue = new LinkedList<>();
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
                    returnValue.add(schoolName);
                }
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return returnValue;
    }

    private static List<School> consummateData(Queue<String> schoolSet) {

        List<School> schools = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("testDataSet\\secondClassDB.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (String s : schoolSet) {
                Optional<XSSFRow> optional = searchRowWithSchoolNameInSecondDB(s, sheet);

                if (optional.isEmpty()) {
                    continue;
                }
                XSSFRow row = optional.get();

                School school = new School();

                XSSFCell cell = row.getCell(8);
                // 학교이름으로 school 객체를 받아옵니다.
                school.setSchoolName(cell.getStringCellValue());


                /**
                 * 학생 수
                 */
                // 전체 학생 수
                cell = row.getCell(28);
                int students_number = (int) cell.getNumericCellValue();
                school.setStudents_number(students_number);


                // 여학생 수
                cell = row.getCell(29);
                school.setStudents_female_number((int) cell.getNumericCellValue());


                // 전출
                cell = row.getCell(73);
                school.setStudents_moveOutRatio(students_number / cell.getNumericCellValue());


                // 전입
                cell = row.getCell(74);
                school.setStudents_moveInRatio(students_number / cell.getNumericCellValue());


                /**
                 * 교사
                 */
                // 일반 교사 수
                cell = row.getCell(55);
                school.setGeneralTeacher_number((int) cell.getNumericCellValue());


                // 보직교사 수
                cell = row.getCell(53);
                school.setPositionTeacher_number((int) cell.getNumericCellValue());


                // 여교사 수
                cell = row.getCell(56);
                school.setGeneralTeacher_female_number((int) cell.getNumericCellValue());


                // 보직교사 여자 수
                cell = row.getCell(54);
                school.setPositionTeacher_female_number((int) cell.getNumericCellValue());


                /**
                 * 학교시설
                 */
                // 교지 면적
                cell = row.getCell(88);
                school.setSchoolSize((int) cell.getNumericCellValue());


                // 일반교실 수
                cell = row.getCell(83);
                school.setGeneralClass_number((long) cell.getNumericCellValue());


                // 특별교실 수
                cell = row.getCell(85);
                school.setSpecialClass_number((long) cell.getNumericCellValue());


                // 진학률
                cell = row.getCell(79);
                school.setEnrollmentRate(cell.getNumericCellValue() / 100);



                // 졸업자 수
                cell = row.getCell(77);
                int numberOfGraduates = (int) cell.getNumericCellValue();

                // 진학자 수
                cell = row.getCell(78);
                int numberOfEnrolled = (int) cell.getNumericCellValue();

                // 취업자 수
                cell = row.getCell(80);
                int numberOfEmployed = (int) cell.getNumericCellValue();

                // 입대자 수
                cell = row.getCell(81);
                int numberOfEnlisted = (int) cell.getNumericCellValue();

                // 취업률
                school.setEmploymentRate(numberOfEmployed / (double) (numberOfGraduates - numberOfEnlisted - numberOfEnrolled));


                schools.add(school);

            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return schools;
    }


    public static Optional<XSSFRow> searchRowWithSchoolNameInSecondDB(String searchText, XSSFSheet sheet) {

        //Iterate rows
        for (int j = 14; j <= sheet.getLastRowNum(); j++) {

            XSSFRow row = sheet.getRow(j);

            XSSFCell cell = row.getCell(8);

            if (searchText.equals(cell.getStringCellValue())) {
                System.out.println(searchText);
                return Optional.of(row);
            }

        }
        return Optional.empty();
    }

}






















