package com.company.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MyBookInfoTable {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";	String pw="tiger";
		String sql_tb = "CREATE TABLE MyBookInfo ("
				+ "mno NUMBER PRIMARY KEY NOT NULL,"
				+ "bname VARCHAR2(20) NOT NULL,"
				+ "bookNo NUMBER NOT NULL,"
				+ "bdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
				+ "foreign key(bookNo) references bookinfo(no))";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw);
			pstmt = conn.prepareStatement(sql_tb);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/*
CREATE SEQUENCE seq_myBookInfo;

CREATE TABLE MyBookInfo (
    mno NUMBER PRIMARY KEY NOT NULL,
    name VARCHAR2(20) NOT NULL,
    bno NUMBER CONSTRAINT bno_fk REFERENCES bookinfo(bno),
    bdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
*/