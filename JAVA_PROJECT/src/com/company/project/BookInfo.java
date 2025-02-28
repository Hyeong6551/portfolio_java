package com.company.project;

public class BookInfo {
	public static int cnt=0;
	private int no;
	private String title;
	private String author;
	private String publisher;
	private boolean bookState;
	
	public int getNo() { return no; }
	public void setNo(int no) { this.no = no; }
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getAuthor() { return author; }
	public void setAuthor(String author) { this.author = author; }
	public String getPublisher() { return publisher; }
	public void setPublisher(String publisher) { this.publisher = publisher; }
	public boolean isBookState() { return bookState; } 
	public void setBookState(boolean bookState) { this.bookState = bookState; }
	
	public BookInfo() { super(); }
	public BookInfo(int no) { super(); this.no = no; }
	public BookInfo(int no, String title, String author, String publisher, boolean bookState) {
		super();
		this.no = no;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.bookState = bookState;
	}
	
	public BookInfo(String title, String author, String publisher, boolean bookState) {
		super();
//		this.no = ++cnt;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.bookState = bookState;
	}
	
	@Override
	public String toString() {
		return "BookInfo [no=" + no + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", bookState=" + bookState + "]\n";
	}
	


}
