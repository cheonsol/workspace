package com.kh.project.service;

import static com.kh.project.common.ProjectTemplate.close;
import static com.kh.project.common.ProjectTemplate.commit;
import static com.kh.project.common.ProjectTemplate.getConnection;
import static com.kh.project.common.ProjectTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.project.dao.Dao;
import com.kh.project.vo.Member;
import com.kh.project.vo.Movie;
import com.kh.project.vo.Rate;

public class Service {
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		
		int result = new Dao().insertMember(m, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int updateMember(Member m) {
		Connection conn = getConnection();
		
		int result = new Dao().updateMember(m, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteMember(Member m) {
		Connection conn = getConnection();
		
		int result = new Dao().deleteMember(m, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public ArrayList<Member> memberSearch(String keyword) {
		Connection conn = getConnection();
		ArrayList<Member> list = new Dao().memberSearch(keyword, conn);
		close(conn);
		return list;
	}
	
	public int insertMovie(Movie movie) {
		Connection conn = getConnection();
		
		int result = new Dao().insertMovie(movie, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int updateMovie(Movie movie) {
		Connection conn = getConnection();
		
		int result = new Dao().updateMovie(movie, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteMovie(Movie movie) {
		Connection conn = getConnection();
		
		int result = new Dao().deleteMovie(movie, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}	
	
	public ArrayList<Movie> movieSearch(String keyword) {
		Connection conn = getConnection();
		ArrayList<Movie> list = new Dao().movieSearch(keyword, conn);
		close(conn);
		return list;
	}
	
	public List<Movie> selectMovieList() {
		Connection conn = getConnection();
		
		List<Movie> list = new Dao().selectMovieList(conn);
		close(conn);
		
		return list;
	}
	
	public int insertRate(Rate r) {
		Connection conn = getConnection();
		
		int result = new Dao().insertRate(r, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public List<Rate> selectRateList() {
		Connection conn = getConnection();
		
		List<Rate> list = new Dao().selectRateList(conn);
		close(conn);
		
		return list;
	}
	
	public int updateRate(Rate r) {
		Connection conn = getConnection();
		
		int result = new Dao().updateRate(r, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteRate(Rate r) {
		Connection conn = getConnection();
		
		int result = new Dao().deleteRate(r, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public ArrayList<Rate> rateSearch(String keyword) {
		Connection conn = getConnection();
		ArrayList<Rate> list = new Dao().RateSearch(keyword, conn);
		close(conn);
		return list;
	}
}
