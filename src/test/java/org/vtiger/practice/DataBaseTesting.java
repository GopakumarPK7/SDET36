package org.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseTesting {

	public static void main(String[] args) throws SQLException
	{
	//Create the object for driver which is given by database vendors	
	Driver driver=new Driver();
	//Register the driver to jdbc
	DriverManager.registerDriver(driver);
	//Establish the connection
	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tyss","root","root");
	//Create the statement
	Statement statement=connection.createStatement();
	//excecute query
	ResultSet result=statement.executeQuery("Select * from sdet36;");
	//Validate the data
	while(result.next())
	{
		System.out.println(result.getString(1)+" "+result.getString("empname"));
	}
	connection.close();
	}
	

}
