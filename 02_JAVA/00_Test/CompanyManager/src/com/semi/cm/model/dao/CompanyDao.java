package com.semi.cm.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.cm.common.JdbcConnecntion;
import com.semi.cm.model.vo.EmpVo;
import com.semi.cm.model.vo.MemberVo;
/**
 * DAO
 */
public class CompanyDao {

	private Properties prop = new Properties();
	
	
	
	
	public CompanyDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/emp.xml"));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}



	/**
	 * 회원 가입(직원 추가)
	 * @param m
	 * @return
	 */
	public int signEmp(EmpVo ev, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("signEmp");
		//생일을 localDate에서 timeStamp로 형변환
		LocalDateTime localDateTime = ev.getBirtDate().atStartOfDay();
		Timestamp birtDate = Timestamp.valueOf(localDateTime);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ev.getEmpId());
			pstmt.setString(2, ev.getPassword());
			pstmt.setString(3, ev.getEmpDept());
			pstmt.setString(4, ev.getEmpJob());
			pstmt.setInt(5, ev.getSalary());
			pstmt.setTimestamp(6, birtDate);
			pstmt.setString(7, ev.getEmpName());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception	
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		
		return result;
	}
	
	/**
	 * 회원가입시 입력한 부서명이 존재하는지 체크
	 * @param empDept
	 * @return
	 */
	public int checkDept(String empDept,Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("checkDept");
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empDept);
			
			rset = pstmt.executeQuery();

			rset.next();
			result = rset.getInt("CNT");

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
			JdbcConnecntion.close(rset);
			
		}
		
		
		return result;
	}
	
	/**
	 * 회원가입시 입력한 직급명이 존재하는지 체크
	 * @param empJob
	 * @return
	 */
	public int checkJob(String empJob,Connection conn) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("checkJob");
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empJob);
			
			rset = pstmt.executeQuery();
			
			rset.next();
			result = rset.getInt("CNT");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
			JdbcConnecntion.close(rset);
			
		}
		
		
		return result;
	}
	/**
	 * 회원 가입시 입력한 부서 내 사원수 증가
	 * @param empDept
	 * @param conn
	 * @return
	 */
	
	public int updateDeptCnt(String empDept,Connection conn) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		
		String sql = prop.getProperty("updateDeptCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, val);
			pstmt.setString(1, empDept);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
		
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 회원정보 수정시 이전 부서 사원수 차감
	 * @param empDept
	 * @param conn
	 * @return
	 */
	public int updateOldDeptCnt(String empDept,Connection conn) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		
		String sql = prop.getProperty("updateOldDeptCnt");
		System.out.println("empDept"+empDept);
		try {
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, val);
			pstmt.setString(1, empDept);

			result = pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		return result;
	}
	
	
	/**
	 * 회원가입시 입력한 직급의 사원수 증가
	 * @param empJob
	 * @param conn
	 * @return
	 */
	public int updateJobCnt(String empJob, Connection conn) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		
		String sql = prop.getProperty("updateJobCnt");

		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, val);
			pstmt.setString(1, empJob);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 회원 정보 수정시 이전 직급 사원수 차감
	 * @param empJob
	 * @param conn
	 * @return
	 */
	public int updateOldJobCnt(String empJob, Connection conn) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		
		String sql = prop.getProperty("updateOldJobCnt");

		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, val);
			pstmt.setString(1, empJob);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 로그인.
	 * @param e
	 * @param conn
	 * @return
	 */
	//암호화 , 복호화는 마지막에 시도.
	public List<EmpVo> loginEmp(EmpVo ev, Connection conn) {
		List<EmpVo> result = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginEmp");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ev.getEmpId());
			pstmt.setString(2, ev.getPassword());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				EmpVo emp = new EmpVo();
				emp.setEmpId(rset.getString("EMP_ID"));
				emp.setEmpName(rset.getString("EMP_NAME"));
				emp.setEmpDept(rset.getString("EMP_DEPT"));
				emp.setEmpJob(rset.getString("EMP_JOB"));
				
				result.add(emp);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
			JdbcConnecntion.close(rset);
		}
		
		return result;
	}
	
	/**
	 * 회원 조회
	 * @param ev
	 * @param conn
	 * @return
	 */
	public List<EmpVo> selectEmpList(EmpVo ev, Connection conn) {
		List<EmpVo> result = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectEmpList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ev.getEmpName());
			pstmt.setString(2, ev.getEmpId());
			pstmt.setString(3, ev.getEmpDept());
			pstmt.setString(4, ev.getEmpJob());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				EmpVo emp = new EmpVo();
				emp.setEmpId(rset.getString("EMP_ID"));
				emp.setEmpName(rset.getString("EMP_NAME"));
				emp.setEmpDept(rset.getString("EMP_DEPT"));
				emp.setEmpJob(rset.getString("EMP_JOB"));
				emp.setBirtDate(rset.getTimestamp("BIRT_DATE").toLocalDateTime().toLocalDate());
				emp.setHireDate(rset.getTimestamp("HIRE_DATE").toLocalDateTime().toLocalDate());
				emp.setSalary(rset.getInt("SALARY"));
				
				result.add(emp);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			JdbcConnecntion.close(pstmt);
			JdbcConnecntion.close(rset);
		}
		
		
		
		return result;
	}
	
	/**
	 * 수정할 아이디의 정보 조회.
	 * xml if문 대체
	 * @param ev
	 * @param conn
	 * @return
	 */
	public List<EmpVo> selectUpdateEmp(EmpVo ev, Connection conn) {
		List<EmpVo> result = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectUpdateEmp");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ev.getEmpId());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				EmpVo emp = new EmpVo();
				emp.setEmpNo(rset.getInt("EMP_NO"));
				emp.setEmpId(rset.getString("EMP_ID"));
				emp.setEmpName(rset.getString("EMP_NAME"));
				emp.setEmpDept(rset.getString("EMP_DEPT"));
				emp.setEmpJob(rset.getString("EMP_JOB"));
				emp.setBirtDate(rset.getTimestamp("BIRT_DATE").toLocalDateTime().toLocalDate());
				emp.setSalary(rset.getInt("SALARY"));
				
				result.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
			JdbcConnecntion.close(rset);
		}
		
		return result;
	}
	
	/**
	 * 회원 정보 수정
	 * @param ev
	 * @param conn
	 * @return
	 */
	public int updateEmp(EmpVo ev,Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateEmp");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ev.getEmpDept());
			pstmt.setString(2, ev.getEmpJob());
			pstmt.setInt(3, ev.getSalary());
			pstmt.setString(4, ev.getEmpId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 회원 정보 탈퇴
	 * @param ev
	 * @param conn
	 * @return
	 */
	public int deleteEmp(EmpVo ev,Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteEmp");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ev.getEmpId());
			
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 탈퇴 회원 정보 임시 저장
	 * @param ev
	 * @param conn
	 * @return
	 */
	public int insertDelEmp(EmpVo ev, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertDelEmp");
		//생일을 localDate에서 timeStamp로 형변환
		LocalDateTime localDateTime = ev.getBirtDate().atStartOfDay();
		Timestamp birtDate = Timestamp.valueOf(localDateTime);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ev.getEmpNo());
			pstmt.setString(2, ev.getEmpId());
			pstmt.setString(3, ev.getEmpDept());	
			pstmt.setString(4, ev.getEmpJob());
			pstmt.setTimestamp(5, birtDate);
			pstmt.setString(6, ev.getEmpName());
			
			
			result = pstmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		return result;
	}
}
