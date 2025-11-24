package com.kh.review.interface1;

import java.util.Scanner;

public class PhoneController {
	private String[] result = new String[5];
	
	public String[] method() {
		Scanner sc = new Scanner(System.in);
		Phone[] phoneArr = new Phone[5];
		
		for(int i=0; i<5; i++) {
			System.out.print("어떤 휴대폰을 생성하겠스니까?(1.GalaxyNote9 2.V40) :  ");
			int sel = sc.nextInt();
			phoneArr[i] = sel == 1 ? new GalaxyNote9() : new V40();
		}
	
		int count = 0;
		for(Phone phone : phoneArr) {
			if(phone instanceof SmartPhone) {
				result[count++] = ((SmartPhone)phone).printInformation();
			}
		}
		
		return result;
	}
}
