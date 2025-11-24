package com.project.lifegame.view;


import java.util.List;

public class DisplayMsg {
	public static void displaySuccess(String msg) {
		System.out.println("\n서비스 요청 성공 : " + msg);
	}
	
	public static void displayFail(String msg) {
		System.out.println("\n서비스 요청 실패 : " + msg);
	}
	
	public static void displayNoData(String msg) {
		System.out.println(msg);
	}
	
	public static void displayRank(List list, String title) {
		System.out.println("======== " + title + " ========");
	    System.out.printf("%-4s %-12s %-15s %10s%n", " 순위", "유저 아이디", "캐릭터 이름", "최종 재산");
	    
	    int rank = 1;
	    for(Object o : list) {
	        System.out.printf("%3d위  %s%n", rank++, o);
	    }
	}
	
	public static void displayAchievement(List list, String title) {
		System.out.println("======== " + title + " ========");
		for(Object o : list) {
			System.out.println(o);
		}
	}
}
