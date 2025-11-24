package com.kh.project.vo;

public class Movie {
	private String movieName;
	private String movieGenre;
	private int movieAge;
	
	public Movie() {
		super();
	}

	public Movie(String movieName) {
		super();
		this.movieName = movieName;
	}

	public Movie(String movieName, String movieGenre, int movieAge) {
		super();
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieAge = movieAge;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public int getMovieAge() {
		return movieAge;
	}

	public void setMovieAge(int movieAge) {
		this.movieAge = movieAge;
	}

	@Override
	public String toString() {
		return "[영화 이름 : " + movieName + ", 영화 장르 : " + movieGenre + ", 영화 관람가 : " + movieAge + "]";
	}
	
	
}
