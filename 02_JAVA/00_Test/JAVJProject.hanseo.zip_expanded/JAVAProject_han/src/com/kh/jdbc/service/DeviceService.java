package com.kh.jdbc.service;

import java.util.List;

import com.kh.jdbc.model.vo.Device;

public interface DeviceService {
	int insertDevice(Device d);
	List<Device> selectAllDevices();
	int updateDevice(Device d);
	int deleteDevice(Long devNo);
	List<Device> searchDeviceByName(String keyword);
}
