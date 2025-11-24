package com.kh.review.oop7;

public class ProductController {
	private Product[] pro = new Product[10];

	public ProductController() {
		super();
		
		pro[0] = new Product("갤럭시", 1200000, "삼성");
		pro[1] = new Product("아이폰", 1300000, "애플");
		pro[2] = new Product("아이패드", 800000, "애플");
	}
	
	public boolean insertProduct(String pName, int price, String brand) {
		for(int i=0; i<pro.length; i++) {
			if(pro[i] == null) { //가장 먼저 만나는 비어있는 값에
				pro[i] = new Product(pName, price, brand); //새로운 데이터 추가
				return true;
			}
		}
		
		return false;
	}
	
	public Product[] selectProduct() {
		return pro;
	}
	
	/*
	 * 상품목록의 최근 추가된 제품을 삭제
	 */
	public boolean deleteProduct() {
		for(int i=0; i<pro.length; i++) {
			if(i == 0 && pro[i] == null) {
				return false;
			} else if((i != pro.length - 1) && pro[i+1] == null) {
				pro[i] = null;
				return true;
			}
		}
		return false;
	}
	
	//전달받은 key를 상품명을 통한 검색으로
	//검색된 상품 목록을 반환.
	public Product[] searchProduct(String key) {
		Product[] searchArr = new Product[pro.length];
		
		int count = 0;
		for(int i=0; i<pro.length; i++) {
			if(pro[i] == null) {
				break;
			}else if(pro[i].getpName().contains(key)) {
				searchArr[count++] = pro[i];
			}
		}
		
		return searchArr;
	}
	
	/*
	 * 상품명을 검색해서 상품가격을 수정
	 * 성공여부 반환
	 */
	public boolean updateProductPrice(String name, int price) {
		for(Product p : pro) {
			if(p == null) {
				return false;
			} else if(p.getpName().equals(name)){
				p.setPrice(price);
				return true;
			}
		}
		
		return false;
	}
	
	
}
