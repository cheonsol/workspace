package com.kh.review.poly;

import java.util.Scanner;

public class LibraryMenu {
	private LibraryController lc = new LibraryController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		
		System.out.print("성별 : ");
		char gender = sc.next().charAt(0);
		
		lc.insertMember(new Member(name, age, gender));
		
		while(true) {
			System.out.println();
			System.out.println("==== 메뉴 ====");
			System.out.println("1. 마이페이지");
			System.out.println("2. 도서 전체 조회 ");
			System.out.println("3. 도서 검색");
			System.out.println("4. 도서 대여하기");
			System.out.println("5. 도서 추가하기");
			System.out.println("6. 도서 삭제하기");
			System.out.println("9. 프로그램 종료하기 ");
			System.out.print("메뉴 번호 :");
			
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				Member m = lc.myInfo();
				if(m != null)
					System.out.println(m);
				break;
			case 2:
				selectAll();
				break;
			case 3:
				searchBook();
				break;
			case 4:
				rentBook();
				break;
			case 5:
				insertBook();
				break;
			case 6:
				deleteBook();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못입력하셨습니다.");
			}
		}
	}
	
	//도서정보를 전체 출력한 후 index를 입력받아
	//도서목록에서 해당 index에 값을 제거한 후
	//도서목록에 생성된 객체에 딱맞게 배열을 다시 생성해서 교체
	public void deleteBook() {
		selectAll();
		
		System.out.print("삭제할 도서번호 : ");
		int index = sc.nextInt();
		sc.nextLine();
		
		lc.deleteBook(index);
	}
	
	//도서정보를 모두 입력받아 컨트롤러의 도서목록에 추가
	//단, 도서목록이 가득찼을경우 길이가+1 배열을 새로 생성하여 추가
	public void insertBook() {
		//정보입력받아 생성
		Book b = null;
		System.out.println("======= 도서 추가 ========");
		System.out.print("책 종류(1.만화 2.요리) : ");
		int kind = sc.nextInt();
		
		System.out.print("도서명 : ");
		String title = sc.next();
		
		System.out.print("저자 : ");
		String author = sc.next();
		
		System.out.print("출판사 : ");
		String publisher  = sc.next();
		
		switch(kind) {
			case 1:
				System.out.print("연령제한 : ");
				int accessAge   = sc.nextInt();
				b = new AniBook(title, author, publisher, accessAge);
				break;
			case 2:
				System.out.print("쿠폰여부(1.발급 2.미발금) : ");
				boolean isCoupon = sc.nextInt() == 1;
				b = new CookBook(title, author, publisher, isCoupon);
		}
		sc.nextLine();
		
		lc.insertBook(b);
	}
	
	public void selectAll() {
		Book[] bookArr = lc.selectAll();
		for(int i=0; i<bookArr.length; i++) {
			if(bookArr[i] == null)
				break;
			
			System.out.printf("%d번 도서 : %s\n", i, bookArr[i]);
		}
	}
	
	public void searchBook() {
		System.out.print("검색할 제목 키워드 : ");
		String keyword = sc.next();
		sc.nextLine();
		
		Book[] searchList = lc.searchBook(keyword);
		if(searchList[0] == null) {
			System.out.println("검색 결과가 없습니다.");
		} else {
			for(Book bk : searchList) {
				if(bk == null)
					break;
				System.out.println(bk);
			}	
		}
	}
	
	public void rentBook() {
		//대여전 목록안내
		selectAll();
		
		System.out.print("대여할 도서 번호 선택 : ");
		int index = sc.nextInt();
		sc.nextLine();
		int result = lc.rentBook(index);
		switch(result) {
		case 0:
			System.out.println("성공적으로 대여되었습니다.");
			break;
		case 1:
			System.out.println("나이 제한으로 대여 불가능입니다.");
			break;
		case 2:
			System.out.println("성공적으로 대여되었습니다. 요리학원 쿠폰이 발급되었으니 마이페이지에서 확인하세요.");
			break;
		default:
			System.out.println("대여가 불가능한 책입니다.");
		}
	}
}
