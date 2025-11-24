package com.kh.option;

/**
 * 접근제한자 : 클래스, 필드, 메서드등 구성 요소의 접근 범위를 제한하는 키워드
 *           불필요하거나 위험한 접근을 막아 데이터 무결성과 보안을 유지.
 * public > protected > default > private
 * 
 * public : 어디서든 접근가능
 * protected : 같은패키지 + 상속관계인 경우 접근가능
 * default : 같은 패키지 내에서 접근가능
 * private : 오직 해당 클래스에서만 접근가능
 */

public class Book {
	//필드(private) -> 정보은닉
	private String title; //제목
	private String genre; //장르
	private String author; //저자
	private int maxPage; //페이지수
	
	public Book() {
		super();
	}

	public Book(String title, String genre, String author, int maxPage) {
		super();
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.maxPage = maxPage;
	}
	
	//Getter, Setter
	//alt+shift+s -> r : 생성단축키
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		if(maxPage < 1) {
			this.maxPage = 1;
			return;
		} 
		
		this.maxPage = maxPage;
	}
	
	
}
