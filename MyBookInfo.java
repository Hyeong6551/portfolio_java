package com.company.project;

import java.util.Calendar;

class MyBookInfo{
	public static int cnt=0;
	private int mno;
	private String name;
	private int bookNo;
	private String title;
	private String date;
	
	public MyBookInfo() { super(); }
	public MyBookInfo(int mno, String name, int bookNo, String title, String date) {
		super();
		this.mno = mno;
		this.name = name;
		this.bookNo = bookNo;
		this.title = title;
		this.date = date;
	}
	
	public MyBookInfo(String name, BookInfo book) {
		super();
		this.name = name;
		this.bookNo = book.getNo();
		this.title = book.getTitle();
		Calendar today = Calendar.getInstance();
		this.date = today.get(1) + "-" + today.get(2) + "-" + today.get(5);
	}

	public int getMno() { return mno; } public void setMno(int mno) { this.mno = mno; }
	public String getName() { return name; } public void setName(String name) { this.name = name; }
	public String getDate() { return date; } public void setDate(String date) { this.date = date; }
	public int getBookNo() { return bookNo; }  public void setBookNo(int bookNo) { this.bookNo = bookNo; }
	public String getTitle() { return title; } public void setTitle(String title) { this.title = title; }
	
	@Override
	public String toString() {
		return "MyBookInfo [mno=" + mno + ", name=" + name + ", bookNo=" + bookNo + ", title=" + title + ", date="
				+ date + "]";
	}
}
