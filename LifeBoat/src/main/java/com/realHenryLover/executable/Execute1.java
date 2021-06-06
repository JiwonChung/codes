package com.realHenryLover.executable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realHenryLover.model.School;
import com.realHenryLover.repository.MysqlSchoolRepository;
import com.realHenryLover.repository.SchoolRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Execute1 {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> schoolNameAndSubject = getSchoolNameAndSubject();
        List<School> schools = getAlmostEveryData(schoolNameAndSubject);
        SchoolRepository repository = new MysqlSchoolRepository();

        List<School> schoolList = new ArrayList<>();
        for (School value : schools) {
            School school = repository.addNewSchool(value);
            schoolList.add(school);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(schoolList));
    }

    /**
     * get data from _hiveData
     * @return HashMap<학교, 학과 수>
     */
    private static Map<String, Integer> getSchoolNameAndSubject() {
        Map<String, Integer> returnValue = new LinkedHashMap<>();

        try {
            FileInputStream hiveFile = new FileInputStream("testDataSet\\akmu\\ThirdClassDB_hiveData.xlsx");
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
     * @param schoolSet
     * @return 거의 완성된 상태의 school
     */
    private static List<School> getAlmostEveryData(Map<String, Integer> schoolSet) {
        List<School> returnValue = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("testDataSet\\akmu\\SecondClassDB_school.xlsx");
            XSSFWorkbook workbook_school = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet_school = workbook_school.getSheetAt(0);

            XSSFWorkbook workbook_ppp = new XSSFWorkbook(new FileInputStream("testDataSet/akmu/SecondClassDB_pricePerPy.xlsx"));
            XSSFSheet sheet_ppp = workbook_ppp.getSheetAt(0);

            XSSFWorkbook workbook_book = new XSSFWorkbook(new FileInputStream("testDataSet/akmu/SecondClassDB_book.xlsx"));
            XSSFSheet sheet_book = workbook_book.getSheetAt(0);

            XSSFWorkbook workbook_coefficient = new XSSFWorkbook(new FileInputStream("testDataSet/akmu/SecondClassDB_coefficient.xlsx"));
            XSSFSheet sheet_coefficient = workbook_coefficient.getSheetAt(0);

            XSSFWorkbook workbook_city = new XSSFWorkbook(new FileInputStream("testDataSet/akmu/ThridClassDB_city.xlsx"));
            XSSFSheet sheet_city = workbook_city.getSheetAt(1);

            XSSFWorkbook workbook_personalEarnings = new XSSFWorkbook(new FileInputStream("testDataSet/akmu/ThridClassDB_personalEarnings.xlsx"));
            XSSFSheet sheet_personalEarnings = workbook_personalEarnings.getSheetAt(1);


            for (Map.Entry<String, Integer> entry : schoolSet.entrySet()) {
                Optional<XSSFRow> optionalRow = searchRowUsingSchoolName_school(entry.getKey(), sheet_school);

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
                if (schoolName.contains("정보") || schoolName.contains("소프트웨어") || schoolName.contains("컴퓨터") || schoolName.contains("인터넷")) {
                    school.setSchoolName_keyWord(1);
                } else if (schoolName.contains("상업")) {
                    school.setSchoolName_keyWord(2);
                } else if (schoolName.contains("기계")) {
                    school.setSchoolName_keyWord(4);
                } else if (schoolName.contains("공업")) {
                    school.setSchoolName_keyWord(5);
                } else if (schoolName.contains("항공")) {
                    school.setSchoolName_keyWord(7);
                } else if (schoolName.contains("세무")) {
                    school.setSchoolName_keyWord(8);
                } else {
                    school.setSchoolName_keyWord(-1);
                }


                /**
                 * 학생
                 */
                // 학생 수
                cell = row.getCell(28);
                school.setStudents_number((int) cell.getNumericCellValue());

                // 여학생 수
                cell = row.getCell(29);
                school.setStudents_female_number((int) cell.getNumericCellValue());

                // 전출 학생 수
                cell = row.getCell(73);
                school.setStudents_moveOut_number((int) cell.getNumericCellValue());

                // 전입 학생 수
                cell = row.getCell(74);
                school.setStudents_moveIn_number((int) cell.getNumericCellValue());


                /**
                 * 교사
                 */
                // 일반 교사 수
                cell = row.getCell(55);
                school.setGeneralTeacher_number((int) cell.getNumericCellValue());

                // 여교사 수
                cell = row.getCell(56);
                school.setGeneralTeacher_female_number((int) cell.getNumericCellValue());

                // 보직교사 수
                cell = row.getCell(53);
                school.setPositionTeacher_number((int) cell.getNumericCellValue());

                // 여 보직교사 수
                cell = row.getCell(54);
                school.setPositionTeacher_female_number((int) cell.getNumericCellValue());


                /**
                 * 학교 시설
                 */
                // 학교 부지 크기
                cell = row.getCell(88);
                school.setSchoolSize((int) cell.getNumericCellValue());

                // 학과 수
                school.setNumberOfDepartments(entry.getValue());

                // 일반교실 수
                cell = row.getCell(83);
                school.setGeneralClass_number((long) cell.getNumericCellValue());

                // 특수교실 수
                cell = row.getCell(85);
                school.setSpecialClass_number((long) cell.getNumericCellValue());


                /**
                 * 지역
                 */
                cell = row.getCell(2);
                String region = cell.getStringCellValue().trim();

                // 지역 평균 소득 <-- 시도별 1인당 실지 gdp() 데이터가 있는데 괜찮?
                searchCityNameUsingGu(region, sheet_city).ifPresentOrElse(
                        (si) -> {
                            long personalGDP = getPersonalGDP(si, sheet_personalEarnings);
                            if (personalGDP == -1) {
                                System.out.println(si);
                            }
//                            System.out.println(schoolName + " : " +  personalGDP);
                            school.setRegionalAverageIncome(personalGDP);
                        } ,
                        () -> {
                            System.out.println("지역 income 을 찾을 수 없습니다. " + schoolName);
                        }
                );

                // 지역 평당가
                Optional<Long> ppp = searchAveragePriceUsingRegionName_ppy(region, sheet_ppp);
                ppp.ifPresent(
                        school::setRegionalPricePerPy
                );

                // 부동산 매매가격지수 변동 모멘텀
                Optional<XSSFRow> momentum_opt = searchRowUsingRegion_coefficient(region, sheet_coefficient);
                if (momentum_opt.isPresent()) {
                    XSSFRow row_coefficient = momentum_opt.get();
                    double yearlyMomentum = row_coefficient.getCell(26).getNumericCellValue() - row_coefficient.getCell(4).getNumericCellValue();
                    school.setRegionalPriceMomentum(yearlyMomentum);
                }

                // 지역특화 산업
                // 자신없음
                // 일단보류


                /**
                 * 도서
                 */
                Optional<XSSFRow> book_opt = searchRowUsingSchoolName_book(schoolName, sheet_book);
                if (book_opt.isPresent()) {
                    XSSFRow row_book = book_opt.get();

                    // 장서 수 계
                    school.setBook_total((int) row_book.getCell(2).getNumericCellValue());

                    // 연간 이용자
                    school.setBook_yearlyUser((int) row_book.getCell(5).getNumericCellValue());

                    // 연간 이용 책
                    school.setBook_yearlyBook((int) row_book.getCell(6).getNumericCellValue());
                }

                /**
                 * 종속변수
                 */
                // 진학률
                cell = row.getCell(79);
                school.setEnrollmentRate(cell.getNumericCellValue() / 100);


                // 취업률
                school.setEmploymentRate(makeEmploymentRate(row));







                // 마무리!
                returnValue.add(school);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return returnValue;
    }

    /**
     * for _school
     */
    public static Optional<XSSFRow> searchRowUsingSchoolName_school(String searchText, XSSFSheet sheet) {

        //Iterate rows
        for (int j = 14; j <= sheet.getLastRowNum(); j++) {

            XSSFRow row = sheet.getRow(j);

            XSSFCell cell = row.getCell(8);

            if (searchText.equals(cell.getStringCellValue())) {
                return Optional.of(row);
            }

        }
        return Optional.empty();
    }

    /**
     * for _coefficient
     */
    public static Optional<XSSFRow> searchRowUsingRegion_coefficient(String searchText, XSSFSheet sheet) {

        // Iterate rows
        for (int i = 12; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell cell = row.getCell(3);
            if (cell != null) {
                if (cell.getStringCellValue().contains(searchText)) {
                    return Optional.of(row);
                }
            }
            cell = row.getCell(2);
            if (cell != null) {
                if (cell.getStringCellValue().contains(searchText)) {
                    return Optional.of(row);
                }
            }
            cell = row.getCell(1);
            if (cell != null) {
                if (cell.getStringCellValue().contains(searchText)) {
                    return Optional.of(row);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * for _book
     */
    public static Optional<XSSFRow> searchRowUsingSchoolName_book(String searchText, XSSFSheet sheet) {

        // Iterate rows
        for (int i = 11; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell cell = row.getCell(0);
            if (searchText.equals(cell.getStringCellValue())) {
                return Optional.of(row);
            }
        }
        return Optional.empty();
    }

    /**
     * for _ppp
     */
    public static Optional<Long> searchAveragePriceUsingRegionName_ppy(String searchText, XSSFSheet sheet) {

        // Iterate rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell cell = row.getCell(1);
            if (cell.getStringCellValue().contains(searchText)) {
                String tmp_string = row.getCell(2).getStringCellValue();
                long price = Long.parseLong(tmp_string.split("만")[0]);
                return Optional.of(price);
            }
        }
        return Optional.empty();
    }

    /**
     * for 행정구로 시도 찾기(실질)
     * 행정구
     * @param sheet _city
     */
    public static Optional<String> searchCityNameUsingGu(String gu_name, XSSFSheet sheet) {
        // Iterate rows
        for (int i = 3; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell cell = row.getCell(2);
            if (cell != null) {
                if (cell.getStringCellValue().equals(gu_name)) {
                    return Optional.of(row.getCell(2).getStringCellValue());
                }
            }
            cell = row.getCell(4);
            if (cell != null) {
                if (cell.getStringCellValue().equals(gu_name)) {
                    return Optional.of(row.getCell(2).getStringCellValue());
                }
            }
            cell = row.getCell(6);
            if (cell != null) {
                if (cell.getStringCellValue().equals(gu_name)) {
                    return Optional.of(row.getCell(2).getStringCellValue());
                }
            }
        }
        return Optional.empty();
    }

    /**
     * for 도시이름으로 지역 gdp 받기
     * @param townName
     * @return
     */
    public static long getPersonalGDP(String townName, XSSFSheet sheet) {
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell cell = row.getCell(0);
            if (cell.getStringCellValue().contains(townName)) {
                cell = row.getCell(1);
                return (long) cell.getNumericCellValue();
            }
        }
        return -1;
    }



    public static double makeEmploymentRate(XSSFRow row) {
        // 졸업자
        XSSFCell cell = row.getCell(77);
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

        return numberOfEmployed / (double) (numberOfGraduates - numberOfEnlisted - numberOfEnrolled);
    }
}
