package com.kh.jdbc.model.dao;

import static com.kh.jdbc.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.jdbc.model.vo.Book;

public class BookDao {
	private Properties prop = new Properties();
	
	public BookDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/book-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertBook(Book book, Connection conn) {
		//book추가 -> int
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBook");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<Book> selectAllBooks(Connection conn) {
		List<Book> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllBooksWithRentalStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Book book = new Book();
				book.setBookId(rset.getLong("BOOK_ID"));
				book.setTitle(rset.getString("TITLE"));
				book.setAuthor(rset.getString("AUTHOR"));
				book.setPublisher(rset.getString("PUBLISHER"));
				book.setRented(rset.getInt("IS_RENTED") == 1);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int updateBook(Book book, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBook");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setLong(4, book.getBookId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteBook(Long bookId, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBook");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bookId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<Book> searchBookByTitle(String keyword, Connection conn) {
		List<Book> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchBookByTitle");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Book book = new Book();
				book.setBookId(rset.getLong("BOOK_ID"));
				book.setTitle(rset.getString("TITLE"));
				book.setAuthor(rset.getString("AUTHOR"));
				book.setPublisher(rset.getString("PUBLISHER"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
}
