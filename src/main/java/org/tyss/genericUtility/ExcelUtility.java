package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains reusable methods for excel file
 * @author admin
 *
 */
public final class ExcelUtility 
{
private Workbook workbook;
/**
 * This method is used to initialize the excel file
 */
public void initilizeExcelFile(String excelPath)
{
	FileInputStream fisExcel=null;
	try {
		fisExcel=new FileInputStream(excelPath);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		workbook=WorkbookFactory.create(fisExcel);
	} catch (EncryptedDocumentException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
/**
 * This method is used to fetch data from excel
 * @return 
 */
public String getDataFromExcel(String sheetname,int rowNumber,int cellNumber)
{
	Sheet sheet = workbook.getSheet(sheetname);
	return new DataFormatter().formatCellValue(sheet.getRow(rowNumber).getCell(cellNumber));
	
}
/**This method is used to save data in excel
 * 
 * @param sheetname
 * @param rowNumber
 * @param cellNumber
 * @return
 */
public void saveDataIntoExcel(String excelSavePath)
{
	FileOutputStream fos=null;
	try {
		fos=new FileOutputStream(excelSavePath);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		workbook.write(fos);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
/**
 * This method is used to close the excel workbook
 */
public void closeWorkbook()
{
	try {
		workbook.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
/**
 * This method is used to write the data to the excel
 */
public void setDataIntoExcel(String sheetName,int rowNumber,int cellNumber,String value)
{
	Sheet sheet=workbook.getSheet(sheetName);
	sheet.getRow(rowNumber).createCell(cellNumber).setCellValue(value);
}
}
