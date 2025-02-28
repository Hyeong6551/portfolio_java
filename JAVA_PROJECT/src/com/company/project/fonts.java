package com.company.project;

//Java Program to extract the list of Fonts
import java.awt.*;

//Driver class to check available Fonts in AWT
public class fonts {
 public static void main(String[] args)
	 {
	     System.out.println("To Know the available font family names");
	     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	     System.out.println("Getting the font family names");
	
	     // Array of all the fonts available in AWT
	     String fonts[] = ge.getAvailableFontFamilyNames();
	
	     // Getting the font family names
	     for (String i : fonts) {
	         System.out.println(i + " ");
	     }
	 }
}
/*
	Arial 
	Arial Black 
	Arial Narrow 
	Bahnschrift 
	Book Antiqua 
	Bookman Old Style 
	Bookshelf Symbol 7 
	Calibri 
	Calibri Light 
	Cambria 
	Cambria Math 
	Candara 
	Candara Light 
	Cascadia Code 
	Cascadia Mono 
	Century 
	Century Gothic 
	Comic Sans MS 
	Consolas 
	Constantia 
	Corbel 
	Corbel Light 
	Courier New 
	Dialog 
	DialogInput 
	Dubai 
	Dubai Light 
	Dubai Medium 
	Ebrima 
	Franklin Gothic Medium 
	Gabriola 
	Gadugi 
	Garamond 
	Georgia 
	Haan Wing2 
	Haettenschweiler 
	HancomEQN 
	HoloLens MDL2 Assets 
	HyhwpEQ 
	HY견고딕 
	HY견명조 
	HY궁서B 
	HY그래픽M 
	HY목각파임B 
	HY신명조 
	HY얕은샘물M 
	HY엽서L 
	HY엽서M 
	HY중고딕 
	HY헤드라인M 
	Impact 
	Ink Free 
	Javanese Text 
	Leelawadee 
	Leelawadee UI 
	Leelawadee UI Semilight 
	Lucida Console 
	Lucida Sans Unicode 
	Marlett 
	Microsoft Himalaya 
	Microsoft JhengHei 
	Microsoft JhengHei Light 
	Microsoft JhengHei UI 
	Microsoft JhengHei UI Light 
	Microsoft New Tai Lue 
	Microsoft PhagsPa 
	Microsoft Sans Serif 
	Microsoft Tai Le 
	Microsoft Uighur 
	Microsoft YaHei 
	Microsoft YaHei Light 
	Microsoft YaHei UI 
	Microsoft YaHei UI Light 
	Microsoft Yi Baiti 
	MingLiU-ExtB 
	MingLiU_HKSCS-ExtB 
	Mongolian Baiti 
	Monospaced 
	Monotype Corsiva 
	MS Gothic 
	MS Outlook 
	MS PGothic 
	MS Reference Sans Serif 
	MS Reference Specialty 
	MS UI Gothic 
	MT Extra 
	MV Boli 
	Myanmar Text 
	NewJumja 
	Nirmala UI 
	Nirmala UI Semilight 
	NSimSun 
	Palatino Linotype 
	PMingLiU-ExtB 
	Sans Serif Collection 
	SansSerif 
	Segoe Fluent Icons 
	Segoe MDL2 Assets 
	Segoe Print 
	Segoe Script 
	Segoe UI 
	Segoe UI Black 
	Segoe UI Emoji 
	Segoe UI Historic 
	Segoe UI Light 
	Segoe UI Semibold 
	Segoe UI Semilight 
	Segoe UI Symbol 
	Segoe UI Variable 
	Serif 
	SimSun 
	SimSun-ExtB 
	SimSun-ExtG 
	Sitka Text 
	Sylfaen 
	Symbol 
	Tahoma 
	Times New Roman 
	Trebuchet MS 
	Verdana 
	Webdings 
	Wingdings 
	Wingdings 2 
	Wingdings 3 
	Yu Gothic 
	Yu Gothic Light 
	Yu Gothic Medium 
	Yu Gothic UI 
	Yu Gothic UI Light 
	Yu Gothic UI Semibold 
	Yu Gothic UI Semilight 
	굴림 
	굴림체 
	궁서 
	궁서체 
	돋움 
	돋움체 
	맑은 고딕 
	맑은 고딕 Semilight 
	바탕 
	바탕체 
	새굴림 
	한컴 고딕 
	한컴 말랑말랑 Bold 
	한컴 말랑말랑 Regular 
	한컴 훈민정음 가로쓰기 
	한컴 훈민정음 세로쓰기 
	한컴산뜻돋움 
	함초롬돋움 
	함초롬돋움 확장 
	함초롬바탕 
	함초롬바탕 확장 
	함초롬바탕 확장B 
	휴먼둥근헤드라인 
	휴먼매직체 
	휴먼모음T 
	휴먼아미체 
	휴먼엑스포 
	휴먼옛체 
	휴먼편지체 
*/