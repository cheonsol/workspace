package com.kh.Insurance.model.Dao;

import static com.kh.Insurance.common.Template.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


import com.kh.Insurance.model.Amount;

public class AmountDao {
	private Properties prop = new Properties();
	public AmountDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int insertAmount(Amount a, Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAmount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getUserId());
			pstmt.setInt(2, a.getPayFee());
			pstmt.setInt(3, a.getInputFee());
			pstmt.setInt(4, a.getRestFee());
			pstmt.setInt(5, a.getFailCount());
			pstmt.setString(6, a.getStatus());

			
			re = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;

	}
	public int inputFee(Amount a, Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateFee");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getInputFee());
			pstmt.setInt(2, a.getUserId());
			

			
			re = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;

	}
	public int searchRestFee(Amount a, Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectRestFee");
		
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getUserId());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				a.setRestFee(rset.getInt("REST_FEE"));
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return a.getRestFee();
	}
	public int updateRestFee(Amount a, int fee, Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateRestFee");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fee);
			pstmt.setInt(2, a.getUserId());
			

			
			re = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}
	public int insertCount(Amount a, Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getFailCount());
			pstmt.setInt(2, a.getUserId());
			

			
			re = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;

	}
	public int selectCount(Amount a, Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectCount");
		
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getUserId());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				a.setFailCount(rset.getInt("FAIL_COUNT"));
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return a.getFailCount();
	
    }
	public int selectPayFee(Amount a, Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectPayFee");
		
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,a.getUserId());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				a.setPayFee(rset.getInt("PAY_FEE"));
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return a.getPayFee();
	
	}
	public int selectInputFee(Amount a, Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectInputFee");
		
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getUserId());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				a.setInputFee(rset.getInt("INPUT_FEE"));
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return a.getInputFee();
	
	}
	public int updateStatus(Amount a,Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateStatus");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getUserId());
			

			
			re = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}
	public boolean existAmount(Amount a, Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("existAmount");
		
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getUserId());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				return rset.getInt(1)>0;
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return false;
	}
	public int fixAmount(Amount a, Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateFixAmount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getInputFee());
			pstmt.setInt(2, a.getUserId());
			

			
			re = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}
	
		


}