package com.kh.project.menu;

import java.util.List;
import java.util.Scanner;

import com.kh.project.controller.Controller;

public class MovieMenu {
	private Scanner sc = new Scanner(System.in);
	private Controller c = new Controller();

	public void movieMenu() {
		while(true) {
			System.out.println("====== 영화 메뉴 ======");
			System.out.println("1. 영화 추가");
			System.out.println("2. 영화 정보 수정");
			System.out.println("3. 영화 삭제");
			System.out.println("4. 영화 검색");
			System.out.println("5. 영화 전체 조회");
			System.out.println("9. 메인 메뉴로");
			
			System.out.print("메뉴 번호 입력 : ");
			int sel = sc.nextInt();
			
			System.out.println();
			
			switch(sel) {
			case 1: insertMovie(); break;
			case 2: updateMovie(); break;
			case 3: deleteMovie(); break;
			case 4: searchMovie(); break;
			case 5: c.selectMovieList(); break;
			case 9:
				return;
			}
		}
	}
	
		public void insertMovie() {
			System.out.print("새로운 영화이름 입력 : ");
			String movieName = sc.next();
			
			System.out.print("영화 장르 입력 : ");
			String movieGenre = sc.next();
			
			System.out.print("영화 연령등급 입력 : ");
			int movieAge = sc.nextInt();
			sc.nextLine();
			
			c.insertMovie(movieName, movieGenre, movieAge);
		}
		
		public void updateMovie() {
			System.out.print("수정할 영화이름 : ");
			String movieName = sc.next();
			
			System.out.print("영화 장르 수정 : ");
			String movieGenre = sc.next();
			
			System.out.print("영화 연령등급 수정 : ");
			int movieAge = sc.nextInt();
			sc.nextLine();
			
			c.updateMovie(movieName, movieGenre, movieAge);
		}
		
		public void deleteMovie() {
			System.out.print("삭제할 영화 이름 입력 : ");
			String movieName = sc.next();
			sc.nextLine();
			
			c.deleteMovie(movieName);
		}
		
		public void searchMovie() {
			System.out.print("검색할 영화 이름 : ");
			String keyword = sc.next();
			sc.nextLine();
			
			c.movieSearch(keyword);
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

