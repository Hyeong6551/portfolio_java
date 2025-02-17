package com.company.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class use_Book_mvc{
	// model
	ArrayList<BookInfo> books;
	ArrayList<MyBookInfo> myBooks;
	
	// view
	View_Intro_Page intro;
	View_Admin_crud admin;
	View_User_crud user;

	// controller
	BookProcess controller;
	BookProcess[] process;

	// constructor
	public use_Book_mvc() {
		books = new ArrayList<>();
		myBooks = new ArrayList<>();
		
		intro = new View_Intro_Page();		intro.Show_Intro();
		admin = new View_Admin_crud();
		user = new View_User_crud();
		
		process = new BookProcess[] {
			new BookCreate(), new BookUpdate(), 
			new BookDelete(), new MyBookBorrow(),
			new MyBookReturn()
		};
	}
	
	// function
	public void start_Intro() {
		intro.button[0].addActionListener(new ActionListener() {	// 관리자 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				intro.frame.dispose();
				admin.Show_Admin();
				start_Admin();
				start_User();
			}
		});
		
		intro.button[1].addActionListener(new ActionListener() {	// 사용자 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				intro.frame.dispose();
				user.Show_User();
				start_Admin();
				start_User();
			}
		});
	}
	
	//////////////////////////////////////		관리자 페이지
	public void start_Admin() {		
		new BookRead().exec(admin, user);
		admin.ad_button[0].addActionListener(new ActionListener() {		// 책 추가
			@Override
			public void actionPerformed(ActionEvent e) {
				controller = process[0];	controller.exec(admin, user);
			}
		});
		
		admin.ad_button[1].addActionListener(new ActionListener() {		// 책 변경
			@Override
			public void actionPerformed(ActionEvent e) {
				controller = process[1];	controller.exec(admin, user);
			}
		});
		
		admin.ad_button[2].addActionListener(new ActionListener() {		// 책 삭제
			@Override
			public void actionPerformed(ActionEvent e) {
				controller = process[2];	controller.exec(admin, user);
			}
		});
		
		admin.ad_button[3].addActionListener(new ActionListener() {		// 사용자 페이지로 이동
			@Override
			public void actionPerformed(ActionEvent e) {
				admin.frame.dispose();
				user.Show_User();
			}
		});
		
		admin.ad_button[4].addActionListener(new ActionListener() {		// 종료
			@Override
			public void actionPerformed(ActionEvent e) {
				admin.frame.dispose();
			}
		});
	}
	
	//////////////////////////////////////		사용자 페이지
	public void start_User() {
		new BookRead().exec(admin, user);
		user.usr_button[0].addActionListener(new ActionListener() {		//  책 대출
			@Override
			public void actionPerformed(ActionEvent e) {
				controller = process[3];	controller.exec(admin, user);
			}
		});
		
		user.usr_button[1].addActionListener(new ActionListener() {		// 책 반납
			@Override
			public void actionPerformed(ActionEvent e) {
				controller = process[4];	controller.exec(admin, user);
			}
		});
		
		user.usr_button[2].addActionListener(new ActionListener() {		// 관리자 페이지 이동
			@Override
			public void actionPerformed(ActionEvent e) {
				user.frame.dispose();
				admin.Show_Admin();
			}
		});
		
		user.usr_button[3].addActionListener(new ActionListener() {		// 종료
			@Override
			public void actionPerformed(ActionEvent e) {
				user.frame.dispose();
			}
		});
	}
}

public class BookMVC {
	public static void main(String[] args) {
		use_Book_mvc mvc = new use_Book_mvc();
		mvc.start_Intro();
	}
}
