package com.kh.jdbc.service;

import static com.kh.jdbc.common.JDBCTemplate.close;
import static com.kh.jdbc.common.JDBCTemplate.commit;
import static com.kh.jdbc.common.JDBCTemplate.getConnection;
import static com.kh.jdbc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.model.dao.DeviceDao;
import com.kh.jdbc.model.vo.Device;

public class DeviceServiceImpl implements DeviceService {
	private DeviceDao deviceDao = new DeviceDao();

	@Override
	public int insertDevice(Device d) {
		Connection conn = getConnection();

		int result = deviceDao.insertDevice(d, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	@Override
	public List<Device> selectAllDevices() {
		Connection conn = getConnection();
		List<Device> list = deviceDao.selectDeviceAll(conn);
		close(conn);
		return list;
	}

	@Override
	public int updateDevice(Device d) {
		Connection conn = getConnection();
		int result = deviceDao.updateDevice(d, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	@Override
	public int deleteDevice(Long devNo) {
		Connection conn = getConnection();
		int result = deviceDao.deleteDevice(devNo, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	@Override
	public List<Device> searchDeviceByName(String keyword) {
		Connection conn = getConnection();
		List<Device> list = deviceDao.DeviceSearchByCategory(keyword, conn);
		close(conn);
		return list;
	}

}
