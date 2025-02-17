package com.company.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInfoTable {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "tiger";
		String sql_tb = "CREATE TABLE BookInfo ("
				+ "no NUMBER PRIMARY KEY NOT NULL,"
				+ "title VARCHAR2(100) NOT NULL,"
				+ "author VARCHAR2(20) NOT NULL,"
				+ "publisher VARCHAR2(30) NOT NULL,"
				+ "bookState VARCHAR2(1) CHECK (bookState IN('T','F')) NOT NULL)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw);
			
			if(conn != null) {
				System.out.println("success");
			}
			pstmt = conn.prepareStatement(sql_tb);
			pstmt.executeQuery();
			System.out.println(pstmt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
}

/*
create SEQUENCE seq_myBookInfo;

CREATE TABLE BookInfo (
    bno NUMBER PRIMARY KEY NOT NULL,
    title VARCHAR2(100) NOT NULL,
    author VARCHAR2(20) NOT NULL,
    publisher VARCHAR2(30) NOT NULL,
    bookState VARCHAR2(5) CHECK (bookState IN('true','false')) NOT NULL);
*/