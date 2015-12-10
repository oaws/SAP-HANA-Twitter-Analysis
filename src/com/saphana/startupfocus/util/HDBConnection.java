package com.saphana.startupfocus.util;

import java.sql.*;

public class HDBConnection {
	public static Connection connection = null;
	
// Test HDB Connection 
	public static void main(String[] argv) throws ClassNotFoundException {
		try {
		    Class.forName("com.sap.db.jdbc.Driver");

		    String url = "####";
		    String user = "####";
		    String password = "####";

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

