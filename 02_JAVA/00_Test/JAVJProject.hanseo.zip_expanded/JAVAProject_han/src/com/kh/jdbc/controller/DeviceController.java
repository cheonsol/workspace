package com.kh.jdbc.controller;

import java.util.List;

import com.kh.jdbc.model.vo.Device;
import com.kh.jdbc.service.DeviceService;
import com.kh.jdbc.view.MainMenu;

public class DeviceController {
	private DeviceService deviceService;

	public DeviceController(DeviceService deviceService) {
		super();
		this.deviceService = deviceService;
	}

	public void insertDevice(String category, String medName, String price) {
		Device d = new Device(category, medName, Integer.parseInt(price));

		int result = deviceService.insertDevice(d);

		if (result > 0) {
			new MainMenu().displaySuccess("성공적으로 제품이 추가되었습니다.");
		} else {
			new MainMenu().displayFail("제품추가에 실패하였습니다.");
		}
	}

	public void deleteDevice(Long devNo) {

		int result = deviceService.deleteDevice(devNo);
		if (result > 0) {
			new MainMenu().displaySuccess("성공적으로 제품이 삭제되었습니다.");
		} else {
			new MainMenu().displayFail("제품삭제에 실패하였습니다.");
		}
	}


	public void updateDevice(Long devNo, String price) {
		Device d = new Device();
		d.setDevNo(devNo);
		d.setPrice(Integer.parseInt(price));

		int result = deviceService.updateDevice(d);

		if (result > 0) {
			new  MainMenu().displaySuccess("성공적으로 제품정보를 수정하였습니다.");
		} else {
			new MainMenu().displayFail("제품정보를 수정하는데 실패하였습니다.");
		}
	}

	public void DeviceSearchByKeyword(String keyword) {
		List<Device> list = deviceService.searchDeviceByName(keyword);

		if (list.isEmpty()) {
			new  MainMenu().displayNoData("제품목록 키워드 검색 결과가 없습니다.");
		} else {
			new  MainMenu().displayList(list, "키워드 검색 목록");
		}
	}

	public void selectDeviceAll() {
		List<Device> list = deviceService.selectAllDevices();

		if (list.isEmpty()) {
			new MainMenu().displayNoData("제품목록 조회 결과가 없습니다.");
		} else {
			new MainMenu().displayList(list, "제품 목록");
		}
	}

}
