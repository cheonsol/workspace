package com.kh.kiosk.model.dao;

import static com.kh.kiosk.common.JDBCTemplate.close;
import static com.kh.kiosk.common.JDBCTemplate.nextVal;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.kh.kiosk.model.vo.Payment;

public class PaymentDao {

    private Properties prop = new Properties();

    public PaymentDao() {
        super();
        try {
            prop.loadFromXML(new FileInputStream("resources/queries/payment-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // SEQ_PAYMENT_ID.NEXTVAL 사용
    public int insertPayment(Payment p, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("insertPayment");

        try {
            long paymentId = nextVal(conn, "SEQ_PAYMENT_ID");
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, paymentId);
            pstmt.setLong(2, p.getOrderId());
            pstmt.setLong(3, p.getAmount());
            pstmt.setString(4, p.getStatus());
            pstmt.setString(5, p.getApprovalCode());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public Payment selectPaymentByOrderId(long orderId, Connection conn) {
        Payment pay = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectPaymentByOrderId");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderId);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                pay = new Payment();
                pay.setPaymentId(rset.getLong("PAYMENT_ID"));
                pay.setOrderId(rset.getLong("ORDER_ID"));
                pay.setAmount(rset.getLong("AMOUNT"));
                pay.setStatus(rset.getString("STATUS"));
                pay.setApprovalCode(rset.getString("APPROVAL_CODE"));
                Timestamp payAt = rset.getTimestamp("PAY_AT");
                Timestamp refundAt = rset.getTimestamp("REFUND_AT");
                pay.setPayAt(payAt != null ? payAt.toLocalDateTime() : null);
                pay.setRefundAt(refundAt != null ? refundAt.toLocalDateTime() : null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return pay;
    }

    public int refundPayment(long orderId, String approvalCode, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("refundPayment");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderId);
            pstmt.setString(2, approvalCode);
            result = pstmt.executeUpdate(); // 0이면 조건 불일치(환불불가)
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
    
    public int updatePaymentStatus(long orderId, String status, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("updatePaymentStatus");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setLong(2, orderId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
}
