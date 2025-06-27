package utlity;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	 public static Object[][] getTestData(String filePath, String sheetName) throws IOException {
	        FileInputStream fis = new FileInputStream(filePath);
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        XSSFSheet sheet = workbook.getSheet("Sheet1");
	        
	        int totalRows = sheet.getLastRowNum();
	        int totalCells = sheet.getRow(0).getLastCellNum();
	        
	        Object[][] data = new Object[totalRows][totalCells];
	        
	        for (int r = 1; r <= totalRows; r++) {  // Start from row 1 to skip header
	            XSSFRow currentRow = sheet.getRow(r);
	            for (int c = 0; c < totalCells; c++) {
	                XSSFCell cell = currentRow.getCell(c);
	                switch (cell.getCellType()) {
	                    case STRING:
	                        data[r-1][c] = cell.getStringCellValue();
	                        break;
	                    case NUMERIC:
	                        data[r-1][c] = cell.getNumericCellValue();
	                        break;
	                    case BOOLEAN:
	                        data[r-1][c] = cell.getBooleanCellValue();
	                        break;
	                    default:
	                        data[r-1][c] = "";
	                }
	            }
	        }
	        workbook.close();
	        fis.close();
	        return data;
	    }
}
