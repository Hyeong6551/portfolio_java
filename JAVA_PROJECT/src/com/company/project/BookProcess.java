package com.company.project;

import java.util.ArrayList;

public interface BookProcess {
	public void exec( ArrayList<BookInfo> books );
	public void exec( ArrayList<BookInfo> books, ArrayList<MyBookInfo> myBooks, View_Admin_crud ad_crud, View_User_crud usr_crud );
	public void exec( View_Admin_crud ad_crud, View_User_crud usr_crud );
}
