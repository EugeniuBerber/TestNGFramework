package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelReading {
    static Workbook book;
    static Sheet sheet;

    public static void openExcel(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            book = new XSSFWorkbook(fileInputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSheet(String sheetName) { // this opens the specified Sheet
        sheet = book.getSheet(sheetName);
    }
    public static int getRowCount(){ // this returns physical num of rows
        return sheet.getPhysicalNumberOfRows();
    }
    public static int getColsCount(int rowIndex){ // this returns physical num of cells in a  specified row
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }
    public static String getCellData(int rowIndex, int colIndex){ // to colect data from every cell in a form of String
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }
    public static List<Map<String,String>> excelIntoListMap(String filePath, String sheetName){
        openExcel(filePath);
        getSheet(sheetName);
        List<Map<String,String >> listData = new ArrayList<>();
        // outerloop
        for (int row = 1; row < getRowCount(); row++) {
            //creating a Map<> for every row
            Map<String ,String> map = new LinkedHashMap<>();
            //looping through the values of all cells
            for (int col = 0; col < getColsCount(row); col++) {
                map.put(getCellData(0,col), getCellData(row,col));
            }//adding the map in List
             listData.add(map);
        }return listData;
    }
}
