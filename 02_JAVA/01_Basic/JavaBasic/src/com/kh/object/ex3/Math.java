package com.kh.object.ex3;

public class Math {

	int adder(int n1) {
		return n1 + n1;
	}
	
	int adder(int n1, int n2) {
		return n1 + n2;
	}
	
	double adder(double n1, double n2) {
		return n1 + n2;
	}
	
	String adder(int n1, String ch) {
		String addResult = n1 + ch;
		return addResult;
	}
	
	String adder(String ch, int n1) {
		String addResult = ch + n1;
		return addResult;
	}
}
