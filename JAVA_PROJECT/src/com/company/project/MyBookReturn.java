package com.company.project;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class MyBookReturn implements BookProcess{

	@Override
	public void exec(ArrayList<BookInfo> books) {
		
	}

	@Override
	public void exec(ArrayList<BookInfo> books, ArrayList<MyBookInfo> myBooks, View_Admin_crud ad_crud,View_User_crud usr_crud) {
		try {
			int bookNo = Integer.parseInt(JOptionPane.showInputDialog("반납할 번호를 입력해세요"));
			int findNum = -1;
			int cnt=0;
			
			// 내 도서목록
			Iterator <MyBookInfo> iter = myBooks.iterator(); // 줄
			while(iter.hasNext()) { // 처리대상확인
				MyBookInfo temp = iter.next(); //꺼내오기
				if(temp.getMno() == bookNo ) {findNum = temp.getBookNo(); iter.remove(); break; }
				cnt++;
			}

			if(findNum==-1) {
				JOptionPane.showMessageDialog(null, "존재하지 않는 번호입니다.");
			} else {
				// 전체 목록
				Iterator <BookInfo> iter2 = books.iterator(); // 줄
				while(iter2.hasNext()) { // 처리대상확인
					BookInfo temp = iter2.next(); //꺼내오기
					if(temp.getNo() == findNum ) { temp.setBookState(true);   break; }
				}
				usr_crud.model[1].removeRow(cnt);
			}
			System.out.println(books);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "올바른 값을 입력해주세요.");
		}
	}

	@Override
	public void exec(View_Admin_crud ad_crud, View_User_crud usr_crud) {
		try {
			int bookNo = Integer.parseInt(JOptionPane.showInputDialog("반납할 번호를 입력해세요"));
			BookDao dao = new BookDao();	
			MyBookInfo myBooks = new MyBookInfo();
			
			// boolean true
			dao.getConnection();
			myBooks.setBookNo(bookNo);
			dao.returnBookStateTrue(myBooks);
			
			dao.getConnection();
			dao.returnBook(bookNo);
			
			ArrayList<MyBookInfo> list = new ArrayList<>();
			Iterator<MyBookInfo> iter = list.iterator();
			while(iter.hasNext()) {
				MyBookInfo temp = iter.next();
				usr_crud.model[1].removeRow(temp.getMno());
			}
			new BookRead().exec(ad_crud, usr_crud);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "올바른 값을 입력해주세요.");
		}
	}
}
