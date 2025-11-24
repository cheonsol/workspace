package com.kh.Insurance.service;

import static com.kh.Insurance.common.Template.close;
import static com.kh.Insurance.common.Template.commit;
import static com.kh.Insurance.common.Template.getConnection;
import static com.kh.Insurance.common.Template.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.Insurance.model.AccidentWeight;
import com.kh.Insurance.model.DriveWeight;
import com.kh.Insurance.model.Dao.DriveWeightDao;



public class DriveWeightService {
	private DriveWeightDao dd = new DriveWeightDao();

	public DriveWeightService() {
		super();
	}
	public int insertDriveWeight(DriveWeight d) {
		Connection conn = getConnection();
        int result = dd.insertDriveWeight(d, conn);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public double selectDriveWeight(int id, int car, int career) {
		Connection conn = getConnection();
		double score = dd.selectDriveWeight(id, car, career, conn);
		close(conn);
		return score;
	}
	

}
