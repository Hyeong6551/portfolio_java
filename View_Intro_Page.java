package com.company.project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View_Intro_Page {
	JFrame frame;
	JPanel panel;
	JButton[] button;
	JLabel[] label;
	Font[] font;
	Color[] color;
	ImageIcon[] icon;

	public View_Intro_Page() {
		frame = new JFrame("선택 페이지");
		button = new JButton[] { new JButton("<html>관리자 <br>페이지</html>"), new JButton("<html>사용자 <br>페이지</html>") };
		font = new Font[] { new Font("맑은 고딕",Font.BOLD, 55),new Font("맑은 고딕",Font.BOLD, 60)};
		icon = new ImageIcon[] { new ImageIcon("src/com/company/project/book.png"), 
								 new ImageIcon("src/com/company/project/waterfall.jpg")};
		label = new JLabel[] { new JLabel(icon[0]), new JLabel(icon[1]), new JLabel("도서 관리 프로그램")};
		color = new Color[] {
				new Color(207,240,158),new Color(121,189,154),
				new Color(59,134,134),new Color(58,81,52)
		};
	}

	public void Show_Intro(){
		frame.setLayout(null);
		frame.setResizable(false);
//		frame.setLocation(300,300);
		for(int i=0;i<button.length;i++) {
			button[i].setBackground(color[1]);
			button[i].setForeground(color[3]);
			button[i].setFont(font[0]);
			button[i].setBorderPainted(false);
			frame.add(button[i]);
		}
		frame.add(label[0]);
		frame.add(label[1]);
		frame.add(label[2]);
		
		label[0].setBounds(75, 100, 560, 560);
		label[1].setBounds(660, 0, 650, 850);
		label[2].setBounds(75, 648, 550, 77);
		label[2].setFont(font[1]);
		label[2].setForeground(color[2]);
		button[0].setBounds(775,477,400,250);
		button[1].setBounds(775,125,400,250);
		
		frame.setBackground(color[0]);
		frame.setSize(1300, 850);
		frame.setVisible(true);
		frame.getContentPane().setBackground(color[0]);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
