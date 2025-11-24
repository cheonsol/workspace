package com.kh.objectarray;

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

	@Override
	public String toString() {
		return "Book [title=" + title + ", genre=" + genre + ", author=" + author + ", maxPage=" + maxPage + "]";
	}
}
