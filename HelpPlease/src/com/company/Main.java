package com.company;

import com.company.repository.School;
import com.company.repository.Subject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // read operate
        read();

    }

    private static List<School> read() {
        try {
            FileInputStream file = new FileInputStream("testDataSet\\thirdClassDBTlqkf.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int maxRows = 1986;
            int maxCell = 11;

            List<School> schools = new ArrayList<>();

            int rowIndex;
            /**
             * @cellIndex
             * 1: 학교이름
             * 2: 학과이름
             * 4: 1학년 학생 수
             * 5: 2학년 학생 수
             * 6: 3학년 학생 수
             * 11: 남녀공학/남/녀
             */
            int cellIndex;


            // ************************************
            School SOJ = new School();
            SOJ.setSchool_name("지원스쿨");

            schools.add(SOJ);
            // ************************************

            String schoolName = "";
            boolean girl = false, boy = false;


            for (rowIndex = 2; rowIndex <= 1986; rowIndex++) {

                String subjectName;
                int firstGradeStudents;
                int secondGradeStudents;
                int thirdGradeStudents;

                XSSFRow row = sheet.getRow(rowIndex);

                // cellNum == 1 학교이름
                XSSFCell cell = row.getCell(1);
                schoolName = cell.getStringCellValue();

                // cellNum = 2 학과이름
                cell = row.getCell(2);
                subjectName = cell.getStringCellValue();

                // cellNum = 4 1학년 학생 수
                cell = row.getCell(4);
                firstGradeStudents = (int) cell.getNumericCellValue();

                // cellNum = 6 2학년 학생 수
                cell = row.getCell(6);
                secondGradeStudents = (int) cell.getNumericCellValue();

                // cellNum = 8 3학년 학생 수
                cell = row.getCell(8);
                thirdGradeStudents = (int) cell.getNumericCellValue();


                if (schools.get(schools.size() - 1).getSchool_name().equals(schoolName)) {
                    // 기존 학교는 학과만 추가
                    Subject subject = new Subject();
                    subject.setSubjectName(subjectName);
                    long[] value = {firstGradeStudents, secondGradeStudents, thirdGradeStudents};
                    subject.setNumberOfClassesByGrade(value);

                    schools.get(schools.size() - 1).addSubjects(subject);
                } else {
                    // 새 학교
                    School school = new School();

                    // 학교이름
                    school.setSchool_name(schoolName);

                    // 남녀공학? or anything else?
                    cell = row.getCell(11);
                    System.out.println(cell.getStringCellValue());
                    if (cell.getStringCellValue().trim().equals("남녀공학")) {
                        girl = true;
                        boy = true;
                    } else if (cell.getStringCellValue().trim().equals("여")) {
                        girl = true;
                        boy = false;
                    } else if (cell.getStringCellValue().trim().equals("남")) {
                        girl = false;
                        boy = true;
                    }
                    school.setBoy(boy);
                    school.setGirl(girl);

                    // 새로운 학과
                    Subject subject = new Subject();
                    subject.setSubjectName(subjectName);
                    long[] a = {firstGradeStudents, secondGradeStudents, thirdGradeStudents};
                    subject.setNumberOfClassesByGrade(a);
                    school.addSubjects(subject);

                    // list 에 학교 추가
                    schools.add(school);
                }
            }

            // json 변환
            ObjectMapper objectMapper = new ObjectMapper();

            // 출력
            System.out.println(objectMapper.writeValueAsString(schools));

            return schools;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

//    private static void write() {
//        try {
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            XSSFSheet sheet = workbook.createSheet("나비");
//
//        }
//    }
}
