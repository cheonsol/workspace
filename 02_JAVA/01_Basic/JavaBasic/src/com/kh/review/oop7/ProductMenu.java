package com.kh.review.oop7;

import java.util.Scanner;

//사용자에게 입력받거나 출력
public class ProductMenu {
	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();
	
	public void mainMenu() {
		int sel;
		do {
			System.out.println("====== 상품 관리 메뉴 ======");
			System.out.println("1. 상품 추가");
			System.out.println("2. 상품 전체 조회");
			System.out.println("3. 상품 삭제(최근저장)");
			System.out.println("4. 상품명 키워드 검색");
			System.out.println("5. 상품가격 수정");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴 번호 : ");
			
			sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
				case 1: insertProduct(); break;
				case 2: selectProduct(); break;
				case 3: deleteProduct(); break;
				case 4: searchProduct(); break;
				case 5: updateProductPrice(); break;
				case 9: System.out.println("프로그램을 종료합니다."); break;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			System.out.println();
		
		} while(sel != 9);
	}
	
	/*
	 * 상품명과 수정할 가격을 입력받아 해당상품의 가격을 수정한다.
	 */
	public void updateProductPrice() {
		System.out.print("수정할 상품명 : ");
		String name = sc.next();
		
		System.out.print("수정할 가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		boolean isUpdate = pc.updateProductPrice(name, price);
		if(isUpdate) {
			System.out.println("정상적으로 수정하였습니다.");
		} else {
			System.out.println("수정에 실패하였습니다.");
		}
	}
	
	/*
	 * 가장 최근에 추가된 상품을 제거하고 성공여부를 출력
	 */
	public void deleteProduct() {
		//상품목록
		boolean isDelete = pc.deleteProduct();
		if(isDelete) {
			System.out.println("정상적으로 삭제하였습니다.");
		} else {
			System.out.println("삭제에 실패하였습니다.");
		}
	}
	
	/*
	 * 키워드를 입력받아 제품명을 통한 키워드 검색을 하여
	 * 제품목록을 출력
	 */
	public void searchProduct() {
		System.out.print("키워드 입력 : ");
		String keyword = sc.nextLine();
		Product[] productArr = pc.searchProduct(keyword);
		
		printProductArr(productArr);
	}
	
	public void selectProduct() {
		Product[] productArr = pc.selectProduct();
		
		printProductArr(productArr);
	}
	
	public void printProductArr(Product[] productArr) {
		if(productArr != null && productArr[0] != null) {
			for(Product p : productArr) {
				if(p == null) {
					return;
				}
				System.out.println(p.inform());
			}
		} else {
			System.out.println("상품이 존재하지 않습니다.");
		}
	}
	
	public void insertProduct() {
		System.out.print("추가할 상품명 : ");
		String name = sc.nextLine();
		
		System.out.print("추가할 가격 : ");
		int price = sc.nextInt();
		
		System.out.print("추가할 브랜드 : ");
		String brand = sc.next();
		sc.nextLine();
		
		boolean isInsert = pc.insertProduct(name, price, brand);
		if(isInsert) {
			System.out.println("정상적으로 추가하였습니다.");
		} else {
			System.out.println("추가에 실패하였습니다.");
		}
	}
}
