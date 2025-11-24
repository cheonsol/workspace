package com.semi.cm.service;

import static com.semi.cm.common.JdbcConnecntion.*;

import java.sql.Connection;
import java.util.List;

import com.semi.cm.model.dao.CompanyDao;
import com.semi.cm.model.vo.EmpVo;
import com.semi.cm.model.vo.MemberVo;

/**
 * 서비스
 */
public class CompanyService {

	private CompanyDao cd;
	
	
	
	public CompanyService() {
		super();
		this.cd = new CompanyDao();
	}



	/**
	 * 회원 가입(직원 추가)
	 * @param m
	 * @return
	 */
	public int signEmp(EmpVo e) {
		Connection conn = getConnection();
		
		String empDept = e.getEmpDept();
		String empJob = e.getEmpJob();
		//입력한 부서명과 직급명이 존재하는지 체크
		int deptCnt = cd.checkDept(empDept, conn);
		
		int jobCnt = cd.checkJob(empJob, conn);
		if (deptCnt == 0) {
			System.out.println("부서명을 확인해주세요.");
			return 0;
		}
		if (jobCnt == 0) {
			System.out.println("직급명을 확인해주세요.");
			return 0;
		}
		
		int result = cd.signEmp(e,conn);
		
		if (result > 0) {
			//입력한 부서와 직급의 사원수 증가
			int deptResult = cd.updateDeptCnt(empDept, conn);
			int jobResult = cd.updateJobCnt(empJob, conn);
			
			//부서와 직급 업데이트 실패시 롤백
			if (deptResult > 0 && jobResult > 0) {
				commit(conn);
				
			} else {
				rollback(conn);
				result = 0;
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	/**
	 * 로그인
	 * @param e
	 * @return
	 */
	public List<EmpVo> loginEmp(EmpVo e) {
		Connection conn = getConnection();
		List<EmpVo> result = cd.loginEmp(e,conn);
		
		close(conn);
		return result;
	}
	
	/**
	 * 회원 조회
	 * @param e
	 * @return
	 */
	public List<EmpVo> selectEmpList(EmpVo e) {
		Connection conn = getConnection();
		List<EmpVo> result = cd.selectEmpList(e,conn);
		
		
		close(conn);
		return result;
		
	}
	
	/**
	 * 회원 정보 수정
	 * @param e
	 * @return
	 */
	public int updateEmp(EmpVo e) {
		Connection conn = getConnection();
		//기존 정보 불러오기
		List<EmpVo> updateEmp = cd.selectUpdateEmp(e, conn);
		//수정 사항 값이 null 또는 공백인 경우 기존 값으로 대체 (xml에서 if test태그가 필요. nybatis에서만?)
		
		if (!(updateEmp.size() > 0)) {
			rollback(conn);
			close(conn);
			return 0;
		}
		int deptCnt  = 0; // 입력한 부서가 존재하는지 확인
		int jobCnt = 0; // 입력한 직급이 존재하는지 확인
		int deptResult = 0; // 입력한 부서 사원수 증가
		int jobResult = 0; // 입력한 직급 사원수 증가
		int oldDeptResult = 0; // 변경전 부서 사원수 차감
		int oldJobResult = 0; // 변경전 직급 사원수 차감
		
		
		//입력한 부서명과 직급명이 존재하는지 체크 / 입력한 직급 및 부서가 없을 경우 기존 데이터로 덮어 씌운다.
		if (e.getEmpDept().equals("") || e.getEmpDept().equals(null)) {
			e.setEmpDept(updateEmp.get(0).getEmpDept());
			deptCnt = 1;
			deptResult =1;
			oldDeptResult = 1;
			
		} else {
			
			String empDept = e.getEmpDept();
			deptCnt = cd.checkDept(empDept, conn);
			
			//입력한 부서와 직급의 사원수 증가
			deptResult = cd.updateDeptCnt(empDept, conn);				

			
			//기존 부서 사원수 차감
			String oldDept = updateEmp.get(0).getEmpDept();
			oldDeptResult= cd.updateOldDeptCnt(oldDept, conn);

		}
		
		if (e.getEmpJob().equals("") || e.getEmpJob().equals(null)) {
			e.setEmpJob(updateEmp.get(0).getEmpJob());
			jobCnt = 1;
			jobResult = 1;
			oldJobResult = 1;
		} else {
			
			String empJob = e.getEmpJob();
			jobCnt = cd.checkJob(empJob, conn);
			System.out.println(jobCnt);
			//입력한 부서와 직급의 사원수 증가
			jobResult = cd.updateJobCnt(empJob, conn);
			System.out.println(jobResult);
			//기존 직급 사원수 차감
			String oldJob = updateEmp.get(0).getEmpJob();
			oldJobResult = cd.updateOldJobCnt(oldJob, conn);
		}
		

		if (deptCnt == 0) {
			System.out.println("부서명을 확인해주세요.");
			rollback(conn);
			close(conn);
			return 0;
		}
		if (jobCnt == 0) {
			System.out.println("직급명을 확인해주세요.");
			rollback(conn);
			close(conn);
			return 0;
		}

		
		//세팅한 값 dao로 전송
		System.out.println(2);
		int result = cd.updateEmp(e,conn);
		System.out.println(3);
		if (result > 0) {

			//부서와 직급 업데이트 실패시 롤백
			if (deptResult > 0 && jobResult > 0 && oldDeptResult > 0 && oldJobResult > 0) {
				commit(conn);
				
			} else {
				rollback(conn);
				result = 0;
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	/**
	 * 회원 탈퇴
	 * @param e
	 * @return
	 */
	public int deleteEmp(EmpVo e) {
		int result =0;
		Connection conn = getConnection();
		//기존 정보 불러오기
		List<EmpVo> deleteEmp = cd.selectUpdateEmp(e, conn);
		e.setEmpNo(deleteEmp.get(0).getEmpNo());
		e.setEmpDept(deleteEmp.get(0).getEmpDept());
		e.setEmpJob(deleteEmp.get(0).getEmpJob());
		e.setEmpName(deleteEmp.get(0).getEmpName());
		e.setBirtDate(deleteEmp.get(0).getBirtDate());
		
		String deleteDept = deleteEmp.get(0).getEmpDept();
		String deleteJob = deleteEmp.get(0).getEmpJob();
		
		int oldDeptResult= cd.updateOldDeptCnt(deleteDept, conn);
		
		int oldJobResult = cd.updateOldJobCnt(deleteJob, conn);
		
		result = cd.deleteEmp(e,conn);
		
		int insertDelEmp = cd.insertDelEmp(e,conn);
		
		
		if (oldDeptResult > 0 && oldJobResult > 0 && result > 0 && insertDelEmp > 0) {
			commit(conn);
		} else {
			result = 0;
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public void rollback2() {
		
		Connection conn = getConnection();
		//commit(conn);
		rollback(conn);
	}
	
}
