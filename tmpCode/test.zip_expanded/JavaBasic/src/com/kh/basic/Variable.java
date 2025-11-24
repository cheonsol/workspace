package com.kh.basic;

public class Variable {

	/*
	 * 변수 : 값을 기록하고 사용하기위한 메모리공간
	 * 
	 * [표현식]
	 * 자료형 변수이름;
	 * 
	 * 자료형 : 변수가 사용할 메모리크기에 따라서 지정
	 * 변수이름 : 변수를 식별하기위해 붙여주는 이름
	 * */
	public static void main(String[] args) {
		//정수형 변수 num을 선언
		int num;
		
		//정수형 변수 num에 10이라는 값을 대입해라
		num = 10;
		System.out.println(num);
		
		//참과거짓을 표현하는 자료형으로 isTrue라는 이름의 변수를 선언
		boolean isTrue = false; //선언과 동시에 false라는 값을 대입
		isTrue = true;
		System.out.println(isTrue);
		
		//실수형(소수점6자리까지 사용) 자료형으로 num2라는 이름의 변수를 선언
		float num2;
		num2 = 3.14123546431f;
		System.out.printf("%.6f",num2);
		
		//-----------------------------------------------------------
		// 원시타입 : 가장 기본적인 데이터타입, 값 자체를 저장하며 추가적인 속성이 없다.
		
		/*
		 * 정수 자료형
		 * 
		 * byte(1) short(2) int(4) long(8)
		 * 모두 정수를 저장하는 자료형, 각 자료형마다 저장할 수 있는 수의 범위가 다름.
		 * 
		 * 정수 리터럴의 기본값은 int
		 * byte, short은 범위내에 있을 경우 자동 형변환된다.
		 */
		int in = 10;
		byte by = 10;
		short sh = 10;
		long lo = 10;

		System.out.println("정수 자료형");
		System.out.printf("%d %d %d %d \n",by,sh,in,lo);
		
		/*
		 * 실수 자료형
		 * float(4) double(8)
		 * 실수를 저장하는 자료형, float보다 double 더 정밀하게 표현할 수 있음.
		 * */
		float fl = 4.24f;
		double dou = 4.24;
		System.out.println("실수 자료형");
		System.out.println(fl + ", " + dou);
		
		/*
		 * 문자 자료형
		 * char(2)
		 * 문자 하나를 담을 수 있는 자료형 ''
		 * 자바는 2바이트 유니코드를 사용함.
		 * */
		
		char ch1 = '최'; //2바이트 유니코드 정수로 저장됨
		char ch2 = '지';
		char ch3 = '원';
		System.out.println("" + ch1 + ch2 + ch3); //기본적으로 char은 연산시 int로 형변환이 일어난다.
		System.out.println(ch1);
		
		/*
		 * 논리 자료형
		 * boolean(1)
		 * true와 false를 저장하는 자료형
		 * */
		
		boolean b1 = 10 > 5; // true
		boolean b2 = 20 == 10; // false
		
		System.out.println("논리 자료형");
		System.out.println(b1);
		System.out.println(b2);
		
		/*
		 * 문자열
		 * String
		 * 문자열 같은 경우 할당할 메모리크기가 가변적이므로 원시타입이 아닌 객체타입으로 변수를 만들어 사용함.
		 * 
		 * 원래 객체를 만드는 방식
		 * String 변수명 = new 클레스명(초기값);
		 * 
		 * 문자열은 특별한 객체로 원시타입과 동일하게 생성하고 사용할 수 있다.
		 * */
		
		String str1 = "안녕하세요. 김아무개입니다.";
		System.out.println(str1);
		
		
		String str2;
		str2 = "누구?";
		str2 = "저는 누구가 아니라 최지원입니다.";
		str2 = null; //String의 변수는 참조변수이기 때문에 기본값이 null이고 빈값을 표현하기위해 null을 대입할 수 있다.
		
		String str3;
		str3 = "김수민";
		str3 = null;
		System.out.println(str3);
		
		/*
		 * 이스케이프 시퀀스
		 * 문자열내에서 탭, 백슬러시, 작따옴표등을 사용하기위한 방식
		 * */
		
		// \t : 탭(Tab)
		// 가로로 일정 간격을 띄움
		System.out.println("이름\t나이\t주소");
		
		// \\ : 백슬래시(\)
		//	문자 자체로 출력
		System.out.println("경로 : C:\\devtool\\jdk-17.0.2");
		
		// \' : 작은따옴표    \" : 쌍따옴표
		//	문자형에서 '을 그대로 쓰면 구문오류
		System.out.println("오늘은 \"금요일\" 입니다.");
		System.out.println('\'');
		
		// \n : 개행문자
		
		/*
		 * 상수
		 * 수학 -> 변하지않는 값
		 * 프로그래밍 -> 한번만 쓸 수 있는 메모리
		 * 
		 * [표현법]
		 * final 자료형 변수이름;
		 * 
		 * 상수의 변수명은 모두 대문자로 짓는 것이 일반적인다.
		 * 대문자로 작성하되 스네이크케이스를 사용함.
		 * */
		
		final int MY_AGE;
		MY_AGE = 100;
//		MY_AGE = 111; --값을 한번 초기화하면 더이상 변경할 수 없음.
		System.out.println(MY_AGE);
		
		
		
		/*
		 * 프로그래밍에서 이름 짓는 방식
		 * 카멜케이스 : 단어를 나열하며 이름을 짓되, 두번째단어부터는 단어의 첫글자를 대문자로 시작(자바에서 모든 메서드, 변수명)
		 * ex) userName, totalPrice
		 * 
		 * 파스칼케이스 : 카멜케이스에서 첫글자도 대문자로 작성(자바에서는 클래스명에 사용)
		 * ex) UserName, TotalPrice
		 * 
		 * 스네이크케이스 : 문자와 문자사이를 언더스코어(_)를 통해서 이어주는 것(파이썬에서 주로 사용)
		 * ex) user_name, total_price
		 * 
		 * 대문자스네이크케이스 : 스네이크케이스 + 모두 대문자 (대부분 언어에서 상수명)
		 * ex) USER_NAME
		 * 
		 * 케밥케이스 : 단어를 소문자로 작성하되 문자와 문자사이를 하이픈(-)으로 이어주는 것(html속성, url, 설정파일등)
		 * ex) user-name
		 * */
	}

}









