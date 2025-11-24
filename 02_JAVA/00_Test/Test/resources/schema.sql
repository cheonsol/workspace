------------------------------------------------------------------------
-- 무인편의점 키오스크 데이터베이스 스키마
-- Oracle Database DDL Script
------------------------------------------------------------------------

-- 기존 테이블 및 시퀀스 삭제
DROP TABLE ORDER_ITEM CASCADE CONSTRAINTS;
DROP TABLE PAYMENT CASCADE CONSTRAINTS;
DROP TABLE POINT_HISTORY CASCADE CONSTRAINTS;
DROP TABLE ORDERS CASCADE CONSTRAINTS;
DROP TABLE PRODUCT CASCADE CONSTRAINTS;
DROP TABLE MEMBER CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_USERNO;
DROP SEQUENCE SEQ_PRODUCT_ID;
DROP SEQUENCE SEQ_ORDER_ID;
DROP SEQUENCE SEQ_ORDER_ITEM_ID;
DROP SEQUENCE SEQ_PAYMENT_ID;
DROP SEQUENCE SEQ_PH_ID;

------------------------------
-- MEMBER (회원: 이름, 전화번호, 포인트)
------------------------------
CREATE TABLE MEMBER (
  USERNO         NUMBER          PRIMARY KEY,
  NAME           VARCHAR2(50)    NOT NULL,          -- 이름
  PHONE          VARCHAR2(20)    NOT NULL UNIQUE,   -- 전화번호(간편 식별/적립)
  POINT_BALANCE  NUMBER          DEFAULT 0 NOT NULL,-- 보유 포인트 잔액
  JOIN_DATE      DATE            DEFAULT SYSDATE NOT NULL
);

COMMENT ON TABLE MEMBER IS '무인편의점 회원(포인트 적립 대상)';
COMMENT ON COLUMN MEMBER.POINT_BALANCE IS '현재 포인트 잔액';

------------------------------
-- PRODUCT (편의점 상품) - 바코드 필드 제거
------------------------------
CREATE TABLE PRODUCT (
  PROD_ID       NUMBER           PRIMARY KEY,
  NAME          VARCHAR2(100)   NOT NULL,
  CATEGORY      VARCHAR2(50),
  PRICE         NUMBER          NOT NULL CHECK (PRICE >= 0),  
  STOCK_QTY     NUMBER          DEFAULT 0 NOT NULL CHECK (STOCK_QTY >= 0),
  IS_PERISHABLE CHAR(1)         DEFAULT 'N' CHECK (IS_PERISHABLE IN ('Y','N')),
  EXPIRY_DATE   DATE,                                   -- 유통기한(선택)
  ON_SALE_YN    CHAR(1)         DEFAULT 'Y' CHECK (ON_SALE_YN IN ('Y','N'))
);

COMMENT ON TABLE PRODUCT IS '편의점 판매 상품';
COMMENT ON COLUMN PRODUCT.PRICE IS '단가 (KRW, 정수)';
COMMENT ON COLUMN PRODUCT.STOCK_QTY IS '재고 수량';

------------------------------
-- ORDERS (주문 헤더) - 비회원 불가
------------------------------
CREATE TABLE ORDERS (
  ORDER_ID      NUMBER           PRIMARY KEY,
  USERNO        NUMBER           NOT NULL
    REFERENCES MEMBER(USERNO),
  ORDER_DATE    DATE             DEFAULT SYSDATE NOT NULL,
  STATUS        VARCHAR2(20)     DEFAULT 'PLACED' NOT NULL
    CHECK (STATUS IN ('PLACED','CANCELED')),
  TOTAL_AMOUNT  NUMBER           NOT NULL CHECK (TOTAL_AMOUNT >= 0)
);

COMMENT ON TABLE ORDERS IS '주문 헤더(회원, 주문일시, 상태, 합계 스냅샷)';

------------------------------
-- ORDER_ITEM (주문 상세)
------------------------------
CREATE TABLE ORDER_ITEM (
  ORDER_ITEM_ID NUMBER           PRIMARY KEY,
  ORDER_ID      NUMBER           NOT NULL
    REFERENCES ORDERS(ORDER_ID) ON DELETE CASCADE,
  PROD_ID       NUMBER           NOT NULL
    REFERENCES PRODUCT(PROD_ID),
  QTY           NUMBER           NOT NULL CHECK (QTY > 0),
  UNIT_PRICE    NUMBER           NOT NULL CHECK (UNIT_PRICE >= 0)
);

COMMENT ON TABLE ORDER_ITEM IS '주문 항목(상품, 수량, 당시 단가 스냅샷)';

------------------------------
-- PAYMENT (결제)
------------------------------
CREATE TABLE PAYMENT (
  PAYMENT_ID    NUMBER           PRIMARY KEY,
  ORDER_ID      NUMBER           NOT NULL
    REFERENCES ORDERS(ORDER_ID),
  AMOUNT        NUMBER           NOT NULL CHECK (AMOUNT >= 0),
  STATUS        VARCHAR2(20)     NOT NULL
    CHECK (STATUS IN ('APPROVED','FAILED','REFUNDED')),
  APPROVAL_CODE VARCHAR2(64),            -- 승인번호(시뮬레이션)
  PAY_AT        DATE             DEFAULT SYSDATE NOT NULL,
  REFUND_AT     DATE
);

COMMENT ON TABLE PAYMENT IS '결제 기록(승인/실패/환불 상태 포함)';

------------------------------
-- POINT_HISTORY (포인트 이력)
------------------------------
CREATE TABLE POINT_HISTORY (
  PH_ID       NUMBER           PRIMARY KEY,
  USERNO      NUMBER           NOT NULL
    REFERENCES MEMBER(USERNO),
  ORDER_ID    NUMBER           NULL
    REFERENCES ORDERS(ORDER_ID),
  POINT_DIFF  NUMBER           NOT NULL,    -- +적립 / -회수
  REG_DATE    DATE             DEFAULT SYSDATE NOT NULL,
  MEMO        VARCHAR2(200)
);

COMMENT ON TABLE POINT_HISTORY IS '포인트 적립/회수 이력';
COMMENT ON COLUMN POINT_HISTORY.POINT_DIFF IS '+적립, -차감 (정수 포인트)';

------------------------------
-- 시퀀스 생성
------------------------------
CREATE SEQUENCE SEQ_USERNO         START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SEQ_PRODUCT_ID     START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SEQ_ORDER_ID       START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SEQ_ORDER_ITEM_ID  START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SEQ_PAYMENT_ID     START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SEQ_PH_ID          START WITH 1 INCREMENT BY 1 NOCACHE;

-- 스키마 생성 완료 메시지
SELECT 'Database schema created successfully!' AS STATUS FROM DUAL;