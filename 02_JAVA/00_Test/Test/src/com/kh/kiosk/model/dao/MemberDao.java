package com.kh.kiosk.model.dao;

import static com.kh.kiosk.common.JDBCTemplate.close;
import static com.kh.kiosk.common.JDBCTemplate.nextVal;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

import com.kh.kiosk.model.vo.Member;

public class MemberDao {

    private Properties prop = new Properties();

    public MemberDao() {
        super();
        try {
			prop.loadFromXML(new FileInputStream("resources/queries/member-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Member selectMemberByPhone(String phone, Connection conn) {
        Member m = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectMemberByPhone"); // PHONE=?

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                m = new Member();
                m.setUserNo(rset.getLong("USERNO"));
                m.setName(rset.getString("NAME"));
                m.setPhone(rset.getString("PHONE"));
                m.setPointBalance(rset.getLong("POINT_BALANCE"));
                Timestamp ts = rset.getTimestamp("JOIN_DATE");
                m.setJoinDate(ts != null ? ts.toLocalDateTime() : LocalDateTime.now());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return m;
    }

    // 시퀀스는 SQL에서 SEQ_USERNO.NEXTVAL 사용
    public int insertMember(Member m, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("insertMember");

        try {
            long userNo = nextVal(conn, "SEQ_USERNO");
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userNo);
            pstmt.setString(2, m.getName());
            pstmt.setString(3, m.getPhone());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int updatePointBalance(long userNo, long diff, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("updatePointBalance"); // +? WHERE USERNO=?

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, diff);
            pstmt.setLong(2, userNo);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public long selectPointBalance(long userNo, Connection conn) {
        long balance = 0L;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("getPointBalance"); // WHERE USERNO=?

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userNo);
            rset = pstmt.executeQuery();
            if (rset.next()) balance = rset.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return balance;
    }
}
