package com.kh.kiosk.controller;

import java.util.Map;

import com.kh.kiosk.service.OrderService;
import com.kh.kiosk.view.KioskMenu.OrderResult;

public class OrderController {
    private OrderService orderService = new OrderService();
    
    public OrderResult placeOrder(long userNo, Map<Long, Integer> cart, boolean approved) {
        return orderService.placeOrder(userNo, cart, approved);
    }
    
    public boolean cancelOrder(long orderId, String approvalCode, long userNo) {
        return orderService.cancelOrder(orderId, approvalCode, userNo);
    }
}