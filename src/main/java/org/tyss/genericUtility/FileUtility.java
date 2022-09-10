package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

/**
 * This class contains reusable methods for Csv file and property file
 * @author admin
 *
 */
public final class FileUtility
{
private Properties properties;
/**
 * This method is used for initialize the property file
 */
public void initializePropertyFile(String filePath)
{
	FileInputStream fis=null;
			try {
				fis=new FileInputStream(filePath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			properties=new Properties();
			try {
				properties.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
}
/**
 * This method is used to fetch the data from property file
 * @param key
 * @return
 */
public String getDataFromProperty(String key)
{
	return properties.getProperty(key).trim();
}
/**
 * This method is used to fetch the data from csv file
 */
public String getDataFromCSV(String csvFilePath,int rowNumber,int cellNumber)
{
	CSVReader reader=null;
	String data=null;
	try {
		reader=new CSVReader(new FileReader(csvFilePath));
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
	try {
		data=reader.readAll().get(rowNumber)[cellNumber];
	} catch (IOException e) {
		e.printStackTrace();
	} catch (CsvException e) {
		e.printStackTrace();
	}
	return data;
}
}