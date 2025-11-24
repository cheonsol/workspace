package com.kh.example.abstractNInterface;

public class PhoneController {
	private String[] result = new String[2];
	
	public String[] method() {
		Phone[] phoneArr = new Phone[2];
		phoneArr[0] = new GalaxyNote9();
		phoneArr[1] = new V40();
		
		String[] result = new String[2];
		int index = 0;
		for(Phone p : phoneArr) {
			if(p instanceof SmartPhone) {
				result[index++] = ((SmartPhone)p).printInformation();
			}
		}
		
		return result;
	}
}
