package com.kh.kiosk.controller;

import java.util.List;

import com.kh.kiosk.model.vo.Member;
import com.kh.kiosk.model.vo.PointHistory;
import com.kh.kiosk.service.PointService;

public class PointController {
    private PointService pointService = new PointService();
    
    public Member findMemberByPhone(String phone) {
        return pointService.findMemberByPhone(phone);
    }
    
    public long getPointBalanceByPhone(String phone) {
        return pointService.getPointBalanceByPhone(phone);
    }
    
    public List<PointHistory> getHistoryByUserNo(long userNo) {
        return pointService.getPointHistoryByUserNo(userNo);
    }
}