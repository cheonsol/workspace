package com.kh.project.menu;

import java.util.List;
import java.util.Scanner;

import com.kh.project.controller.Controller;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	private Controller c = new Controller();
	private MovieMenu mm = new MovieMenu();
	private MemberMenu um = new MemberMenu();
	
		public void mainMenu() {
			System.out.println("※※※※※※※ 영화 평가하기 ※※※※※※※");
			System.out.println();
		while(true) {
		System.out.println("====== 메인메뉴 ======");
		System.out.println("1. 회원 메뉴");
		System.out.println("2. 영화 메뉴");
		System.out.println("3. 영화 평가 쓰기");
		System.out.println("4. 평가 검색");
		System.out.println("5. 전체 평가 조회");
		System.out.println("6. 평가 수정");
		System.out.println("7. 평가 삭제");
		System.out.println("9. 종료");
		System.out.print("번호 입력 : ");
		
		int sel =sc.nextInt();
		sc.nextLine();
		
		System.out.println();
		
		switch(sel) {
		
		case 1: um.memberMenu(); break;
		case 2: mm.movieMenu(); break;
		case 3: insertRate(); break;
		case 4: rateSearch(); break;
		case 5: c.selectRateAll(); break;
		case 6: updateRate(); break;
		case 7: deleteRate(); break;
		case 9:
			System.out.println("프로그램 종료");
			return;
		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
			}
		}
	}

	public void insertRate() {
		System.out.print("아이디 입력 : ");
		String userId = sc.next();
		
		System.out.print("영화 입력 : ");
		String movieName = sc.next();
		
		System.out.print("영화 점수 입력(1~5 숫자 입력) : ");
		int movieScore = sc.nextInt();
		sc.nextLine();
		
		System.out.print("영화에 대한 평가입력 : ");
		String movieComment = sc.nextLine();
		
		c.insertRate(userId, movieName, movieScore, movieComment);
	}
	
	public void updateRate() {
		System.out.print("수정할 평가의 아이디 : ");
		String userId = sc.next();
		
		System.out.print("수정할 평가의 영화명 : ");
		String movieName = sc.next();
		
		System.out.print("수정할 평가 점수(1~5 사이 숫자 입력) : ");
		int movieScore = sc.nextInt();
		
		System.out.print("평가 코멘트 수정 : ");
		String movieComment = sc.nextLine();
		
		c.updateRate(userId, movieName, movieScore, movieComment);
	}
	
	public void deleteRate() {
		System.out.print("삭제할 평가의 아이디 : ");
		String userId = sc.next();
		
		System.out.print("삭제할 평가의 영화명 : ");
		String movieName = sc.next();
		sc.nextLine();
		
		c.deleteRate(userId, movieName);
	}
	
	public void rateSearch() {
		System.out.print("검색할 영화 이름 : ");
		String keyword = sc.next();
		
		c.RateSearch(keyword);
	}
	
	//========================= 응답화면 ===================================
		//서비스요청 처리 후 성공했을 때 사용자가 보게될 화면
		//msg : 기능별 성공메세지
		public void displaySuccess(String msg) {
			System.out.println("\n서비스 요청 성공 : " + msg);
		}
		
		//서비스요청 처리 후 실패했을 때 사용자가 보게될 화면
		//msg : 기능별 실패메세지
		public void displayFail(String msg) {
			System.out.println("\n서비스 요청 실패 : " + msg);
		}
		
		public void displayNoData(String msg) {
			System.out.println("\n" + msg);
		}
		
		public void displayList(List<?> list, String title) {
			System.out.println("========== " + title + " ==========");
			for(Object o : list) {
				System.out.println(o);
			}
		}
}
