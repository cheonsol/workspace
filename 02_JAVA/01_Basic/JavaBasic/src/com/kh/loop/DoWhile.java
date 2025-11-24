package com.kh.loop;

import java.util.Scanner;

public class DoWhile {
	/*
	 * do-while문
	 * 
	 * do {
	 * 		실행할 코드
	 * } while(조건식);
	 * 
	 * 처음에 무조건 실행코드 한번 실행되고 조건문을 검사
	 * 
	 * *기존의 for/while은 처음 수행될 때 조건검사 후 true일 경우에만 반복코드를 실행함.
	 * 하지만 do-while은 첫 실행은 조건검사를 하지않고 무조건 실행.
	 * 
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		int num;
		
		do {
			System.out.println("서비스 번호를 입력하세요.");
			System.out.println("1. 추가");
			System.out.println("2. 삭제");
			System.out.println("3. 종료");
			
			System.out.print("입력 : ");
			num = sc.nextInt();
			
			switch(num) {
			case 1:
				//추가하는 로직
				System.out.println("추가되었습니다.");
				break;
			case 2:
				//삭제하는 로직
				System.out.println("삭제되었습니다.");
				break;
			case 3:
				System.out.println("종료합니다.");
				break;
			default:
				System.out.println("잘못입력하셨습니다.");
			}
			
		} while(num != 3);
		*/
		
		//사용자가 입력한 수를 계속해서 더하는 프로그램 작성
		//단, 사용자가 0을 입력하면 종료
		
		int sum = 0;
		int num;
		
		do {
			num = sc.nextInt();
			sum+= num;
		} while(num != 0);
		
		System.out.println("sum : " + sum);
	}

}
