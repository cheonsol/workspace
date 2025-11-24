package com.kh.example.oop4;

import java.util.Scanner;

public class ShapeMenu {
    private Scanner sc = new Scanner(System.in);
    private SquareController scr  = new SquareController();
    private TriangleController tc = new TriangleController();

    public void inputMenu() {
        while (true) {
            System.out.println("===== 도형 프로그램 =====");
            System.out.println("3. 삼각형");
            System.out.println("4. 사각형");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴번호 : ");

            int select = sc.nextInt();
            sc.nextLine(); // 개행 제거

            switch (select) {
                case 3:
                    triangleMenu();
                    break;
                case 4:
                    squareMenu();
                    break;
                case 9:
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("잘못된 번호입니다. 다시 입력하세요.");
            }
        }
    }

    public void triangleMenu() {
        while (true) {
            System.out.println();
            System.out.println("===== 삼각형 =====");
            System.out.println("1. 삼각형 면적");
            System.out.println("2. 삼각형 색칠");
            System.out.println("3. 삼각형 정보");
            System.out.println("9. 메인으로");
            System.out.print("메뉴번호 : ");

            int select = sc.nextInt();
            sc.nextLine(); // 개행 제거

            switch (select) {
                case 1: // 면적
                    inputSize(3, 2);
                    break;
                case 2: // 색칠
                    inputSize(3, 3);
                    break;
                case 3: // 정보
                    printInformation(3);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘못된 번호입니다. 다시 입력하세요.");
            }
        }
    }

    public void squareMenu() {
        while (true) {
            System.out.println("===== 사각형 =====");
            System.out.println("1. 사각형 둘레");
            System.out.println("2. 사각형 면적");
            System.out.println("3. 사각형 색칠");
            System.out.println("4. 사각형 정보");
            System.out.println("9. 메인으로");
            System.out.print("메뉴번호 : ");

            int select = sc.nextInt();
            sc.nextLine(); // 개행 제거

            switch (select) {
                case 1: // 둘레
                    inputSize(4, 1);
                    break;
                case 2: // 면적
                    inputSize(4, 2);
                    break;
                case 3: // 색칠
                    inputSize(4, 3);
                    break;
                case 4: // 정보
                    printInformation(4);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘못된 번호입니다. 다시 입력하세요.");
            }
        }
    }

	 // type -> 3 : 삼각형 / 4 : 사각형
	 // menuNum -> 1 : 둘레, 2 : 면적, 3 : 색상
	 public void inputSize(int type, int menuNum) {
	     // 색상먼저 처리
	     if (menuNum == 3) {
	         System.out.print("색깔을 입력하세요 : ");
	         String color = sc.nextLine().trim();
	         if (type == 3) {
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
	     if (type == 3) {
	         double area = tc.calcArea(height, width);   // (w*h/2) 이어야 함
	         System.out.println("삼각형의 넓이 : " + area);
	     } else { // type == 4
	         double area = scr.calcArea(height, width);  // (w*h)
	         System.out.println("사각형의 넓이 : " + area);
	     }
	 }

    public void printInformation(int type) {
        System.out.println(type == 3 ? tc.print() : scr.print());
    }
}
