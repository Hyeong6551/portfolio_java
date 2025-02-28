package com.company.project;

import java.util.ArrayList;
import java.util.Scanner;

public class BookController {
	public static void main(String[] args) {
		ArrayList<BookInfo> list = new ArrayList<>();
		ArrayList<MyBookInfo> myList = new ArrayList<>();
		
		BookProcess controller = null;
		BookProcess [] crud = {new BookCreate(), new BookUpdate(), new BookDelete() };
		
		Scanner sc = new Scanner(System.in);

		int num=-1;
		for(;;) {
			System.out.println("1. 추가\n2. 수정\n3. 삭제\n4. 사용자 페이지\n5. 종료");
			num = sc.nextInt();
			if(num==5) {
				System.out.println("프로그램을 종료합니다.");	break;
			}else if(num==4){
				System.out.println("이동");
			}
			else if(num>=1 && num<=3) {
				controller = crud[num-1];	controller.exec(list);
			}
		}
	}
}
