package com.kh.api;

import java.util.StringTokenizer;

public class StringAPI {
	public void method01() {
		//1.생성자를 통한 문자열 생성
		String str1 = new String("hello");
		String str2 = new String("hello");
		
		System.out.println(str1);
		System.out.println(str2.toString());
		//String클래스에 toString메서드는 이미 오버라이딩 되어있음
		//그래서 참조변수 출력시 Object의 toString형식(타입@주소값16진수)이 아니라 String의 저장된 문자열이 반환됨.
	
		System.out.println(str1 == str2);
		//String객체의 주소값을 비교하기 때문에 false가 나옴
		
		System.out.println(str1.equals(str2));
		//String클래스에서 equals메서드도 이미 오버라이딩 되어있음(주소값비교가 아니라 문자열 값을 비교하도록)
		
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		//String클래스에서 hashCode메서드도 이미 오버라딩 되어있음(주소값이 아닌 문자열을 가지고 해시값을 만들도록)
		
		//System.identityHashCode(참조변수)
		//해당 참조변수의 메모리값을 기반으로한 해시값을 반환
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		
		
		System.out.println("-------------------------------------------------------------");
		
		//2. 문자열을 리터럴값으로 생성
		String str3 = "hello";
		String str4 = "hello";
		
		System.out.println(str3 == str4);
		//문자열 리터럴 사용시 상수풀영역에 문자열을 생성한다.
		//String pool : 동일한 문자열을 중복해서 가지지 않는다.
		
		System.out.println(str3.hashCode());
		System.out.println(str4.hashCode());
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		System.out.println(System.identityHashCode(str3));
		System.out.println(System.identityHashCode(str4));
		
		str3 = "bye";
		System.out.println(System.identityHashCode(str3));
		//String 리터럴의 특성상 참조변수에 값을 변경하는 순간 기존의 문자열 메모리에 값을 변경하는게 아니라
		//상수이기 떄문에 새로운 값을 String pool에 만들고 참조하도록 되어있음.
	}
	
	public void method02() {
		String str1 = "Hello World";
		
		//문자열.charAt(int index) : char
		//문자열에서 전달받은 index위치에 문자를 추출
		char ch = str1.charAt(6);
		System.out.println("ch : " + ch);
		
		//문자열.contains(찾고자하는 문자열) : boolean
		//문자열에 전달된 문자열이 포함되어 있는지 확인하여 결과를 반환
		System.out.println("str1에 ello라는 문자열이 포함 ? : " + str1.contains("ello"));
		System.out.println("str1에 bye라는 문자열이 포함 ? : " + str1.contains("bye"));
		
		//문자열.concat(String str) : String
		//문자열과 전달된 또다른 문자열을 하나로 합처서 새로운 문자열을 리턴
		String str2 = str1.concat("!!!");
		System.out.println("str2 : " + str2);
		System.out.println("str2 : " + str1 + "!!!");
		System.out.println("str2 : " + new StringBuilder(str1).append("!!!").toString());
		
		//문자열.substring(시작위치, [끝위치])
		//문자열을 시작위치부터 끝위치 -1까지 추출해서 반환
		System.out.println(str1.substring(6));
		System.out.println(str1.substring(6, 9));
		
		//문자열.replace(대상문자, 변경할 문자) : String
		//문자열에서 대상문자를 찾아서 변경할 문자로 대체한 후 변환된 문자열을 반환
		String str4 = str1.replace('l', 'c');
		System.out.println("str1 : " + str1);
		System.out.println("str4 : " + str4);
		
		System.out.println("모든 문자열 대문자로 변환 : " + str1.toUpperCase());
		System.out.println("모든 문자열 소문자로 변환 : " + str1.toLowerCase());
		
		//문자열.trim();
		//문자열의 앞뒤공백을 제거시킨 새문자열을 반환
		String str5 = "     JA    VA     ";
		System.out.println(str5.trim());
		
		//문자열.toCharArray()
		//문자열을 문자배열로 반환
		char[] arr = str1.toCharArray();
		for(char c : arr) {
			System.out.print(c + " ");
		}
	}
	
	public void method03() {
		String str = "Java,Oracle,sql,html,css,js,spring";
		
		//구분자를 기준으로 문자열을 분리시키는 방법
		//방법1. 분리된 문자열들을 String[]에 차곡차곡 담아야 할 때
		//String에서 제공하는 split메서드를 사용
		//문자열.split(구분자) : String[]
		String[] arr = str.split(",");
		for(String st : arr) {
			System.err.print(st + " ");
		}
		
		System.out.println();
		//분리된 문자열 배열을 다시 String으로 연결하는 방법
		//String.join(구분자, 배열);
		String str2 = String.join(",",arr);
		System.out.println(str2);
		
		//방법2 분리된 문자열들을 각각 토큰으로써 관리가능
		//문자열 토큰화 : StringTokenizer
		StringTokenizer stn = new StringTokenizer(str, ",");
		
		System.out.println(stn.countTokens());//토큰의 남은갯수
		
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		//System.out.println(stn.nextToken()); 더이상 토큰이 남아있지 않을 떄 다음토큰 호출시 예외발생
		
		stn = new StringTokenizer(str, ",");
		int size = stn.countTokens();
		for(int i=0; i<size; i++) {
			System.out.println(stn.nextToken());
		}
		
		stn = new StringTokenizer(str, ",");
		while(stn.hasMoreElements()) { //남은 토근이 있냐?
			System.out.println(stn.nextToken());
		}
	}
}


















