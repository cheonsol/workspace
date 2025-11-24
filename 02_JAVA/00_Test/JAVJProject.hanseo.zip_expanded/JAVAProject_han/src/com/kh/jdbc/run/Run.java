package com.kh.jdbc.run;

import java.util.Scanner;

import com.kh.jdbc.controller.DeviceController;
import com.kh.jdbc.service.DeviceServiceImpl;
import com.kh.jdbc.view.MainMenu;

public class Run {

	public static void main(String[] args) {
		DeviceController dc = new DeviceController(new DeviceServiceImpl());
		new MainMenu(new Scanner(System.in), dc).mainMenu();

	}

}
