package com.kh.kiosk.service;

import static com.kh.kiosk.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.kiosk.model.dao.MemberDao;
import com.kh.kiosk.model.dao.PointDao;
import com.kh.kiosk.model.vo.Member;
import com.kh.kiosk.model.vo.PointHistory;

public class PointService {
    
    private final MemberDao memberDao = new MemberDao();
    private final PointDao pointDao = new PointDao();
    
    public Member findMemberByPhone(String phone) {
        Connection conn = null;
        try {
            conn = getConnection();
            return memberDao.selectMemberByPhone(phone, conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
    
    public long getPointBalanceByPhone(String phone) {
        Connection conn = null;
        try {
            conn = getConnection();
            Member member = memberDao.selectMemberByPhone(phone, conn);
            if (member == null) {
                return 0;
            }
            return member.getPointBalance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
    
    public List<PointHistory> getPointHistoryByUserNo(long userNo) {
        Connection conn = null;
        try {
            conn = getConnection();
            return pointDao.selectPointHistoryByUserNo(userNo, conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
}