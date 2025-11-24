package com.kh.example.abstractNInterface;

public class Run {
	public static void main(String[] args) {
		PhoneController pc = new PhoneController();
		String[] infoArr = pc.method();
		for(String s : infoArr) {
			System.out.println(s);
			System.out.println();
		}
	}
}
