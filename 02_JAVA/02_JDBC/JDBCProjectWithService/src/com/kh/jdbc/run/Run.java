package com.kh.jdbc.run;

import com.kh.jdbc.view.MemberMenu;

/*
 * MVC패턴
 * 각 관심사에 따라서 레이어를 분리해서 작성하는 구조
 * Model : 도메인 상태와 규칙(vo, dao, 엔티티, 레포지토리...)
 * View : 화면에 표현, 사용자와의 의사소통(콘솔에 출력, 브라우저 화면...)
 * Controller : 사용자의 의도 해석, 흐름을 제어(요청을 직접적으로 받아 기능을 처리하는 역할)
 * 
 * 왜 MVC패턴을 사용하는가? 
 * 하나의 클레스또는 하나의 패키지에 작성을 하게되면 유지보수가 어렵고, 재사용성이 떨어지고, 테스트가 어렵다.
 * -> 기능의 분리가 되어있지않고 하나의 메서드에서 전부 처리
 */
public class Run {
	
	public static void main(String[] args) {
		new MemberMenu().mainMenu();
	}

}
