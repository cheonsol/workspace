package com.kh.Insurance.service;

import static com.kh.Insurance.common.Template.close;
import static com.kh.Insurance.common.Template.commit;
import static com.kh.Insurance.common.Template.getConnection;
import static com.kh.Insurance.common.Template.rollback;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.util.List;

import com.kh.Insurance.model.CompanyVo;
import com.kh.Insurance.model.Dao.CompanyDao;
import com.kh.Insurance.model.Dao.MemberDao;

public class CompanyService {
	private CompanyDao cd = new CompanyDao();

	public CompanyService() {
		super();
	}
	public int insertCompany(CompanyVo c) {
		Connection conn = getConnection();
        int result = cd.insertCompany(c, conn);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public List<CompanyVo> selectCompanyList(){
		Connection conn = getConnection();
		
		List<CompanyVo> list = cd.selectCompanyList(conn);
		close(conn);
		
		return list;
	}
	public CompanyVo searchCompanyIdByName(CompanyVo c) {
		Connection conn = getConnection();
		
		CompanyVo co = cd.searchCompanyIdByName(c,conn);
		close(conn);
		return co;
	}
	public CompanyVo selectCompanyById(int id) {
		Connection conn = getConnection();
		
		CompanyVo c = cd.selectCompanyById(id, conn);
		return c;
	}

}
