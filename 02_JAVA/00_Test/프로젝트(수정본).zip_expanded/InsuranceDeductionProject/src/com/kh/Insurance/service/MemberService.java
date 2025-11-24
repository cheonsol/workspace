package com.kh.Insurance.service;

import static com.kh.Insurance.common.Template.close;
import static com.kh.Insurance.common.Template.commit;
import static com.kh.Insurance.common.Template.getConnection;
import static com.kh.Insurance.common.Template.rollback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.kh.Insurance.model.AccidentWeight;
import com.kh.Insurance.model.CompanyVo;
import com.kh.Insurance.model.DriveWeight;
import com.kh.Insurance.model.MemberVo;
import com.kh.Insurance.model.Dao.AccidentWeightDao;
import com.kh.Insurance.model.Dao.CompanyDao;
import com.kh.Insurance.model.Dao.DriveWeightDao;
import com.kh.Insurance.model.Dao.MemberDao;

public class MemberService {
	private MemberDao md = new MemberDao();
	private CompanyService cc= new CompanyService();
	private DriveWeightService dc = new DriveWeightService();
	private AccidentWeightService ac = new AccidentWeightService();

	public MemberService() {
		super();
	}
	
	public int updateJoinMember(MemberVo m) {
		Connection conn = getConnection();
		
		int result = md.updateJoinMemebr(m, conn);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	public int deleteJoinMember(MemberVo m) {
		Connection conn = getConnection();
		
		int result = md.deleteJoinMember(m, conn);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	public double selectFee(MemberVo m) {
		Connection conn = getConnection();
		
		MemberVo mo = md.selectMemberById(m, conn);
		int userCar = mo.getUserCar();
		int dirveCareer = mo.getDriveCareer();
		int accidentCount = mo.getAccidentCount();

		CompanyVo c = cc.selectCompanyById(mo.getCompanyId());
		int comFee = c.getDeductionFee();
		double dw = dc.selectDriveWeight(c.getCompanyId(),userCar,dirveCareer);
		double aw = ac.selectAccidentWeight(c.getCompanyId(),userCar,accidentCount);
		double total = comFee*aw+comFee*dw;
	
		close(conn);
		return total;
	}
	public MemberVo searchMember(MemberVo m) {
		Connection conn = getConnection();
		MemberVo mo = md.selectMemberById(m, conn);
		close(conn);
		return mo;
		
	}
	public int insertMember(MemberVo m) {
        Connection conn = getConnection();
		int result = md.insertMember(m, conn);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public MemberVo searchLogMember(MemberVo m) {
		Connection conn = getConnection();
		MemberVo mo = md.selectLogMember(m,conn);
		close(conn);
		return mo;
	}
	public List<MemberVo> selectMemberList(){
		Connection conn = getConnection();
		
		List<MemberVo> list = new MemberDao().selectMemberList(conn);
		close(conn);
		
		return list;
	}

	
	

}
