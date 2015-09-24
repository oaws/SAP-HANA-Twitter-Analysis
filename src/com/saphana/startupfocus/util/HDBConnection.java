package com.saphana.startupfocus.util;

import java.sql.*;

public class HDBConnection {
	public static Connection connection = null;
	
// Test HDB Connection 
	public static void main(String[] argv) throws ClassNotFoundException {
		try {
		    Class.forName("com.sap.db.jdbc.Driver");

		    String url = "jdbc:sap://ushdc8514.us.deloitte.com:30015/OSIDDIQUI";
		    String user = "OSIDDIQUI";
		    String password = "Purple2014";

		    Connection cn = java.sql.DriverManager.getConnection(url, user, password);

			System.out.println("Connection to HANA successful!");

			Statement stmt = cn.createStatement();
			ResultSet resultSet = stmt.executeQuery("Select 'helloworld' from dummy");
			resultSet.next();
			String hello = resultSet.getString(1);
			System.out.println(hello);

		} catch (Exception e) {
		    e.printStackTrace();
		}
	
		
			
		}
	}

