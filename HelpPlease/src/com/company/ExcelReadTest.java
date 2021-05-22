package com.company;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReadTest {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream("testDataSet\\thirdClassDBTlqkf.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int rowIndex;
            int cellIndex = 0;

            XSSFSheet sheet = workbook.getSheetAt(0);

            // sheet 의 row 수를 가져온다.
            int rows = sheet.getPhysicalNumberOfRows();

            for (rowIndex = 0; rowIndex < rows; rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);

                if (row != null) {

                    int cell_count = row.getPhysicalNumberOfCells();

                    for (cellIndex = 0; cellIndex <= cell_count; cellIndex++) {
                        XSSFCell cell = row.getCell(cellIndex);

                        String value = "";

                        if (cell != null) {
                            switch (cell.getCellType()) {
//                                case FORMULA:
//                                    value = cell.getCellFormula();
//                                    break;
                                case STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                case NUMERIC:
                                    value = cell.getNumericCellValue() + "";
                                    break;
//                                case BLANK:
//                                    value = cell.getBooleanCellValue() + ""; // cell == null 에서 왜 안걸러지는거임? ㅅㅂ
//                                    break;
//                                case ERROR:
//                                    value = cell.getErrorCellValue() + "";
//                                    break;
                            }
                            System.out.println(rowIndex + "번 행, " + cellIndex + "번 열 값 : " + value);
                        }


                    }
                }
            }

        } catch (Exception e){
        }
    }
}