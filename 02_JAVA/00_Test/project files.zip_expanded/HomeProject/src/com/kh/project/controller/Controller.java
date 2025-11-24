package com.kh.project.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.project.menu.Menu;
import com.kh.project.service.Service;
import com.kh.project.vo.Member;
import com.kh.project.vo.Movie;
import com.kh.project.vo.Rate;

public class Controller {
	private Service s = new Service();
	
	public Controller() {
		super();
	}
	
	public void insertMember(String userId, String userName, int userAge) {
		Member m = new Member(userId, userName, userAge);
		
		int result = s.insertMember(m);
		
		if(result > 0) {
			new Menu().displaySuccess("회원 추가에 성공하였습니다.");
		} else {
			new Menu().displayFail("회원추가에 실패하였습니다. 다시 시도해주세요");
		}
	}
	
	public void updateMember(String userId,String userName, int userAge) {
		Member m = new Member(userId, userName, userAge);
		
		int result = s.updateMember(m);
		
		if(result > 0) {
			new Menu().displaySuccess("회원 정보 수정을 완료하였습니다");
		} else {
			new Menu().displayFail("회원 정보 수정에 실패하였습니다. 다시 시도해주세요");
		}
	}
	
	public void deleteMember(String userId) {
		Member m = new Member(userId);
				
		int result = s.deleteMember(m);
		if(result > 0) {
			new Menu().displaySuccess("회원 삭제를 완료하였습니다");
		} else {
			new Menu().displayFail("회원삭제에 실패하였습니다. 다시 시도해주세요");
		}	
	}
	
	public void memberSearch(String keyword) {
		ArrayList<Member> list = s.memberSearch(keyword);
		
		if(list.isEmpty()) {
			new Menu().displayNoData("검색 결과가 없습니다.");
		} else {
			new Menu().displayList(list, "검색 목록");
		}
	}
	
	public void insertMovie(String movieName,String movieGenre, int movieAge) {
		Movie movie = new Movie(movieName, movieGenre, movieAge);
		
		int result = s.insertMovie(movie);
		
		if(result > 0) {
			new Menu().displaySuccess("영화를 추가하였습니다.");
		} else {
			new Menu().displayFail("영화 추가에 실패하였습니다. 다시 시도해주세요");
		}
	}
	
	public void updateMovie(String movieName, String movieGenre, int movieAge) {
		Movie movie = new Movie(movieName, movieGenre, movieAge);
		
		int result = s.updateMovie(movie);
		if(result > 0) {
			new Menu().displaySuccess("영화 정보 수정을 완료하였습니다");
		} else {
			new Menu().displayFail("정보 수정에 실패하였습니다. 다시 시도해주세요");
		}
	}
	
	public void deleteMovie(String movieName) {
		Movie movie = new Movie(movieName);
		
		int result =s.deleteMovie(movie);
		if(result > 0) {
			new Menu().displaySuccess("영화 삭제를 완료하였습니다");
		} else {
			new Menu().displayFail("삭제에 실패하였습니다. 다시 시도해주세요");
		}
	}
	
	public void movieSearch(String keyword) {
		ArrayList<Movie> list = s.movieSearch(keyword);
		
		if(list.isEmpty()) {
			new Menu().displayNoData("검색 결과가 없습니다.");
		} else {
			new Menu().displayList(list, "검색 목록");
		}
	}
	
	public void selectMovieList() {
		List<Movie> list = s.selectMovieList();
		
		if(list.isEmpty()) {
			new Menu().displayNoData("조회 결과가 없습니다.");
		} else {
			new Menu().displayList(list, "목록");
		}
	}
	
	public void insertRate(String userId, String movieName, int movieScore, String movieComment) {
		Rate r = new Rate(userId, movieName, movieScore, movieComment);
		
		int result = s.insertRate(r);
		
		if(result > 0) {
			new Menu().displaySuccess("평가를 추가하였습니다");
		} else {
			new Menu().displayFail("평가 추가에 실패하였습니다. 다시 시도해주세요");
		}
	}
	
	public void selectRateAll() {
		List<Rate> list = s.selectRateList();
		
		if(list.isEmpty()) {
			new Menu().displayNoData("조회 결과가 없습니다.");
		} else {
			new Menu().displayList(list, "목록");
		}
	}
	
	public void updateRate(String userId, String movieName, int movieScore, String movieComment) {
		Rate r = new Rate(userId, movieName, movieScore, movieComment);
		int result = s.updateRate(r);
		if(result > 0) {
			new Menu().displaySuccess("수정 완료하였습니다");
		} else {
			new Menu().displayFail("수정에 실패하였습니다. 다시 시도해주세요");
		}
	}
	
	public void deleteRate(String userId, String movieName) {
		Rate r = new Rate(userId, movieName);
		int result = s.deleteRate(r);
		if(result > 0) {
			new Menu().displaySuccess("삭제하였습니다");
		} else {
			new Menu().displayFail("삭제에 실패하였습니다. 다시 시도해주세요");
		}
	}
	
	public void RateSearch(String keyword) {
		ArrayList<Rate> list = s.rateSearch(keyword);
		
		if(list.isEmpty()) {
			new Menu().displayNoData("검색 결과가 없습니다.");
		} else {
			new Menu().displayList(list, "검색 목록");
		}
	}
}
