package com.kh.kiosk.service;

import static com.kh.kiosk.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.kh.kiosk.model.dao.OrderDao;
import com.kh.kiosk.model.dao.PaymentDao;
import com.kh.kiosk.model.dao.PointDao;
import com.kh.kiosk.model.dao.ProductDao;
import com.kh.kiosk.model.vo.Order;
import com.kh.kiosk.model.vo.OrderItem;
import com.kh.kiosk.model.vo.Payment;
import com.kh.kiosk.model.vo.PointHistory;
import com.kh.kiosk.model.vo.Product;
import com.kh.kiosk.view.KioskMenu.OrderResult;

public class OrderService {
    
    private final OrderDao orderDao = new OrderDao();
    private final ProductDao productDao = new ProductDao();
    private final PaymentDao paymentDao = new PaymentDao();
    private final PointDao pointDao = new PointDao();
    
    public OrderResult placeOrder(long userNo, Map<Long, Integer> cart, boolean approved) {
        Connection conn = null;
        try {
            conn = getConnection();
            
            // 1. 재고 체크 및 상품 정보 조회
            List<OrderItem> orderItems = new ArrayList<>();
            long totalAmount = 0;
            
            for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
                long prodId = entry.getKey();
                int qty = entry.getValue();
                
                Product product = productDao.selectProductById(prodId, conn);
                if (product == null) {
                    rollback(conn);
                    return null; // 상품 없음
                }
                
                if (product.getStockQty() < qty) {
                    rollback(conn);
                    return null; // 재고 부족
                }
                
                // 주문 항목 생성
                OrderItem item = new OrderItem();
                item.setProdId(prodId);
                item.setQty(qty);
                item.setUnitPrice(product.getPrice());
                orderItems.add(item);
                
                totalAmount += product.getPrice() * qty;
            }
            
            // 2. 주문 생성
            Order order = new Order(userNo, totalAmount);
            long orderId = orderDao.insertOrder(order, conn);
            if (orderId == 0) {
                rollback(conn);
                return null;
            }
            
            // 3. 주문 항목 저장
            for (OrderItem item : orderItems) {
                item.setOrderId(orderId);
                if (orderDao.insertOrderItem(item, conn) == 0) {
                    rollback(conn);
                    return null;
                }
            }
            
            // 4. 결제 처리
            Payment payment = new Payment();
            payment.setOrderId(orderId);
            payment.setAmount(totalAmount);
            
            String approvalCode = null;
            if (approved) {
                payment.setStatus("APPROVED");
                approvalCode = "APV" + UUID.randomUUID().toString().substring(0, 8);
                payment.setApprovalCode(approvalCode);
                
                // 재고 차감
                for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
                    long prodId = entry.getKey();
                    int qty = entry.getValue();
                    productDao.updateStock(prodId, -qty, conn);
                }
                
                // 포인트 적립 (1%)
                long pointsToAdd = totalAmount / 100;
                if (pointsToAdd > 0) {
                    PointHistory pointHistory = new PointHistory();
                    pointHistory.setUserNo(userNo);
                    pointHistory.setOrderId(orderId);
                    pointHistory.setPointDiff(pointsToAdd);
                    pointHistory.setMemo("주문 결제 포인트 적립 (1%)");
                    
                    pointDao.insertPointHistory(pointHistory, conn);
                    pointDao.updateMemberPoint(userNo, pointsToAdd, conn);
                }
                
            } else {
                payment.setStatus("FAILED");
            }
            
            if (paymentDao.insertPayment(payment, conn) == 0) {
                rollback(conn);
                return null;
            }
            
            if (approved) {
                commit(conn);
                return new OrderResult(orderId, approvalCode, totalAmount, orderItems);
            } else {
                rollback(conn);
                return null;
            }
            
        } catch (Exception e) {
            rollback(conn);
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
    
    public boolean cancelOrder(long orderId, String approvalCode, long userNo) {
        Connection conn = null;
        try {
            conn = getConnection();
            
            // 1. 결제 정보 확인
            Payment payment = paymentDao.selectPaymentByOrderId(orderId, conn);
            if (payment == null || !payment.getApprovalCode().equals(approvalCode) || 
                !"APPROVED".equals(payment.getStatus())) {
                rollback(conn);
                return false;
            }
            
            // 2. 주문 정보 확인 (사용자 일치)
            Order order = orderDao.selectOrderById(orderId, conn);
            if (order == null || order.getUserNo() != userNo || !"PLACED".equals(order.getStatus())) {
                rollback(conn);
                return false;
            }
            
            // 3. 주문 상태 변경
            orderDao.updateOrderStatus(orderId, "CANCELED", conn);
            
            // 4. 결제 상태 변경
            paymentDao.updatePaymentStatus(orderId, "REFUNDED", conn);
            
            // 5. 재고 복구
            List<OrderItem> orderItems = orderDao.selectOrderItemsByOrderId(orderId, conn);
            for (OrderItem item : orderItems) {
                productDao.updateStock(item.getProdId(), item.getQty(), conn);
            }
            
            // 6. 포인트 회수
            long pointsToDeduct = order.getTotalAmount() / 100;
            if (pointsToDeduct > 0) {
                PointHistory pointHistory = new PointHistory();
                pointHistory.setUserNo(userNo);
                pointHistory.setOrderId(orderId);
                pointHistory.setPointDiff(-pointsToDeduct);
                pointHistory.setMemo("주문 취소로 인한 포인트 회수");
                
                pointDao.insertPointHistory(pointHistory, conn);
                pointDao.updateMemberPoint(userNo, -pointsToDeduct, conn);
            }
            
            commit(conn);
            return true;
            
        } catch (Exception e) {
            rollback(conn);
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
}