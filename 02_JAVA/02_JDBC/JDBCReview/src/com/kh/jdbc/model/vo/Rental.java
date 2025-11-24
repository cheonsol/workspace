package com.kh.jdbc.model.vo;

import java.sql.Date;

import oracle.sql.DATE;
import oracle.sql.NUMBER;

public class Rental {
	private Long rentalId;
	private Long memberId;
	private Long bookId;
	private Date rentalDate;
	private Date returnDate;
	
	public Rental() {
		super();
	}
	
	public Rental(Long rentalId, Long memberId, Long bookId, Date rentalDate, Date returnDate) {
		super();
		this.rentalId = rentalId;
		this.memberId = memberId;
		this.bookId = bookId;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
	}

	public Long getRentalId() {
		return rentalId;
	}

	public void setRentalId(Long rentalId) {
		this.rentalId = rentalId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "[rentalId=" + rentalId + ", memberId=" + memberId + ", bookId=" + bookId + ", rentalDate="
				+ rentalDate + ", returnDate=" + returnDate + "]";
	}
	
	
}
