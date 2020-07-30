package Helpers;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelUtils {

    static XSSFWorkbook workbook;
    static XSSFSheet workSheet;

    public ExcelUtils(String excelPath, String sheetName) throws IOException {
        workbook = new XSSFWorkbook(excelPath);
        workSheet = workbook.getSheet(sheetName);
    }

    public static int getRowCount(){
        int rowCount =0;
        rowCount = workSheet.getPhysicalNumberOfRows();
        System.out.println("Row Count ******" + rowCount);
        return rowCount;
    }

    public static int getColumnCount(){
        int columnCount=0;
        columnCount= workSheet.getRow(0).getPhysicalNumberOfCells();
        System.out.println("Column Count ******" + columnCount);
        return columnCount;
    }

    public String getCellData(int rowNum, int colNum){
        DataFormatter dataFormatter = new DataFormatter();
        String cellValue = dataFormatter.formatCellValue(workSheet.getRow(rowNum).getCell(colNum));
        System.out.println("cellValue"+ cellValue);
        return cellValue;
    }


}
