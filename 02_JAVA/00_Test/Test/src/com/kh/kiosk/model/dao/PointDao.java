package com.kh.kiosk.model.dao;

import static com.kh.kiosk.common.JDBCTemplate.close;
import static com.kh.kiosk.common.JDBCTemplate.nextVal;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.kiosk.model.vo.PointHistory;

public class PointDao {

    private Properties prop = new Properties();

    public PointDao() {
        super();
        try {
            prop.loadFromXML(new FileInputStream("resources/queries/point-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // SEQ_PH_ID.NEXTVAL 사용
    public int insertPointHistory(PointHistory ph, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("insertPointHistory");

        try {
            long phId = nextVal(conn, "SEQ_PH_ID");
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, phId);
            pstmt.setLong(2, ph.getUserNo());
            if (ph.getOrderId() != null) pstmt.setLong(3, ph.getOrderId()); else pstmt.setNull(3, Types.NUMERIC);
            pstmt.setLong(4, ph.getPointDiff());
            pstmt.setString(5, ph.getMemo());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public List<PointHistory> selectPointHistoryByUser(long userNo, Connection conn) {
        List<PointHistory> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectPointHistoryByUser");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userNo);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                PointHistory ph = new PointHistory();
                ph.setPhId(rset.getLong("PH_ID"));
                ph.setUserNo(rset.getLong("USERNO"));
                long oid = rset.getLong("ORDER_ID");
                ph.setOrderId(rset.wasNull() ? null : oid);
                ph.setPointDiff(rset.getLong("POINT_DIFF"));
                Timestamp reg = rset.getTimestamp("REG_DATE");
                ph.setRegDate(reg != null ? reg.toLocalDateTime() : LocalDateTime.now());
                ph.setMemo(rset.getString("MEMO"));
                list.add(ph);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
    }
    
    public List<PointHistory> selectPointHistoryByUserNo(long userNo, Connection conn) {
        return selectPointHistoryByUser(userNo, conn);
    }
    
    public int updateMemberPoint(long userNo, long pointDiff, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("updateMemberPoint");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, pointDiff);
            pstmt.setLong(2, userNo);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
}
