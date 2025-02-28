package com.company.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class BookUpdate implements BookProcess {
	@Override
	public void exec(ArrayList<BookInfo> books) {
		Scanner sc = new Scanner(System.in);
		System.out.print("book no? >");		int num = sc.nextInt();
		Iterator <BookInfo> iter = books.iterator();
		while(iter.hasNext()) {
			BookInfo b = iter.next();
			if(b.getNo() != num) {
				System.out.println("존재하지 않는 번호입니다.");
			} else {
				System.out.print("book title? > ");		String title = sc.next();
				System.out.print("book author > ");		String author = sc.next();
				System.out.print("book publisher > ");	String publisher = sc.next();
				
				b.setTitle(title);	b.setAuthor(author);  b.setPublisher(publisher);  break;
			}
		}
	}
	
	@Override
	public void exec(ArrayList<BookInfo> books,  ArrayList<MyBookInfo> myBooks, View_Admin_crud ad_crud, View_User_crud usr_crud) {
		try {
			int upNum = Integer.parseInt(JOptionPane.showInputDialog("수정할 책 번호를 입력해주세요"));
			int findNum = -1;
			boolean state = true;
			
			// 전체 목록
			Iterator <BookInfo> iter = books.iterator();
			while(iter.hasNext()) {
				BookInfo temp = iter.next();
				if(temp.getNo() == upNum ) { 
					findNum = temp.getNo(); state = temp.isBookState(); break; 
				}
			}
			System.out.println(state);
			
			if(findNum ==-1 ) { JOptionPane.showMessageDialog(null, "존재하지 않는 번호입니다."); return; }
			else {
				if(state) {	
					String title = JOptionPane.showInputDialog("수정할 제목을 입력해주세요"); 
					String author = JOptionPane.showInputDialog("수정할 저자를 입력해주세요."); 
					String publisher = JOptionPane.showInputDialog("수정할 출판사를 입력해주세요."); 
					if(title != null && author != null && publisher != null) {
						books.get(upNum-1).setTitle(title);
						books.get(upNum-1).setAuthor(author);
						books.get(upNum-1).setPublisher(publisher);
						
						ad_crud.model.setValueAt(title, upNum-1, 1);
						ad_crud.model.setValueAt(author, upNum-1, 2);
						ad_crud.model.setValueAt(publisher, upNum-1, 3);
						
						usr_crud.model[0].setValueAt(title, upNum-1, 1);
						usr_crud.model[0].setValueAt(author, upNum-1, 2);
						usr_crud.model[0].setValueAt(publisher, upNum-1, 3);
					}
				} else {
					JOptionPane.showMessageDialog(null, "현재 대출받고 있는 책이므로 변경할 수 없습니다.");
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "올바른 값을 입력해주세요.");
		}
	}

	@Override
	public void exec(View_Admin_crud ad_crud, View_User_crud usr_crud) {
		try {
			int upNum = Integer.parseInt(JOptionPane.showInputDialog("수정할 책 번호를 입력해주세요"));
			BookDao dao = new BookDao();	dao.getConnection();
			BookInfo books = new BookInfo();

			ArrayList<BookInfo> list = dao.readAllBook();
			Iterator<BookInfo> iter = list.iterator();
			while(iter.hasNext()) {
				BookInfo temp = iter.next();
				if(temp.getNo() == upNum){ 
					Boolean bState = temp.isBookState(); 
					if(bState) {
						String title = JOptionPane.showInputDialog("수정할 제목을 입력해주세요"); 
						String author = JOptionPane.showInputDialog("수정할 저자를 입력해주세요."); 
						String publisher = JOptionPane.showInputDialog("수정할 출판사를 입력해주세요."); 
						dao.getConnection();
						books.setNo(upNum);		books.setTitle(title);	books.setAuthor(author);	books.setPublisher(publisher);
						dao.updateBook(books);
						new BookRead().exec(ad_crud, usr_crud);
					} else {
						JOptionPane.showMessageDialog(null, "현재 대출중인 책이므로 변경이 불가능합니다.");
					}
				}
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "올바른 값을 입력해주세요.");
		}
	}
}
