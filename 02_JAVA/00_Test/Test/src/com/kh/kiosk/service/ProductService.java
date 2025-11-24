package com.kh.kiosk.service;

import static com.kh.kiosk.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.kiosk.model.dao.ProductDao;
import com.kh.kiosk.model.vo.Product;

public class ProductService {
    
    private final ProductDao productDao = new ProductDao();
    
    public int insertProduct(Product product) {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = productDao.insertProduct(product, conn);
            
            if (result > 0) {
                commit(conn);
            } else {
                rollback(conn);
            }
            
            return result;
        } catch (Exception e) {
            rollback(conn);
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
    
    public int updateProductBasic(long prodId, String name, String category, Long price, String onSaleYn) {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = productDao.updateProductBasic(prodId, name, category, price, onSaleYn, conn);
            
            if (result > 0) {
                commit(conn);
            } else {
                rollback(conn);
            }
            
            return result;
        } catch (Exception e) {
            rollback(conn);
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
    
    public int addStock(long prodId, int qty) {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = productDao.addStock(prodId, qty, conn);
            
            if (result > 0) {
                commit(conn);
            } else {
                rollback(conn);
            }
            
            return result;
        } catch (Exception e) {
            rollback(conn);
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
    
    public List<Product> selectAllProducts() {
        Connection conn = null;
        try {
            conn = getConnection();
            return productDao.selectAllProducts(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
    
    public Product selectProductById(long prodId) {
        Connection conn = null;
        try {
            conn = getConnection();
            return productDao.selectProductById(prodId, conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
    
    public int updateStock(long prodId, int qty) {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = productDao.updateStock(prodId, qty, conn);
            
            if (result > 0) {
                commit(conn);
            } else {
                rollback(conn);
            }
            
            return result;
        } catch (Exception e) {
            rollback(conn);
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
}