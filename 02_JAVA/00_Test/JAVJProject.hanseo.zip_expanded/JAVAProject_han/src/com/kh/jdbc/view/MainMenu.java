package com.kh.jdbc.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.controller.CustomerController;
import com.kh.jdbc.controller.DeviceController;
import com.kh.jdbc.controller.RentalController;
import com.kh.jdbc.model.vo.Customer;

public class MainMenu {
	private Scanner sc;
	private DeviceController deviceController;
	private CustomerController cutomerController;
	private RentalController rentalController;

	
	public MainMenu() {
		super();
	}

	public MainMenu(Scanner sc, DeviceController deviceController) {
		super();
		this.sc = sc;
		this.deviceController = deviceController;
		this.cutomerController = new CustomerController();
		this.rentalController = new RentalController();
	}

	public void mainMenu() {
		while (true) {
			System.out.println("===== 의료기기 제품 관리 프로그램 =====");
			System.out.println("1. 의료기기 제품 추가");
			System.out.println("2. 의료기기 제품 삭제");
			System.out.println("3. 의료기기 제품 대여");
			System.out.println("4. 의료기기 제품 정보 수정");
			System.out.println("5. 의료기기 제품 검색");
			System.out.println("6. 의료기기 제품 전체 조회");
			System.out.println("7. 회원 전체 조회");
			System.out.println("8. 의료기기 제품 반납");
			System.out.println("9. 프로그램 종료");

			System.out.print("메뉴 입력 : ");
			int sel = sc.nextInt();
			sc.nextLine();

			switch (sel) {
			case 1: insertDevice(); break;
			case 2: deleteDevice(); break;
			case 3: rentalDevice(); break;
			case 4: updateDevice(); break;
			case 5: DeviceSearchByKeyword(); break;
			case 6: deviceController.selectDeviceAll(); break;
			case 7: cutomerController.selectAllCustomer(); break;
			case 8: returnDevice(); break;
			case 9: System.out.println("프로그램을 종료합니다."); return;
			default:System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
			}

			System.out.println();
		}
	}

	public void insertDevice() {
		System.out.println();
		System.out.println("===== 의료기기 제품 추가 =====");

		System.out.print("카테고리 : ");
		String category = sc.nextLine();

		System.out.print("제품명 : ");
		String devName = sc.nextLine();

		System.out.print("가격 : ");
		String price = sc.nextLine();

		deviceController.insertDevice(category, devName, price);
	}

	public void deleteDevice() {
		System.out.println();
		System.out.println("===== 의료기기 제품 삭제 =====");

		System.out.print("삭제할 제품번호 : ");
		Long devNo = sc.nextLong();
		sc.nextLine();

		deviceController.deleteDevice(devNo);
	}

	public void rentalDevice() {
		System.out.println();
		System.out.println("===== 의료기기 제품 대여 =====");

		System.out.print("회원 번호 : ");
		Long cutoNo = sc.nextLong();
		sc.nextLine();
		
		//도서 몰고 보여주고 도서ID입력받기
		deviceController.selectDeviceAll();
		
		System.out.print("대여할 제품 번호 : ");
		Long devNo = sc.nextLong();
		sc.nextLine();
		
		rentalController.rentDevice(cutoNo, devNo);;

	}

	public void updateDevice() {
		System.out.println();
		System.out.println("===== 의료기기 제품 정보 변경 =====");

		System.out.print("정보를 변경할 제품번호 : ");
		Long devNo = sc.nextLong();
		sc.nextLine();

		System.out.print("변경할 가격 : ");
		String price = sc.nextLine();

		deviceController.updateDevice(devNo, price);
	}

	public void DeviceSearchByKeyword() {
		System.out.println();
		System.out.println("===== 키워드로 제품 검색 =====");

		System.out.print("키워드 : ");
		String keyword = sc.nextLine();

		deviceController.DeviceSearchByKeyword(keyword);
	}
	
	public void printAlllCustomerList(List<Customer> list) {
		if(list.isEmpty()) {
			System.out.println("회원이 없습니다.");
		} else {
			System.out.println("===== 회원 목록 =====");
			for(Customer c : list) {
				System.out.println(c);
			}
		}
	}
	
	public void returnDevice() {
		System.out.println("===== 제품 반납 =====");
		cutomerController.selectAllCustomer();
		System.out.print("회원 번호 : ");
		Long cutoNo = sc.nextLong();
		sc.nextLine();
		
		//도서 몰고 보여주고 도서ID입력받기
		deviceController.selectDeviceAll();
		
		System.out.print("대여할 제품 번호 : ");
		Long devNo = sc.nextLong();
		sc.nextLine();
		
		rentalController.returnDevice(cutoNo, devNo);;
	}

	public static void displaySuccess(String msg) {
		System.out.println("\n서비스 요청 성공 : " + msg);
	}

	// 서비스요청 처리 후 실패했을 때 사용자가 보게될 화면
	// msg : 기능별 실패메세지
	public static void displayFail(String msg) {
		System.out.println("\n서비스 요청 실패 : " + msg);
	}

	public static void displayNoData(String msg) {
		System.out.println("\n" + msg);
	}

	public static void displayList(List list, String title) {
		System.out.println("========== " + title + " ==========");
		for (Object o : list) {
			System.out.println(o);
		}
	}
}
