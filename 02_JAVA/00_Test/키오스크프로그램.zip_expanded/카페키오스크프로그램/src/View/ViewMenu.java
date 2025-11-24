package View;

import java.util.Scanner;

import Controller.Controller;

public class ViewMenu {

	private Controller c = new Controller();// 메뉴 관련 서비스
	private Scanner sc = new Scanner(System.in);

	// 현재 로그인한 회원

	public void start() {
		while (true) {
			System.out.println("\n======= 카페 키오스크 ========");
			System.out.println("1. 회원 로그인/등록");
			System.out.println("2. 메뉴 보기");
			System.out.println("3. 장바구니 보기");
			System.out.println("4. 결제하기");
			System.out.println("5. 종료");
			System.out.print("선택 > ");

			int choice = Integer.parseInt(sc.nextLine()); // 문자열을 정수로 변환한다.

			switch (choice) {
			case 1:
				memberLogin();
				break;
			case 2:
				showMenus();
				break;
			case 3:
				showCart();
				break;
			case 4:
				checkout();
				break;
			case 5:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}

	private void memberLogin() {
		System.out.print("전화번호 입력 (회원 로그인/등록): ");
		String phone = sc.nextLine();

		if (phone.matches("\\d{11,11}")) { // match()를 활용해 숫자만 입력가능하고 11자리만 입력가능하게 설정
			try {
				c.memberLogin(phone); // Controller에 있는 로그인 메서드 호출

			} catch (Exception e) {
				System.err.println("회원 로그인/등록 중 오류 발생: " + e.getMessage());
			}

		} else {
			System.out.println("잘못된 전화번호 입력입니다!!");
		}

	}

	private void showMenus() {

		c.showMenus();
	}

	private void showCart() {
		c.showCart();
	}

	private void checkout() {
		c.checkout();
	}
}