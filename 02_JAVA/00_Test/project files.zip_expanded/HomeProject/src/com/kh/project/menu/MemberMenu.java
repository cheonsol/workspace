package com.kh.project.menu;

import java.util.Scanner;

import com.kh.project.controller.Controller;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private Controller c = new Controller();

	public void memberMenu() {
		while(true) {
			System.out.println("====== 회원 메뉴 ======");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 정보 수정");
			System.out.println("3. 회원 삭제");
			System.out.println("4. 회원 검색");
			System.out.println("9. 메인 메뉴로");
			
			System.out.print("메뉴 번호 입력 : ");
			int sel = sc.nextInt();
			
			System.out.println();
			
			switch(sel) {
			case 1: insertMember(); break;
			case 2: updateMember(); break;
			case 3: deleteMember(); break;
			case 4: memberSearch(); break;
			case 9:
				return;
			}
		}
	}
	
		public void insertMember() {
			System.out.print("새로운 아이디 입력 : ");
			String userId = sc.next();
			
			System.out.print("사용할 이름 입력 : ");
			String userName = sc.next();
			sc.nextLine();
			
			System.out.print("나이 입력 : ");
			int userAge = sc.nextInt();
			sc.nextLine();
			
			c.insertMember(userId, userName, userAge);
		}
		
		public void updateMember() {
			System.out.println("정보 수정할 아이디 입력 : ");
			String userId = sc.next();
			
			System.out.print("수정할 이름 입력 : ");
			String userName = sc.next();
			sc.nextLine();
			
			System.out.print("수정할 나이 입력 : ");
			int userAge = sc.nextInt();
			sc.nextLine();
			
			c.updateMember(userId, userName, userAge);
		}
		
		public void deleteMember() {
			System.out.print("삭제할 아이디 입력 : ");
			String userId = sc.next();
			sc.nextLine();
			
			c.deleteMember(userId);
		}
		
		public void memberSearch() {
			System.out.print("검색할 회원 이름 : ");
			String keyword = sc.next();
			sc.nextLine();
			
			c.memberSearch(keyword);
		}
	}

