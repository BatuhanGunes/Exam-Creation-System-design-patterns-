package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;


public class ExcelConnection {
	
	private String FILE_PATH = "ogrenci.xlsx";
	private DataFormatter formatter = new DataFormatter();
	private LinkedList<Long> ogrNo = new LinkedList<>();
	private LinkedList<String> ogrAdveSoyad = new LinkedList<>();
    private FileInputStream fis = null;
    
    private static ExcelConnection obj;	
    private ExcelConnection(){
    
    }
    public static ExcelConnection getObj() {
    	if(obj == null) {
    		 obj = new ExcelConnection();
    	}return obj;
    }

	public void okuma() {
		try {
            fis = new FileInputStream(FILE_PATH); 
            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(fis);
            int numberOfSheets = workbook.getNumberOfSheets(); 
            //looping over each workbook sheet
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Iterator rowIterator = sheet.rowIterator(); 
                //iterating over each row
                while (rowIterator.hasNext()) {
                	Row row = (Row) rowIterator.next();
                    Iterator cells = row.cellIterator();                     
                    //Iterating over each cell (column wise)  in a particular row.
                    while (cells.hasNext()) {
                    	Cell cell = (Cell) cells.next();
                        CellType type = cell.getCellTypeEnum();
                        //The Cell Containing String will is name.
                        if (type == CellType.STRING) {
                            ogrAdveSoyad.add(cell.getStringCellValue());
                            //System.out.println(cell.getStringCellValue());		
                            //The Cell Containing numeric value will contain marks
                        } else if (type == CellType.NUMERIC) {
                        	String numara = formatter.formatCellValue(cell);
                        	ogrNo.add(Long.valueOf(numara));
                            //  System.out.println(cell.getNumericCellValue());
                            //Cell with index 1 contains marks in Maths
                            if (cell.getColumnIndex() == 1) {                            
                            }
                            //Cell with index 2 contains marks in Science
                        }
                    }
                    //end iterating a row, add all the elements of a row in list
                }
            }
            fis.close();
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	public LinkedList<Long> getOgrNo() {
		return ogrNo;
	}

	public void setOgrNo(LinkedList<Long> ogrNo) {
		this.ogrNo = ogrNo;
	}

	public LinkedList<String> getOgrAdveSoyad() {
		return ogrAdveSoyad;
	}

	public void setOgrAdveSoyad(LinkedList<String> ogrAdveSoyad) {
		this.ogrAdveSoyad = ogrAdveSoyad;
	}
 	
}

