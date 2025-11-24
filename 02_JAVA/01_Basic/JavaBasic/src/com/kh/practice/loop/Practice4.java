package com.kh.practice.loop;

public class Practice4 {

	public static void main(String[] args) {
		for(int i=1; i <= 100; i++) {
			
			//7의 배수가 아닐 때 건너뜀
			if(i % 7 != 0) {
				continue;
			}
			
			//짝수
			if(i % 2 == 0) {
				continue;
			}
			
			System.out.print(i + " ");
		}

	}

}
