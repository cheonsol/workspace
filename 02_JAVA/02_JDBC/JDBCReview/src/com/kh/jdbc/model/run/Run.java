package com.kh.jdbc.model.run;

import java.util.Scanner;

import com.kh.jdbc.controller.BookController;
import com.kh.jdbc.service.BookServiceImpl;
import com.kh.jdbc.view.BookMenu;

public class Run {

	public static void main(String[] args) {
		BookController bc = new BookController(new BookServiceImpl());
		new BookMenu(new Scanner(System.in), bc).mainMenu();

	}

}
