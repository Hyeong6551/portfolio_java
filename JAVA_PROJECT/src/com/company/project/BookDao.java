package com.company.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class BookDao {
	public static Connection conn;
	
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";	String pw = "tiger";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw);
		} catch (Exception e) { e.printStackTrace(); }
		return conn;
	}
	
	// 책 추가
	public int createBook(BookInfo book) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO bookinfo (bno, title, author, publisher, bookState)"
				+ " VALUES (seq_bookinfo.nextval, ?,?,?,'true')";
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}
	
	// 책 변경
	public int updateBook(BookInfo book) {
		PreparedStatement pstmt = null;
		String sql = "UPDATE bookinfo SET title=?, author=?, publisher=? WHERE bno=?";
		int result=-1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setInt(4, book.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if( conn != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}
	
	// 책 삭제
	public int deleteBook(int no) {
		PreparedStatement pstmt = null;
		String sql = "Delete from bookinfo where bno=?";
		int result=-1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if( conn != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}
	
	// 모든 책 불러오기
	public ArrayList<BookInfo> readAllBook(){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from bookinfo";
		ArrayList<BookInfo> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new BookInfo(
					rset.getInt("bno"), rset.getString("title"), 
					rset.getString("author"), rset.getString("publisher"), rset.getBoolean("bookState")
					)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( rset != null) {try { rset.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if( pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if( conn != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return list;
	}
	
	// 대출받은 모든 책 불러오기
	public ArrayList<MyBookInfo> readAllmyBook(){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select mno, name, b.bno, title, bdate "
				+ "from bookinfo b, mybookinfo m "
				+ "where b.bno=m.bno";
		ArrayList<MyBookInfo> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new MyBookInfo(	// 테이블 별칭쓰면 안됨
					rset.getInt("mno"), rset.getString("name"),
					rset.getInt("bno"), rset.getString("title") ,rset.getString("bdate")
					)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( rset != null) {try { rset.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if( pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if( conn != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return list;
	}
	
	// 책 대출
	public int borrowBook(MyBookInfo myBooks) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO mybookinfo (mno,name, bno, bdate) "
				+ "values(seq_mybookinfo.nextval,?,?,current_timestamp)";
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myBooks.getName());
			pstmt.setInt(2, myBooks.getBookNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if( conn != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}
	
	// 책 대출 시 책 상태 false;
	public int borrowBookStateFalse(MyBookInfo myBooks) {
		PreparedStatement pstmt = null;
		String sql = "Update bookinfo set bookState='false' where bno=?";
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myBooks.getBookNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if( conn != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}
	
	// 책 반납
	public int returnBook(int no) {
		PreparedStatement pstmt = null;
		String sql = "delete from mybookinfo where mno=?";
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if( conn != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}
	
	// 책 반납 시 책 상태 true
	public int returnBookStateTrue(MyBookInfo myBooks) {
		PreparedStatement pstmt = null;
		String sql = "Update bookinfo set bookState='true' where bno=?";
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myBooks.getBookNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if( conn != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}

	public static void main(String[] args) {
		BookDao dao = new BookDao();
		// 책 추가
//		dao.getConnection();
//		BookInfo book = new BookInfo();
//		book.setTitle("ddzz");
//		book.setAuthor("dddzz");
//		book.setPublisher("ddddzz");
//		if( dao.createBook(book) > 0 ) { System.out.println("성공");};
		
		// 책 변경
//		dao.getConnection();
//		BookInfo book = new BookInfo();
//		book.setNo(5); book.setTitle("asdf"); book.setAuthor("asdfqwe"); book.setPublisher("zxvsd");
//		if( dao.updateBook(book) > 0 ) { System.out.println("성공");} else {System.out.println("실패");};
	
		// 책 삭제
//		dao.getConnection();
//		if(dao.deleteBook(5)>0) {System.out.println("성공");}
		
		// 모든 책 조회
		dao.getConnection();
		System.out.println(dao.readAllBook());
		
		// 대출받은 책 조회
		dao.getConnection();
		System.out.println(dao.readAllmyBook());

		// 책 대출
//		dao.getConnection();
//		MyBookInfo myBooks = new MyBookInfo();
//		myBooks.setBookNo(27); 
//		dao.borrowBookStateFalse(myBooks);
//		dao.getConnection();
//		myBooks.setBookNo(27); 
//		myBooks.setName("hello");
//		dao.borrowBook(myBooks);
		
		// 책 반납
//		dao.getConnection();
//		MyBookInfo myBooks = new MyBookInfo();
//		myBooks.setBookNo(1);
//		dao.returnBookStateTrue(myBooks);
//		dao.getConnection();
//		if(dao.returnBook(1) > 0) {System.out.println("반납 성공");};
	}
}
