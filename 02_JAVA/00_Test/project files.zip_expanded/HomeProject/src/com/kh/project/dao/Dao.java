package com.kh.project.dao;

import static com.kh.project.common.ProjectTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.project.vo.Member;
import com.kh.project.vo.Movie;
import com.kh.project.vo.Rate;

public class Dao {
	private Properties prop = new Properties();

	public Dao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/projectquery.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMember(Member m, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserName());
			pstmt.setInt(3, m.getUserAge());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateMember(Member m, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMovie");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(3, m.getUserId());
			pstmt.setString(1, m.getUserName());
			pstmt.setInt(2, m.getUserAge());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteMember(Member m, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
public ArrayList<Member> memberSearch(String keyword, Connection conn) {
		
		ArrayList<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setUserId(rset.getString("USER_ID"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setUserAge(rset.getInt("USER_AGE"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int insertMovie(Movie movie, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMovie");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, movie.getMovieName());
			pstmt.setString(2, movie.getMovieGenre());
			pstmt.setInt(3, movie.getMovieAge());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateMovie(Movie movie, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMovie");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(3, movie.getMovieName());
			pstmt.setString(1, movie.getMovieGenre());
			pstmt.setInt(2, movie.getMovieAge());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteMovie(Movie movie, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMovie");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, movie.getMovieName());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Movie> movieSearch(String keyword, Connection conn) {
		
		ArrayList<Movie> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchMovie");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Movie movie = new Movie();
				movie.setMovieName(rset.getString("MOVIE_NAME"));
				movie.setMovieGenre(rset.getString("MOVIE_GENRE"));
				movie.setMovieAge(rset.getInt("MOVIE_AGE"));
				
				list.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Movie> selectMovieList(Connection conn) {
		
		ArrayList<Movie> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMovieList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Movie movie = new Movie();
				movie.setMovieName(rset.getString("MOVIE_NAME"));
				movie.setMovieGenre(rset.getString("MOVIE_GENRE"));
				movie.setMovieAge(rset.getInt("MOVIE_AGE"));
				
				list.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int insertRate(Rate r, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertRate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getUserId());
			pstmt.setString(2, r.getMovieName());
			pstmt.setInt(3, r.getMovieScore());
			pstmt.setString(4, r.getMovieComment());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateRate(Rate r, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateRate");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(3, r.getUserId());
			pstmt.setString(4, r.getMovieName());
			pstmt.setInt(1, r.getMovieScore());
			pstmt.setString(2, r.getMovieComment());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteRate(Rate r, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteRate");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getUserId());
			pstmt.setString(2, r.getMovieName());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Rate> selectRateList(Connection conn) {
		ResultSet rset = null;
		ArrayList<Rate> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectRateList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Rate r = new Rate();
				r.setUserId(rset.getString("RATE_USER_ID"));
				r.setMovieName(rset.getString("RATE_MOVIE_NAME"));
				r.setMovieScore(rset.getInt("RATE_MOVIE_SCORE"));
				r.setMovieComment(rset.getString("RATE_MOVIE_COMMENT"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	return list;
	}
	
	public ArrayList<Rate> RateSearch(String keyword, Connection conn) {
		
		ArrayList<Rate> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("rateSearch");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Rate r = new Rate();
				r.setUserId(rset.getString("RATE_USER_ID"));
				r.setMovieName(rset.getString("RATE_MOVIE_NAME"));
				r.setMovieScore(rset.getInt("RATE_MOVIE_SCORE"));
				r.setMovieComment(rset.getString("RATE_MOVIE_COMMENT"));
				
				list.add(r);
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
