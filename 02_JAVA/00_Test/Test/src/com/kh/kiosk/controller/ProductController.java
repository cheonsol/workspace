package com.kh.kiosk.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.kh.kiosk.model.vo.Product;
import com.kh.kiosk.service.ProductService;

public class ProductController {
    private ProductService productService = new ProductService();
    
    public int add(String name, String category, long price, String isPerishable, LocalDateTime expiryDate, String onSaleYn) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setIsPerishable(isPerishable);
        product.setExpiryDate(expiryDate);
        product.setOnSaleYn(onSaleYn);
        
        return productService.insertProduct(product);
    }
    
    public int updateBasic(long prodId, String name, String category, Long price, String onSaleYn) {
        return productService.updateProductBasic(prodId, name, category, price, onSaleYn);
    }
    
    public int addStock(long prodId, int qty) {
        return productService.addStock(prodId, qty);
    }
    
    public List<Product> listAll() {
        return productService.selectAllProducts();
    }
}