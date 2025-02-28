package com.company.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class MyBookBorrow implements BookProcess{

	@Override
	public void exec(ArrayList<BookInfo> books) {
		
	}

	@Override
	public void exec(ArrayList<BookInfo> books, ArrayList<MyBookInfo> myBooks, View_Admin_crud ad_crud,View_User_crud usr_crud) {
		try {
			String name = JOptionPane.showInputDialog("당신의 이름을 입력해주세요");
			int bookNo =  Integer.parseInt(JOptionPane.showInputDialog("대출할 책의 번호를 입력하세요"));
			boolean run=false;
			BookInfo no = new BookInfo(bookNo);
			
			Iterator <BookInfo> iter = books.iterator();
			while(iter.hasNext()) {
				if(iter.next().getNo() == bookNo) { myBooks.add(new MyBookInfo(name, no)); run=true; break; }
			}
			
			if (run) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(BookInfo b : books) {
					if (b.getNo()==bookNo) {
						Object[] data = { MyBookInfo.cnt, name, bookNo ,b.getTitle(), sdf.format(System.currentTimeMillis()) };
						b.setBookState(false);
						usr_crud.model[1].addRow(data);
						System.out.println(books); break;
					}
				}
			}
			run=false;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "올바른 값을 입력해주세요.");
		}
	}

	@Override
	public void exec(View_Admin_crud ad_crud, View_User_crud usr_crud) {
		try {
			String name = JOptionPane.showInputDialog("당신의 이름을 입력해주세요");
			int bookNo =  Integer.parseInt(JOptionPane.showInputDialog("대출할 책의 번호를 입력하세요"));
			MyBookInfo myBooks = new MyBookInfo();
			BookDao dao = new BookDao();
			
			// bookState = false 
			dao.getConnection();
			myBooks.setBookNo(bookNo);
			dao.borrowBookStateFalse(myBooks);
			
			// borrow
			dao.getConnection();
			myBooks.setName(name);
			myBooks.setBookNo(bookNo);
			dao.borrowBook(myBooks);

			dao.getConnection();
			ArrayList<MyBookInfo> list = dao.readAllmyBook();
			Iterator<MyBookInfo> iter = list.iterator();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(iter.hasNext()) {
				MyBookInfo temp = iter.next();
				if(temp.getBookNo()==bookNo) {
					Object[] data = { MyBookInfo.cnt, temp.getName(), temp.getBookNo(), temp.getTitle(), sdf.format(System.currentTimeMillis()) };
					usr_crud.model[1].addRow(data);
					break;
				}
			}
			new BookRead().exec(ad_crud, usr_crud);	// 이 코드를 넣어야 책 번호가 db에 반영
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "올바른 값을 입력해주세요.");
		}
	}
}
