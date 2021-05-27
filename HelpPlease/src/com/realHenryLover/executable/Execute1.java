package com.realHenryLover.executable;

import com.realHenryLover.model.School;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Execute1 {
    public static void main(String[] args) {
        getSchoolNameAndSubject();

    }

    /**
     * get data from _hiveData
     * @return (학교, 학과 수)
     */
    private static Map<String, Integer> getSchoolNameAndSubject() {
        Map<String, Integer> returnValue = new HashedMap<>();

        try {
            FileInputStream hiveFile = new FileInputStream("testDataSet\\akmu\\ThirdClass_DB_hiveData.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(hiveFile);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int maxRows = 1986;

            String schoolName = "강서공업고등학교";
            int subjectCounter = 1;


            for (int rowIndex = 3; rowIndex <= maxRows; rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                String gottenValue = row.getCell(1).getStringCellValue().trim();
                if (schoolName.equals(gottenValue)) {
                    subjectCounter++;
                } else {
                    System.out.println(schoolName + ", " + subjectCounter);
                    returnValue.put(schoolName, subjectCounter);
                    schoolName = gottenValue;
                    subjectCounter = 1;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return returnValue;
    }


    /**
     * get data from _school
     * @param schoolSet 행여 이맘 다칠까
     * @return 거의 완성된 상태의 school
     */
    private static List<School> getAlmostEveryData(Map<String, Integer> schoolSet) {
        List<School> returnValue = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("testDataSet\\akmu\\SecondClass_DB_school.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Map.Entry<String, Integer> entry : schoolSet.entrySet()) {
                Optional<XSSFRow> optionalRow = searchRowWithSchoolNameInSecondDB(entry.getKey(), sheet);

                if (optionalRow.isEmpty()) {
                    continue;
                }
                XSSFRow row = optionalRow.get();

                School school = new School();

                XSSFCell cell;

                /**
                 * 학교 이름
                 */
                // 학교 이름
                cell = row.getCell(8);
                school.setSchoolName(entry.getKey());

                // 키워드
                String schoolName = entry.getKey();
                if (schoolName.contains("정보")) {
                    school.setSchoolName_keyWord(1);
                } else if (schoolName.contains("소프트웨어")) {
                    school.setSchoolName_keyWord(2);
                } else if (schoolName.contains("인터넷")) {
                    school.setSchoolName_keyWord(3);
                } else if (schoolName.contains("기계")) {
                    school.setSchoolName_keyWord(4);
                } else if (schoolName.contains("공업")) {
                    school.setSchoolName_keyWord(5);
                } else if (schoolName.contains("항공")) {
                    school.setSchoolName_keyWord(7);
                } else if (schoolName.contains("세무")) {
                    school.setSchoolName_keyWord(8);
                } else if (schoolName.contains("컴퓨터")) {
                    school.setSchoolName_keyWord(0);
                } else {
                    school.setSchoolName_keyWord(-1);
                }


                /**
                 * 학생
                 */
                // 학생 수

                // 여학생 수

                // 전출 학생 수

                // 전입 학생 수


                /**
                 * 교사
                 */
                // 전체 교사 수

                // 여교사 수

                // 보직교사 수

                // 여 보직교사 수

                /**
                 * 학교 시설
                 */
                // 학교 부지 크기

                // 학과 수

                // 일반교실 수

                // 특수교실 수


                /**
                 * 지역
                 */
                // 지역 평균 소득

                // 지역 평당가

                // 부동산 가격 변동 모멘텀

                // 지역특화 산업 // 자신없음


                /**
                 * 도서
                 */
                // 장서 수 계

                // 연간 이용자

                // 연간 이용 책


            }



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return returnValue;
    }

    /**
     * return row with searchText
     * @param searchText
     * @param sheet
     * @return row
     */
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
