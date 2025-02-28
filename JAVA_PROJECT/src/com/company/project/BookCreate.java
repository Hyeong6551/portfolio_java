package com.company.project;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class BookCreate implements BookProcess{
	@Override
	public void exec(ArrayList<BookInfo> books) {
		Scanner sc = new Scanner(System.in);
		System.out.print("book title? > ");			String title = sc.next();
		System.out.print("book author? > ");		String author = sc.next();
		System.out.print("book publisher? > ");		String publisher = sc.next();
		
		books.add(new BookInfo(title, author, publisher, true));
		System.out.println("제목 : "+title+" | 저자 : "+author+" | 출판사 : "+publisher);
		System.out.println("현재 책 수량 : "+BookInfo.cnt);
	}
	
	@Override
	public void exec(ArrayList<BookInfo> books, ArrayList<MyBookInfo> myBooks,View_Admin_crud ad_crud, View_User_crud usr_crud) {
		String title = JOptionPane.showInputDialog("책 제목을 입력해주세요");
		String author = JOptionPane.showInputDialog("저자를 입력해주세요");
		String publisher = JOptionPane.showInputDialog("출판사를 입력해주세요");
		
		books.add(new BookInfo(title, author, publisher, true));
		Object[] data = { BookInfo.cnt, title, author, publisher };
		ad_crud.model.addRow(data);
		usr_crud.model[0].addRow(data);
		System.out.println(books);
	}

	@Override
	public void exec(View_Admin_crud ad_crud, View_User_crud usr_crud) {
		String title = JOptionPane.showInputDialog("책 제목을 입력해주세요");
		String author = JOptionPane.showInputDialog("저자를 입력해주세요");
		String publisher = JOptionPane.showInputDialog("출판사를 입력해주세요");
		
		BookDao dao = new BookDao(); dao.getConnection();
		BookInfo book = new BookInfo();
		book.setTitle(title);	book.setAuthor(author);		
		book.setPublisher(publisher);	book.setBookState(true);
		dao.createBook(book);
		
		Object[] data = { BookInfo.cnt, title, author, publisher };
		ad_crud.model.addRow(data);
		usr_crud.model[0].addRow(data);
		new BookRead().exec(ad_crud, usr_crud);
	}
}
