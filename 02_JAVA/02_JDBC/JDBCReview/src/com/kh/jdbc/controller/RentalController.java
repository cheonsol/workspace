package com.kh.jdbc.controller;

import com.kh.jdbc.model.vo.Rental;
import com.kh.jdbc.service.RentalService;
import com.kh.jdbc.view.BookMenu;

public class RentalController {
	private RentalService rentalService = new RentalService();

	public void rentBook(Long memberId, Long bookId) {
		Rental r = new Rental();
		r.setMemberId(memberId);
		r.setBookId(bookId);
		
		int result = rentalService.rentBook(r);
		
		if(result > 0) {
			new BookMenu().displaySuccess("대여");
		} else {
			new BookMenu().displayFail("대여");
		}
	}
	
	public void returnBook(Long memberId, Long bookId) {
		Rental r = new Rental();
		r.setMemberId(memberId);
		r.setBookId(bookId);
		
		int result = rentalService.returnBook(r);
		
		if(result > 0) {
			new BookMenu().displaySuccess("반납");
		} else {
			new BookMenu().displayFail("반납");
		}
	}
}
