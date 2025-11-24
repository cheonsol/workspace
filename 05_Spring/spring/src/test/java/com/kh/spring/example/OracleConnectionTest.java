package com.kh.spring.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
public class OracleConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testConnection() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("Oracle 연결 성공: " + conn.getMetaData().getURL());
        } catch (Exception e) {
            System.out.println("연결 실패: " + e.getMessage());
        }
    }
}