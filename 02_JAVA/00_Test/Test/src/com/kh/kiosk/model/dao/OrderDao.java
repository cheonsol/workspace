package com.kh.kiosk.model.dao;

import static com.kh.kiosk.common.JDBCTemplate.close;
import static com.kh.kiosk.common.JDBCTemplate.nextVal;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.kiosk.model.vo.Order;
import com.kh.kiosk.model.vo.OrderItem;

public class OrderDao {

    private Properties prop = new Properties();

    public OrderDao() {
        super();
        try {
            prop.loadFromXML(new FileInputStream("resources/queries/order-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // SEQ_ORDER_ID.NEXTVAL 사용
    public long insertOrder(Order o, Connection conn) {
        long orderId = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("insertOrder");

        try {
            orderId = nextVal(conn, "SEQ_ORDER_ID");
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderId);
            pstmt.setLong(2, o.getUserNo());
            pstmt.setString(3, o.getStatus());
            pstmt.setLong(4, o.getTotalAmount());
            int result = pstmt.executeUpdate();
            return result > 0 ? orderId : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return 0;
    }

    // SEQ_ORDER_ITEM_ID.NEXTVAL 사용 (ORDER_ID는 외부에서 셋팅 or SQL에서 CURRVAL 사용 가능)
    public int insertOrderItem(OrderItem item, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("insertOrderItem");

        try {
            long orderItemId = nextVal(conn, "SEQ_ORDER_ITEM_ID");
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderItemId);
            pstmt.setLong(2, item.getOrderId());
            pstmt.setLong(3, item.getProdId());
            pstmt.setInt(4, item.getQty());
            pstmt.setLong(5, item.getUnitPrice());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public List<OrderItem> selectOrderItemsByOrderId(long orderId, Connection conn) {
        List<OrderItem> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectOrderItemsByOrderId");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                OrderItem it = new OrderItem();
                it.setOrderItemId(rset.getLong("ORDER_ITEM_ID"));
                it.setOrderId(rset.getLong("ORDER_ID"));
                it.setProdId(rset.getLong("PROD_ID"));
                it.setQty(rset.getInt("QTY"));
                it.setUnitPrice(rset.getLong("UNIT_PRICE"));
                list.add(it);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
    }

    public int updateOrderStatus(long orderId, String status, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("updateOrderStatus");

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
    
    public Order selectOrderById(long orderId, Connection conn) {
        Order order = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectOrderById");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderId);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                order = new Order();
                order.setOrderId(rset.getLong("ORDER_ID"));
                order.setUserNo(rset.getLong("USERNO"));
                Timestamp ts = rset.getTimestamp("ORDER_DATE");
                order.setOrderDate(ts != null ? ts.toLocalDateTime() : null);
                order.setStatus(rset.getString("STATUS"));
                order.setTotalAmount(rset.getLong("TOTAL_AMOUNT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return order;
    }
}
