package com.kh.jdbc.controller;

import com.kh.jdbc.model.vo.Rental;
import com.kh.jdbc.service.RentalService;
import com.kh.jdbc.view.MainMenu;

public class RentalController {
	private RentalService rentalService = new RentalService();

	public void rentDevice(Long custNo, Long devNo) {
		Rental r = new Rental();
		r.setCustNo(custNo);
		r.setDevNo(devNo);

		int result = rentalService.rentDevice(r);

		if (result > 0) {
			new MainMenu().displaySuccess("대여");
		} else {
			new MainMenu().displayFail("대여");
		}
	}

	public void returnDevice(Long custNo, Long devNo) {
		Rental r = new Rental();
		r.setCustNo(custNo);
		r.setDevNo(devNo);

		int result = rentalService.returnDevice(r);

		if (result > 0) {
			new MainMenu().displaySuccess("반납");
		} else {
			new MainMenu().displayFail("반납");
		}

	}
}
