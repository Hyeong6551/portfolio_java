package com.company.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

class BookRead implements BookProcess {

	@Override
	public void exec(ArrayList<BookInfo> books) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exec(ArrayList<BookInfo> books, ArrayList<MyBookInfo> myBooks, View_Admin_crud ad_crud,
			View_User_crud usr_crud) {
		// TODO Auto-generated method stub
		
	}

	@Override
	// 트러블 슈팅 dao.getConnection()을 여러번 쓰고 끄고 쓰고 끄고해서 에러
	public void exec(View_Admin_crud ad_crud, View_User_crud usr_crud) {
		BookDao dao = new BookDao();
		dao.getConnection();
		ArrayList<BookInfo> list = dao.readAllBook();	// db 연동 전체 데이터 가져오기
		
		DefaultTableModel ad_model = (DefaultTableModel) ad_crud.table.getModel();
		DefaultTableModel usr_model = (DefaultTableModel) usr_crud.table[0].getModel();
		ad_model.setNumRows(0);	
		usr_model.setNumRows(0);
		
		Iterator<BookInfo> iter= list.iterator();
		while(iter.hasNext()) {
			BookInfo temp = iter.next();
			Object[] data = {temp.getNo(), temp.getTitle(), temp.getAuthor(), temp.getPublisher()};
			ad_crud.model.addRow(data);
			usr_crud.model[0].addRow(data);
		}
		
		dao.getConnection();
		ArrayList<MyBookInfo> mlist = dao.readAllmyBook();
		
		DefaultTableModel usr_model2 = (DefaultTableModel) usr_crud.table[1].getModel();
		usr_model2.setNumRows(0);
		
		Iterator<MyBookInfo> miter = mlist.iterator();
		while(miter.hasNext()) {
			MyBookInfo temp = miter.next();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Object[] data = {temp.getMno(), temp.getName(), temp.getBookNo(), temp.getTitle() ,sdf.format(System.currentTimeMillis()) };
			usr_crud.model[1].addRow(data);
		}
	}
}
