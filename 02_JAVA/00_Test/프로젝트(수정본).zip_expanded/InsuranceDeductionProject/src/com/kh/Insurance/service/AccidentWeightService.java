package com.kh.Insurance.service;

import static com.kh.Insurance.common.Template.close;
import static com.kh.Insurance.common.Template.commit;
import static com.kh.Insurance.common.Template.getConnection;
import static com.kh.Insurance.common.Template.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.Insurance.model.AccidentWeight;
import com.kh.Insurance.model.CompanyVo;
import com.kh.Insurance.model.Dao.AccidentWeightDao;





public class AccidentWeightService {
	private AccidentWeightDao ad = new AccidentWeightDao();

	public AccidentWeightService() {
		super();
	}
	public int insertAccidentWeight(AccidentWeight a) {
		Connection conn = getConnection();
        int result = ad.insertAccidentWeight(a, conn);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public double selectAccidentWeight(int id, int car, int count) {
		Connection conn = getConnection();
		
		double score = ad.selectAccidentWeight(id, car, count, conn);
		
		return score;
	}
	
}
