package com.kh.review.oop4;

import java.util.Scanner;

public class ShapeMenu {
	private Scanner sc = new Scanner(System.in);
	private SquareController scr = new SquareController();
	private TriangleController tc = new TriangleController();
	
	public void inputMenu() {
		while(true) {
			System.out.println("==== 도형 프로그램 ====");
			System.out.println("3. 삼각형");
			System.out.println("4. 사각형");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴 번호 : ");
			
			int sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
			case 3:
				triangleMenu();
				break;
			case 4:
				squareMenu();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
			}
		}
	}
	
	public void triangleMenu() {
		while(true) {
			System.out.println("==== 삼각형 ====");
			System.out.println("1. 삼각형 면적");
			System.out.println("2. 삼각형 색칠");
			System.out.println("3. 삼각형 정보");
			System.out.println("9. 메인으로");
			System.out.print("메뉴 번호 : ");
			
			int sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
			case 1:
			case 2:
				inputSize(2, sel); break;
			case 3:
				printInformation(2); break;
			case 9:
				return;
			default:
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
			}
		}
	}
	
	public void squareMenu() {
		while(true) {
			System.out.println("==== 사각형 ====");
			System.out.println("1. 사각형 둘레");
			System.out.println("2. 사각형 면적");
			System.out.println("3. 삼각형 색칠");
			System.out.println("4. 삼각형 정보");
			System.out.println("9. 메인으로");
			System.out.print("메뉴 번호 : ");
			
			int sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
			case 1:
			case 2:
			case 3:
				inputSize(2, sel); break;
			case 4:
				printInformation(1); break;
			case 9:
				return;
			default:
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
			}
		}
	}
	
	// type -> 2 : 삼각형 / 1 : 사각형
	// 삼각형 menuNum -> 1 : 면적, 2 : 색상
	// 사각형 menuNum -> 1 : 둘레, 2 : 면적, 3 : 색상
	 public void inputSize(int type, int menuNum) {
		 if(type == 2) {
			 switch(menuNum) {
			 	case 1: menuNum = 2; break;
			 	case 2: menuNum = 3; break;
			 }
		 } 
		 
		 
	     // 색상먼저 처리
	     if (menuNum == 3) {
	         System.out.print("색깔을 입력하세요 : ");
	         String color = sc.nextLine().trim();
	         if (type == 2) {
	             tc.paintColor(color);
	         } else { // type == 4
	             scr.paintColor(color);
	         }
	         return;
	     }
	
	     // 이 아래는 숫자(높이/너비)가 필요한 경우만
	     System.out.print("높이 : ");
	     double height = sc.nextDouble();
	     sc.nextLine(); // 개행 제거
	     System.out.print("너비 : ");
	     double width = sc.nextDouble();
	     sc.nextLine(); // 개행 제거
	
	     if (menuNum == 1) { // 둘레 (사각형 전용)
	         // 명세상 둘레는 사각형 메뉴에서만 호출됨
	         double perimeter = scr.calcPerimeter(height, width);
	         System.out.println("사각형의 둘레 : " + perimeter);
	         return;
	     }
	
	     // menuNum == 2 : 면적
	     if (type == 2) {
	         double area = tc.calcArea(height, width);   // (w*h/2) 이어야 함
	         System.out.println("삼각형의 넓이 : " + area);
	     } else { // type == 1
	         double area = scr.calcArea(height, width);  // (w*h)
	         System.out.println("사각형의 넓이 : " + area);
	     }
	 }
	 
	 public void printInformation(int type) {
		 if(type == 1) {
			 System.out.println(scr.print());
		 } else {
			 System.out.println(tc.print());
		 }
	 }
}
