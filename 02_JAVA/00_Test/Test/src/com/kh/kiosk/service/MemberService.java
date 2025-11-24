package com.kh.kiosk.service;

import static com.kh.kiosk.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.kiosk.model.dao.MemberDao;
import com.kh.kiosk.model.vo.Member;

public class MemberService {

    private final MemberDao memberDao = new MemberDao();

    public Member selectByPhone(String phone) {
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

    public Member insertMember(String name, String phone) {
        Connection conn = null;
        try {
            conn = getConnection();
            Member m = new Member();
            m.setName(name);
            m.setPhone(phone);

            int result = memberDao.insertMember(m, conn); 
           
            if (result == 0) {
                rollback(conn);
                return null;
            }
            commit(conn);

            return memberDao.selectMemberByPhone(phone, conn);
        } catch (Exception e) {
            rollback(conn);
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }

    public long getPointBalance(long userNo) {
        Connection conn = null;
        try {
            conn = getConnection();
            return memberDao.selectPointBalance(userNo, conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
}