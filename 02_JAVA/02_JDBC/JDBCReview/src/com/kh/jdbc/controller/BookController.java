package com.kh.jdbc.controller;

import java.util.List;

import com.kh.jdbc.model.vo.Book;
import com.kh.jdbc.service.BookService;
import com.kh.jdbc.view.BookMenu;

public class BookController {
	private BookService bookService;
	
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	public void insertBook(String title,String author,String publisher) {
		Book b = new Book(null, title, author, publisher, false);
		int result = bookService.insertBook(b);
		
		if(result > 0) {
			new BookMenu().displaySuccess("도서 등록");
		} else {
			new BookMenu().displayFail("도서 등록");
		}
	}
	
	public void selectAllBooks() {
		List<Book> list = bookService.selectAllBooks();
		
		new BookMenu().printAllBookList(list);
	}
	
	public void updateBook(Long bookId, String title, String author, String publisher) {
		Book book = new Book(bookId, title, author, publisher, false);
		int result = bookService.updateBook(book);
		
		if(result > 0) {
			new BookMenu().displaySuccess("도서 정보 변경");
		} else {
			new BookMenu().displayFail("도서 정보 변경");
		}
	}
	
	public void deleteBook(Long bookId) {
		int result = bookService.deleteBook(bookId);
		
		if(result > 0) {
			new BookMenu().displaySuccess("도서 삭제");
		} else {
			new BookMenu().displayFail("도서 삭제");
		}
	}
	
	public void searchBookByTitle(String keyword) {
		List<Book> list = bookService.searchBookByTitle(keyword);
		
		new BookMenu().printAllBookList(list);
	}
}
