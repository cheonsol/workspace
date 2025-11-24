package com.kh.practice.controll;

import java.util.Scanner;

public class Practice2 {
	/*
	 * 1부터 6까지 눈이 있는 세 개의 주사위를 던졌을 때, 아래 규칙에 따라 상금을 계산하는 프로그램을 작성하세요.
	 * ### 상금 계산 규칙

		| 경우 | 구체 조건 | 상금 계산식 |
		| --- | --- | --- |
		| 1 | 같은 눈이 *세 개 모두 같을 때* | 10,000원 + (같은 눈) × 1,000원 |
		| 2 | 같은 눈이 *두 개만 같을 때* | 1,000원 + (같은 눈) × 100원 |
		| 3 | 모두 다른 눈일 때 | (최댓값) × 100원 |
		
		예:
		- 예시 입력 `3 3 6` → 두 개만 같음 → `1,000 + 3×100 = 1,300원`
		- 예시 입력 `2 2 2` → 모두 같음 → `10,000 + 2×1,000 = 12,000원`
		- 예시 입력 `6 2 5` → 모두 다름 → `6×100 = 600원`
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n1 = sc.nextInt();
		int n2 = sc.nextInt();
		int n3 = sc.nextInt();
		int reward = 0;
		
		//3개가 같을 때
		//2개만 같을 때(a = b, a = c, b = c)
		if(n1 == n2 && n1 == n3) {
			reward = 10000 + n1 * 1000;
		} else if(n1 == n2 || n1 == n3) {
			reward = 1000 + n1 * 100;
		} else if(n2 == n3) {
			reward = 1000 + n2 * 100;
		} else {
			/*
				int max = n1 > n2 ? n1 : n2;
				max = max > n3 ? max : n3;
			*/
			//java에서 제공해주는 수학함수
			//java.lang.Math -> 코드를 작성할 때 기본적으로 유용한 것들을 담아서 제공해줌.
			//Math.max(n1, n2) -> 둘중 큰값을 반환함.
			int max = Math.max(n1, n2);
			max = Math.max(max, n3);
			reward = max * 100;
		}
		
		System.out.println(reward);
		sc.close();

	}

}
