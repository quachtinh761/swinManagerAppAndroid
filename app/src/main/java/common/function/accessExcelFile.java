package common.function;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class accessExcelFile {
    public static List<List <String> > readExcelFile(String filePath, int sheetNumber){
        List <List <String> > result = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(filePath));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get sheet in file to read
            if (sheetNumber > workbook.getNumberOfSheets()){
                return null;
            }
            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

            Iterator<Row> rowIterator = sheet.iterator();
            //2 first row is header
            rowIterator.next();rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                List<String> rowExcel = new ArrayList<>();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            rowExcel.add(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            rowExcel.add(Double.toString(cell.getNumericCellValue()));
                            break;
                    }
                }
                result.add(rowExcel);
            }
        } catch (Exception e) {

        }
        return result;
    }

    public static void editExcelFile(String filePath, int sheetNumber, int rowNumber, List<String> data){
        try {
            FileInputStream file = new FileInputStream(new File(filePath));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get sheet in file to read
            if (sheetNumber > workbook.getNumberOfSheets()){
                return;
            }
            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);
            Iterator<Row> rowIterator = sheet.iterator();
            //2 first row is header + (n - 1) row to the row that will be edit
            if (rowNumber > sheet.getLastRowNum() - 2){
                return;
            }
            for (int i = 0; i < rowNumber + 1; i++) {
                rowIterator.next();
            }

            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            List<String> rowExcel = new ArrayList<>();
            for (int i = 0; i < data.size(); i++){
                Cell cell = cellIterator.next();
                cell.setCellValue(data.get(i));
            }
        } catch (Exception e) {

        }
    }
}
