package com.project.lifegame.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.lifegame.controller.UserController;

public class LobbyMenu {
	private Scanner sc;
	private UserController uc ;
	
	public LobbyMenu() {
		super();
		sc = new Scanner(System.in);
		uc = new UserController();
	}
	public void lobbyMenu() {
		System.out.println();
		while(true) {
			System.out.println("********** 인생 게임 **********");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("0. 프로그램 종료");
			boolean valid = false;
			int sel=-1;
			while (!valid) {
			    System.out.print("메뉴 입력: ");
			    try {
			    	sel = sc.nextInt();
			        valid = true; 
			    } catch (InputMismatchException e) {
			        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			        sc.nextLine(); 
			    }
			}
			sc.nextLine();
			System.out.println();
			switch(sel) {
				case 1: 
					loginMenu();
					break;
				case 2: 
					signUpMenu();
					break;
				case 0: 
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					System.out.println("다시 입력하세요.");
			}
			System.out.println();
		}
	}
	public void loginMenu() {
		System.out.println("******** 로그인 ********");
		System.out.print("ID : ");
		String userId = sc.nextLine().trim();
		System.out.print("Password : ");
		String userPw = sc.nextLine().trim();
		uc.loginUser(userId, userPw);
		
	}
	public void signUpMenu() {
		System.out.println("******** 회원가입 ********");
		System.out.print("ID : ");
		String userId = sc.nextLine().trim();
		System.out.print("Password : ");
		String userPw = sc.nextLine().trim();
		uc.signUpUser(userId, userPw);
	}
	
	
}
