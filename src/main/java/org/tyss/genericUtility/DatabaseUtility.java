package org.tyss.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

public final class DatabaseUtility {
	private Connection connection;

	/**
	 * This method is used to establish connection with Mysql Database
	 */
	public void getConnectionWithDB(String url, String username, String password) {
		Driver dbDriver = null;
		try {
			dbDriver = new Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			DriverManager.registerDriver(dbDriver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to modify the data in database
	 */
	public int modifyDataInDB(String sqlQuery) {
		int count = 0;
		try {
			count = connection.createStatement().executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * This method is used to close the database connection
	 */
	public void closeDBConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connection closed");
	}

/**
 * This method is used to get the data from the database in the form of List<String>
 */
public List<String> getDataFromDBByColoumnName(String query,String coloumnName)
{
ResultSet results=null;
List<String> List=new ArrayList<>();
try {
	results=connection.createStatement().executeQuery(query);
} catch (SQLException e) {
	e.printStackTrace();
}
try {
	while(results.next())
	{
		List.add(results.getString(coloumnName));
	}
} catch (SQLException e) {
	e.printStackTrace();
}
return List;
}
public List<String> getDataFromDB(String coloumnNameOrcoloumnIndex,String query)
{
ResultSet results=null;
List<String> list=new ArrayList<>();
String s1="";
for(int i=0;i<coloumnNameOrcoloumnIndex.length();i++)
{
	if(Character.isDigit(coloumnNameOrcoloumnIndex.charAt(i)))
	{
		s1=s1+coloumnNameOrcoloumnIndex.charAt(i);
	}
	else
	{
	break;	
	}
	}
try {
	results=connection.createStatement().executeQuery(query);
} catch (SQLException e) {
	e.printStackTrace();
}
try {
	while(results.next())
	{
		if(coloumnNameOrcoloumnIndex.length()==s1.length())
		{
			list.add(results.getString(coloumnNameOrcoloumnIndex));
		}
		return list;	
	}
} catch (SQLException e) {
	e.printStackTrace();
}
return list;
}
public List<String> getDataFromDB(String query,int coloumnIndex)
{
ResultSet results=null;
List<String> List=new ArrayList<>();
try {
	results=connection.createStatement().executeQuery(query);
} catch (SQLException e) {
	e.printStackTrace();
}
try {
	while(results.next())
	{
		List.add(results.getString(coloumnIndex));
	}
} catch (SQLException e) {
	e.printStackTrace();
}
return List;
}

/**
 * This method is used to verify whether the exected data is resent or not
 */
public boolean verifyDataInDB(String query,String coloumnNameOrcoloumnIndex,String expecteddata)
{
	List<String> list=getDataFromDB(query,coloumnNameOrcoloumnIndex);
	System.out.println(list);
	boolean flag=false;
	for(String data:list)
	{
		if(data.equalsIgnoreCase(expecteddata))
		{
			flag=true;
			break;
		}
	}
	return flag;
}
}