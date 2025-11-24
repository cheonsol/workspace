package com.limbus.sin.run;

import com.limbus.sin.view.SinMenu;

public class Run {
	public static void main(String[] args) {
		boolean con = true;
		while(con) {
			con = new SinMenu().mainMenu();
		}
		
		System.out.println("프로그램이 종료되었습니다. 엔터를 눌러서 종료.");
	    new java.util.Scanner(System.in).nextLine();
	    System.exit(1);
	}
}
