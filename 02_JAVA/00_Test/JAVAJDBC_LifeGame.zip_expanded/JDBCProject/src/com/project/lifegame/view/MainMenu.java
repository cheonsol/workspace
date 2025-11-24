package com.project.lifegame.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.lifegame.controller.AchievementController;
import com.project.lifegame.controller.LifeCharacterController;


public class MainMenu {
	private Scanner sc = new Scanner(System.in);
	private LifeCharacterController lcc;
	private AchievementController ac;
	private String accessId;
	
	public MainMenu(String userId) {
		super();
		accessId = userId;
		lcc = new LifeCharacterController();
		ac = new AchievementController();
		sc = new Scanner(System.in);
	}
	
	public void gameLobby() {
		System.out.println();
		while(true) {
			System.out.println("******** " + accessId + " ********");
			System.out.println("1. 게임 시작");
			System.out.println("2. 클리어한 업적 보기");
			System.out.println("3. 랭킹");
			System.out.println("4. 로그아웃");
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
				newGame();
				break;
			case 2:
				showAchievement();
				break;
			case 3:
				showRank();
				break;
			case 4:
				System.out.println(accessId + "님 안녕히 가십시오.\n");
				return;
			default:
				System.out.println("잘못된 입력입니다.");
			}
			System.out.println();
		}
	}
	
	public void newGame() {
		System.out.println("**** 인생 게임을 시작합니다. ****");
		System.out.print("캐릭터 이름 입력: ");
		String characterName = sc.nextLine().trim();
		lcc.createNewCharacter(characterName, accessId);
	}
	
	public void showAchievement() {
		ac.showAchievedList(accessId);
	}
	
	public void showRank() {
		System.out.println();
		lcc.showRank();
	}
}
