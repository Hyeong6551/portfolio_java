package com.company.project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class View_Admin_crud{
	// 테이블
	JFrame frame;
	JPanel panel;
	JLabel label;
	
	String [] column;	Object[][] rowData;
	DefaultTableModel model;
	JScrollPane scroll;
	JTable table;
	
	// 버튼
	JButton[] ad_button;
	Font[] font;
	Color[] color;


	// 생성자
	public View_Admin_crud() {
		frame = new JFrame("관리자 전용 페이지");
		ad_button = new JButton[] { 
				new JButton("책 추가"), new JButton("책 변경"), 
				new JButton("책 삭제"), new JButton("사용자 페이지"), 
				new JButton("종료") 
		};
		label = new JLabel("도서 목록");
		font = new Font[] { 
				new Font("맑은 고딕",Font.BOLD, 30),
				new Font("맑은 고딕",Font.BOLD, 32),
				new Font("함초롬돋움",Font.PLAIN , 20),
				new Font("함초롬돋음",Font.BOLD, 25)
		};
		column = new String[] {"책 번호", "책 이름", "저자", "출판사"};
		rowData = new Object[][] {};
		model = new DefaultTableModel(rowData, column);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		color = new Color[] {
				new Color(58,81,52),new Color(121,189,154),
				new Color(0xfafff1),new Color(0xa8dba8) ,new Color(207,240,158)
		};
	}
	
	public String[] getColumn() { return column; }  public void setColumn(String[] column) { this.column = column; }
	public Object[][] getRowData() { return rowData; }  public void setRowData(Object[][] rowData) { this.rowData = rowData; }
	public DefaultTableModel getModel() { return model; }  public void setModel(DefaultTableModel model) { this.model = model; }
	public JScrollPane getScroll() { return scroll; }  public void setScroll(JScrollPane scroll) { this.scroll = scroll; }
	public JTable getTable() { return table; }  public void setTable(JTable table) { this.table = table; }

	// function
	public void Show_Table() {

		// 테이블 설정
		frame.add(scroll);
		frame.add(label);
		table.getColumn("책 번호").setPreferredWidth(150);
		table.getColumn("책 이름").setPreferredWidth(625);
		table.getColumn("저자").setPreferredWidth(250);
		table.getColumn("출판사").setPreferredWidth(325);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setBackground(color[2]);
		table.setForeground(color[0]);
		table.setGridColor(color[0]);
		table.setRowHeight(30);
		table.setFont(font[2]);
	    table.getTableHeader().setBackground(color[3]);
	    table.getTableHeader().setForeground(color[0]);
	    table.getTableHeader().setFont(font[2]);
		scroll.setBounds(138, 100, 1250, 525);
		label.setBounds(710, 35, 350, 38);
		label.setFont(font[1]);
		label.setForeground(color[0]);
	}
	
	public void Show_Admin() {
		Show_Table();
		frame.setLayout(null);
		frame.setResizable(false);
		for(int i=0;i<ad_button.length;i++) {
			frame.add(ad_button[i]);
			ad_button[i].setFont(font[0]);
			ad_button[i].setBounds(50+(i*300), 750, 250, 75);
			ad_button[i].setBackground(color[1]);
			ad_button[i].setForeground(color[0]);
			ad_button[i].setBorderPainted(false);
		}
		frame.setSize(1550,900);
		frame.setVisible(true);
		frame.getContentPane().setBackground(color[4]);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}