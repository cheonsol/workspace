package com.kh.project.vo;

public class Rate {
	private String userId;
	private String movieName;
	private int movieScore;
	private String movieComment;
	
	public Rate() {
		super();
	}

	public Rate(String userId, String movieName, int movieScore, String movieComment) {
		super();
		this.userId = userId;
		this.movieName = movieName;
		this.movieScore = movieScore;
		this.movieComment = movieComment;
	}

	public Rate(String userId, String movieName) {
		super();
		this.userId = userId;
		this.movieName = movieName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getMovieScore() {
		return movieScore;
	}

	public void setMovieScore(int movieScore) {
		this.movieScore = movieScore;
	}

	public String getMovieComment() {
		return movieComment;
	}

	public void setMovieComment(String movieComment) {
		this.movieComment = movieComment;
	}

	@Override
	public String toString() {
		return "[아이디 : " + userId + ", 영화 : " + movieName + ", 점수 : " + movieScore + ", 코멘트 : "
				+ movieComment + "]";
	}
	
	
}
