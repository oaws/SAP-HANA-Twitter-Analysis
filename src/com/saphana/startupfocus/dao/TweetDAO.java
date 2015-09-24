package com.saphana.startupfocus.dao;

import com.saphana.startupfocus.entity.*;
import com.saphana.startupfocus.config.*;
import java.sql.*;
import java.util.List;


public class TweetDAO {
	private Connection conn = null;

	public static TweetDAO getInstance() {
		TweetDAO tDAO = new TweetDAO();
		 try {
			Class.forName("com.sap.db.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 String url = "jdbc:sap://ushdc8514.us.deloitte.com:30015/OSIDDIQUI";
		    String user = "OSIDDIQUI";
		    String password = "Purple2014";
	try {
		tDAO.setConn(java.sql.DriverManager.getConnection(url, user, password));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return tDAO;
	}
	
	public void insert(Tweet tweet){
		if (conn != null) {
			PreparedStatement pstmt;
			try {
				String stmt = "insert into \"" + Configurations.HDB_SCHEMA + "\"." +  
						      "\"TWEET_TABLE\" values(\"" + Configurations.HDB_SCHEMA + "\"." + 
						      "\"TWEET_SEQ\".NEXTVAL,?,?,?,?)";
				
				pstmt = conn.prepareStatement(stmt);
				pstmt.setString(1, tweet.getUserName());
				Date sqlDate = new Date(tweet.getCreatedAt().getTime());
				pstmt.setDate(2, sqlDate);
				pstmt.setString(3, tweet.getText());
				pstmt.setString(4, tweet.getHashTagsString());
				pstmt.execute();
				
				System.out.println("Insert to HANA successful: " + tweet.getText());
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insert(List<Tweet> tweets){
		for(Tweet t:tweets){
			this.insert(t);
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public void commitAndClose(){
		try {
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
