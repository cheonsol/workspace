package com.kh.jdbc.model.vo;

public class Book {
	private Long bookId; 
	private String title;
	private String author;
	private String publisher;
	
	private boolean isRented; //대여상태(true : 대여중, false : 대여가능)
	
	public Book() {
		super();
	}

	public Book(Long bookId, String title, String author, String publisher, boolean isRented) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isRented = isRented;
	}
	

	public boolean isRented() {
		return isRented;
	}

	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "[bookId=" + bookId + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", isRented=" + isRented + "]";
	}

}

