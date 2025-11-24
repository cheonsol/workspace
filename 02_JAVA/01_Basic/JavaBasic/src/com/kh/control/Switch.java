package com.kh.control;

import java.util.Scanner;

public class Switch {
	/*
	 * switch문도 if문과 동일하게 조건문
	 * 
	 * 다만, switch문은 값이 정확하게 일치(동등비교)하는 경우에만 사용 됨
	 * 
	 * [표현식]
	 * switch(비교대상(정수, 문자, 문자열)){
	 * case 값1 : 실행코드;
	 * case 값2 : 실행코드;
	 * case 값3 : 실행코드;
	 * ...
	 * default : 위의 값이 전부 일치하지 않았을 때 실행할 코드;
	 * 
	 * break -> switch문 내에서 사용하면 해당코드가 실행됨고 동시에 가장 가까운 switch문을 종료
	 * }
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/*
		 * 정수를 입력받아서
		 * 1일경우와
		 * 5일경우 빨간색입니다.
		 * 2일경우 파란색입니다.
		 * 3일경우 초록색입니다.
		 * 다른경우 모두 잘못입력했습니다
		 * 를 출력
		 */
		
		/*
		int num;
		System.out.print("정수 입력 : ");
		num = sc.nextInt();
		
		switch(num) {
		case 1:
		case 5:
			System.out.println("빨간색입니다.");
			break;
		case 2:
			System.out.println("파란색입니다.");
			break;
		case 3:
			System.out.println("초록색입니다.");
			break;
		default:
			System.out.println("잘못 입력하셨습니다.");
		}
		*/
		
		/*
		 * 과일을 구매하는 프로그램을 작성하자.
		 * 구매하고자하는 과일을 입력하면
		 * 그에맞는 가격이 출력되는 프로그램
		 * 
		 * [출력]
		 * 구매하고자하는 과일(사과(2000), 바나나(3000), 딸기(5000)) : xxx
		 * xxx의 가격은 xxx입니다. 출력 / 잘못입력하셨습니다.
		 * */
		
		/*
		String fruit;
		int price = 0;
		System.out.print("구매하고자하는 과일(사과(2000), 바나나(3000), 딸기(5000)) :");
		fruit = sc.next();
		
		switch(fruit) {
		case "사과":
			price = 2000;
			break;
		case "키위":
			price = 4000;
			break;
		case "바나나":
			price = 3000;
			break;
		case "딸기":
		case "자두":
			price = 5000;
			break;
		}
		
		if(price == 0) {
			System.out.println("잘못입력하셨습니다.");
		} else {
			System.out.println(fruit + "의 가격은 " + price + "원입니다.");
		}
		*/
		
		/*
		 * 월을 입력받아서 해당월에 마지막일이 며칠인지를 출력하는 프로그램 작성
		 * 
		 * [출력]
		 * 월을 입력하세요 : xx
		 * xx월은 xx일까지 있습니다.
		 * 
		 * 1,3,5,7,8,10,12 -> 31일
		 * 2 -> 28일
		 * 4,6,9,11 -> 30일
		 * */
		
		
		int month, day = 0;
		
		System.out.print("월을 입력하세요 : ");
		month = sc.nextInt();
		
		switch(month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 10:
		case 12:
			day = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;
		case 2:
			day = 28;
			break;
		default:
		}
		
		System.out.printf("%d월은 %d일까지 있습니다.", month, day);
	}

}
