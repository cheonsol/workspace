package com.kh.example.gearrent;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GearRentMenu {
	private GearRentController gc = new GearRentController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		while(true) {
			System.out.println("=== KH 장비 대여 관리 ===");
			System.out.println("1) 장비등록  2) 회원등록  3) 대여  4) 반남  5) 태그검색");
			System.out.println("6) 키워드검색  7) 전체장비 8) 대여중목록  9) 종료");
			System.out.print("메뉴 : ");
			
			int sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
				case 1: addDevice(); break;
				case 2: addMember(); break;
				case 3: borrow(); break;
				case 4: returnItem(); break;
				case 5: findByTag(); break;
				case 6: findByKeyword(); break;
				case 7: printAllDevices(); break;
				case 8: printActiveLoans(); break;
				case 9: System.out.println("종료합니다."); return;
				default: System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				}
			System.out.println();
		}
	}
	
	public void addDevice() {
		System.out.print("유형(1:Camera, 2:Laptop) : ");
		int type = sc.nextInt();
	
		System.out.print("장비ID : ");
		String id = sc.next();
		
		System.out.print("장비명 : ");
		String name = sc.next();
		
		System.out.print("분류 : ");
		String category = sc.next();
		
		System.out.print("태그(쉼표로 구분) : ");
		String tag = sc.next();
		sc.nextLine();
		
		Set<String> tags = new HashSet<>();
		for(String s : tag.split(",")) {
			tags.add(s.trim());
		}
		
		Device device;
		switch(type) {
			case 1:
				device = new Camera(id, name, category, tags);
				break;
			case 2:
				device = new Laptop(id, name, category, tags);
			default:
				System.out.println("유형을 잘못 입력하셨습니다.");
				return;
		}
		
		boolean isOk = gc.addDevice(device);
		System.out.println(isOk ? "등록 완료" : "중복된 ID입니다. 다시 입력해주세요.");	
	}
	
	public void addMember() {
		System.out.print("회원ID : ");
		String id = sc.next();
		
		System.out.print("이름 : ");
		String name = sc.next();
		sc.nextLine();
		
		boolean isOk = gc.addMember(new Member(id, name));
		System.out.println(isOk ? "가입 완료" : "중복된 ID입니다. 다시 입력해주세요.");	
	}
	
	public void borrow() {
		System.out.print("회원ID : ");
		String memberId = sc.next();
		
		System.out.print("장비ID : ");
		String itemId = sc.next();
		
		System.out.print("대여일(YYYY-MM-DD) : ");
		String day = sc.next();
		sc.nextLine();
		
		LocalDate today = LocalDate.parse(day);
		
		Loan loan = gc.borrow(memberId, itemId, today);
		if(loan == null) {
			System.out.println("대여가 불가한 장비입니다.");
			return;
		}
		System.out.println("대여 생성 완료 : " + loan);
		System.out.println("반납 예정일 : " + loan.getDueDate());
	}
	
	public void returnItem() {
		System.out.print("장비ID : ");
		String itemId = sc.next();
		
		System.out.print("반납일(YYYY-MM-DD) : ");
		String day = sc.next();
		sc.nextLine();
		
		LocalDate today = LocalDate.parse(day);
		
		int fee = gc.returnItem(itemId, today);
		
		if(fee == -1) {
			System.out.println("대여이력이 없습니다.");
		} else {
			System.out.println("반납 완료, 연체로 : " + fee + "원");
		}
	}
	
	public void findByTag() {
		System.out.print("검색 태그 : ");
		String tag = sc.nextLine();
		
		List<Device> list = gc.findByTag(tag);
		if(list.isEmpty()) {
			System.out.println("결과 없음");
		} else {
			for(Device d : list) {
				System.out.println(d);
			}
		}
	}
	
	public void findByKeyword() {
		System.out.print("검색 키워드(이름/카테고리) : ");
		String keyword = sc.nextLine();
		
		List<Device> list = gc.findByKeyword(keyword);
		if(list.isEmpty()) {
			System.out.println("결과 없음");
		} else {
			for(Device d : list) {
				System.out.println(d);
			}
		}
	}
	
	public void printAllDevices() {
		Collection<Device> all = gc.getAllDevices();
		
		if(all.isEmpty()) {
			System.out.println("등록 장비 없음");
		} else {
			for(Device d : all) {
				System.out.println(d);
			}
		}
	}
	
	public void printActiveLoans() {
		Collection<Loan> all = gc.getActiveLoans();
		
		if(all.isEmpty()) {
			System.out.println("대여중 목록 없음");
		} else {
			for(Loan l : all) {
				System.out.println(l);
			}
		}
	}
}
