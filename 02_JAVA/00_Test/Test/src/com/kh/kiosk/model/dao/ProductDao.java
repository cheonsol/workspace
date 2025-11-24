package com.kh.kiosk.model.dao;

import static com.kh.kiosk.common.JDBCTemplate.close;
import static com.kh.kiosk.common.JDBCTemplate.nextVal;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.kiosk.model.vo.Product;

public class ProductDao {

    private Properties prop = new Properties();

    public ProductDao() {
        super();
        try {
            prop.loadFromXML(new FileInputStream("resources/queries/product-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // SEQ_PRODUCT_ID.NEXTVAL 사용
    public int insertProduct(Product p, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("insertProduct");

        try {
            long prodId = nextVal(conn, "SEQ_PRODUCT_ID");
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, prodId);
            pstmt.setString(2, p.getName());
            pstmt.setString(3, p.getCategory());
            pstmt.setLong(4, p.getPrice());
            pstmt.setString(5, p.getIsPerishable());
            if (p.getExpiryDate() != null) pstmt.setTimestamp(6, Timestamp.valueOf(p.getExpiryDate()));
            else pstmt.setNull(6, Types.TIMESTAMP);
            pstmt.setString(7, p.getOnSaleYn());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    // NULL이면 기존값 유지: NVL(?, 컬럼) 패턴 사용(SQL에서 처리)
    public int updateProductBasic(long prodId, String name, String category, Long price, String onSaleYn, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("updateProductBasic"); 
        // 예: UPDATE PRODUCT
        //     SET NAME=NVL(?, NAME), CATEGORY=NVL(?, CATEGORY),
        //         PRICE=NVL(?, PRICE), ON_SALE_YN=NVL(?, ON_SALE_YN)
        //     WHERE PROD_ID=?

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            if (price != null) pstmt.setLong(3, price); else pstmt.setNull(3, Types.NUMERIC);
            pstmt.setString(4, onSaleYn);
            pstmt.setLong(5, prodId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int addStock(long prodId, int qty, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("addStock");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, qty);
            pstmt.setLong(2, prodId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int minusStockIfEnough(long prodId, int qty, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("minusStockIfEnough");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, qty);
            pstmt.setLong(2, prodId);
            pstmt.setInt(3, qty);
            result = pstmt.executeUpdate(); // 0이면 재고부족/경합실패
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public Product selectProductById(long prodId, Connection conn) {
        Product p = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectProductById");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, prodId);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                p = new Product();
                p.setProdId(rset.getLong("PROD_ID"));
                p.setName(rset.getString("NAME"));
                p.setCategory(rset.getString("CATEGORY"));
                p.setPrice(rset.getLong("PRICE"));
                p.setStockQty(rset.getInt("STOCK_QTY"));
                p.setIsPerishable(rset.getString("IS_PERISHABLE"));
                Timestamp ex = rset.getTimestamp("EXPIRY_DATE");
                p.setExpiryDate(ex != null ? ex.toLocalDateTime() : null);
                p.setOnSaleYn(rset.getString("ON_SALE_YN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return p;
    }

    public List<Product> selectProductAll(Connection conn) {
        List<Product> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectProductAll");

        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                Product p = new Product();
                p.setProdId(rset.getLong("PROD_ID"));
                p.setName(rset.getString("NAME"));
                p.setCategory(rset.getString("CATEGORY"));
                p.setPrice(rset.getLong("PRICE"));
                p.setStockQty(rset.getInt("STOCK_QTY"));
                p.setIsPerishable(rset.getString("IS_PERISHABLE"));
                Timestamp ex = rset.getTimestamp("EXPIRY_DATE");
                p.setExpiryDate(ex != null ? ex.toLocalDateTime() : null);
                p.setOnSaleYn(rset.getString("ON_SALE_YN"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
    }
    
    public List<Product> selectAllProducts(Connection conn) {
        return selectProductAll(conn);
    }
    
    public int updateStock(long prodId, int qty, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateStock");
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, qty);
            pstmt.setLong(2, prodId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
}
