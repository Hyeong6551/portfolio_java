package com.company.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class BookDelete implements BookProcess{
	@Override
	public void exec(ArrayList<BookInfo> books) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Book no? > ");	int num = sc.nextInt();
		
		Iterator<BookInfo> iter = books.iterator();
		while(iter.hasNext()) {
			BookInfo b = iter.next();
			if(b.getNo() != num) {
				System.out.println("존재하지 않는 번호 입니다.");
			} else {
				iter.remove();
				System.out.println("삭제완료");
			}
		}
	}
	
	@Override
	public void exec(ArrayList<BookInfo> books, ArrayList<MyBookInfo> myBooks, View_Admin_crud ad_crud, View_User_crud usr_crud) {
		try {		
			int bookNo = Integer.parseInt(JOptionPane.showInputDialog("삭제할 책 번호를 입력해주쉐요"));
			int findNum = -1;
			boolean state = true;
			
			Iterator <BookInfo> iter = books.iterator();
			while(iter.hasNext()) {
				BookInfo temp = iter.next();
				if(temp.getNo() == bookNo ) { 
					findNum = temp.getNo(); state = temp.isBookState();
					if(findNum==-1) {
						JOptionPane.showMessageDialog(null, "존재하지 않는 번호입니다."); return; 
					} else {
						if(state) {
							iter.remove();
							ad_crud.model.removeRow(findNum-1);
							usr_crud.model[0].removeRow(findNum-1);
						} else {
							JOptionPane.showMessageDialog(null, "현재 대출받고 있는 책이므로 변경할 수 없습니다.");
						}
					}
				}
			}
			System.out.println(state);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "올바른 값을 입력해주세요.");
		} 
	}

	@Override
	public void exec(View_Admin_crud ad_crud, View_User_crud usr_crud) {
		try {
			int bookNo = Integer.parseInt(JOptionPane.showInputDialog("삭제할 책 번호를 입력해주쉐요"));
			BookDao dao = new BookDao();				
			dao.getConnection();	
			
			ArrayList<BookInfo> list = dao.readAllBook();
			Iterator<BookInfo> iter = list.iterator();
			while(iter.hasNext()) {
				BookInfo temp = iter.next();
				if(temp.getNo() == bookNo){ 
					Boolean bState = temp.isBookState(); 
					if(bState) {
						dao.getConnection();	
						dao.deleteBook(bookNo);
					} else {
						JOptionPane.showMessageDialog(null, "현재 대출중인 책이므로 삭제가 불가능합니다.");
					}
				}
			}

			ArrayList<BookInfo> mlist = new ArrayList<>();
			Iterator<BookInfo> miter =mlist.iterator();
			while(miter.hasNext()) {
				BookInfo mtemp = miter.next();
				ad_crud.model.removeRow(mtemp.getNo());
				usr_crud.model[0].removeRow(mtemp.getNo());
				break;
			}
			new BookRead().exec(ad_crud, usr_crud);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "올바른 값을 입력해주세요.");
		}
	}
}
