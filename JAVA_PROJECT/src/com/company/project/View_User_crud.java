package com.company.project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class View_User_crud {
	// 테이블
	JFrame frame;
	JButton button;
	
	String [][] column;	Object[][][] rowData;
	DefaultTableModel[] model;
	JScrollPane[] scroll;
	JTable[] table;
	
	// 버튼
	JButton[] usr_button;
	Font [] font;
	Color [] color;
	JLabel [] label;
	
	View_Admin_crud ad_crud;
	
	// 생성자
	public View_User_crud() {	// 버튼 및 테이블
		frame = new JFrame("사용자 전용 페이지");
		label = new JLabel[] {new JLabel("대출 가능한 도서 목록"), new JLabel("내 도서 목록")};
		ad_crud = new View_Admin_crud();
		
		// 사용자 페이지 폰트
		font = new Font[] { 
				new Font("맑은 고딕",Font.BOLD, 30),
				new Font("맑은 고딕",Font.BOLD, 32),
				new Font("함초롬돋움",Font.PLAIN , 20),
				new Font("함초롬돋음",Font.BOLD, 25)
		};
		
		// 사용자 페이지 버튼
		usr_button = new JButton[] { 
				new JButton("책 대출"), new JButton("책 반납"), 
				new JButton("관리자 페이지"), new JButton("종료") 
		};
		column = new String[][] { new String[] {"책 번호", "책 이름", "저자", "출판사"}, new String[] {"번호", "사용자", "책 번호", "책 제목", "빌린 날짜"} };
		rowData = new Object[][][] { ad_crud.rowData, new Object[][] {} };
		model = new DefaultTableModel[] { new DefaultTableModel(rowData[0], column[0]),  new DefaultTableModel(rowData[1], column[1])};
		table = new JTable[] {new JTable(model[0]), new JTable(model[1]) };
		scroll = new JScrollPane[] {new JScrollPane(table[0]), new JScrollPane(table[1])};
		
		// 색상
		color = new Color[] {
				new Color(58,81,52),new Color(121,189,154),
				new Color(0xfafff1),new Color(0xa8dba8) ,new Color(207,240,158)
		};
	}
	public void Show_Table() {
		// 대출 가능한 도서 목록
		frame.add(scroll[0]);
		table[0].getColumn("책 번호").setPreferredWidth(150);
		table[0].getColumn("책 이름").setPreferredWidth(625);
		table[0].getColumn("저자").setPreferredWidth(250);
		table[0].getColumn("출판사").setPreferredWidth(325);
		scroll[0].setBounds(138, 100, 1250, 250);
		
		// 내 도서 목록
		frame.add(scroll[1]);
		table[1].getColumn("번호").setPreferredWidth(150);
		table[1].getColumn("사용자").setPreferredWidth(250);
		table[1].getColumn("책 번호").setPreferredWidth(150);
		table[1].getColumn("책 제목").setPreferredWidth(625);
		table[1].getColumn("빌린 날짜").setPreferredWidth(325);
		scroll[1].setBounds(138, 450, 1250, 250);
		
		// 테이블 설정
		for(int i=0;i<table.length;i++) {
			table[i].getTableHeader().setReorderingAllowed(false);
			table[i].getTableHeader().setResizingAllowed(false);
			table[i].setBackground(color[2]);
			table[i].setForeground(color[0]);
			table[i].setGridColor(color[0]);
			table[i].setRowHeight(30);
			table[i].setFont(font[2]);
		    table[i].getTableHeader().setBackground(color[3]);
		    table[i].getTableHeader().setForeground(color[0]);
		    table[i].getTableHeader().setFont(font[2]);
		}

		for(int i=0;i<label.length;i++) {
			label[i].setFont(font[1]);
			label[i].setForeground(color[0]);
			label[i].setBounds(620+(i*70), 35+(i*350), 350, 38);
			frame.add(label[i]);
		}
	}
	
	public void Show_User() {
		Show_Table();
		frame.setLayout(null);
		frame.setResizable(false);
		for(int i=0;i<usr_button.length;i++) {
			frame.add(usr_button[i]);
			if(i>=2) {
				usr_button[i].setBounds(350+(i*300), 750, 250, 75);
			} else {
				usr_button[i].setBounds(50+(i*300), 750, 250, 75);
			}
			usr_button[i].setFont(font[0]);
			usr_button[i].setBackground(color[1]);
			usr_button[i].setForeground(color[0]);
			usr_button[i].setBorderPainted(false);
		}
		frame.setSize(1550,900);
		frame.setVisible(true);
		frame.getContentPane().setBackground(color[4]);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}
