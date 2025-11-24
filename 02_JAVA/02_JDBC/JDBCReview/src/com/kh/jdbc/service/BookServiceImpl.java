package com.kh.jdbc.service;

import static com.kh.jdbc.common.JDBCTemplate.close;
import static com.kh.jdbc.common.JDBCTemplate.commit;
import static com.kh.jdbc.common.JDBCTemplate.getConnection;
import static com.kh.jdbc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.model.dao.BookDao;
import com.kh.jdbc.model.vo.Book;

public class BookServiceImpl implements BookService{

	@Override
	public int insertBook(Book b) {
		Connection conn = getConnection();
		
		int result = new BookDao().insertBook(b, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	@Override
	public List<Book> selectAllBooks() {
		Connection conn = getConnection();
		List<Book> list = new BookDao().selectAllBooks(conn);
		close(conn);
		return list;
	}

	@Override
	public int updateBook(Book b) {
		Connection conn = getConnection();
		int result = new BookDao().updateBook(b, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	@Override
	public int deleteBook(Long bookId) {
		Connection conn = getConnection();
		int result = new BookDao().deleteBook(bookId, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	@Override
	public List<Book> searchBookByTitle(String keyword) {
		Connection conn = getConnection();
		List<Book> list = new BookDao().searchBookByTitle(keyword, conn);
		close(conn);
		return list;
	}

}
