/*
    DDL(Data Definition Language) : 데이터 정의어
    데이터베이스에서 사용하는 SQL명령어 중 데이터를 저장할 객체의 구조를 정의하거나 변경하는 역할을 함.
    즉, 실제 데이터값이 아닌 규칙 자체를 정의하는 언어.
    
    오라클에서 객체(구조) : 테이블, 뷰, 시퀀스, 인덱스, 패키지, 트리거, 프로시저, 함수, 사용자 등...
    
    객체를 생성(CREATE), 변경(ALTER), 삭제(DROP)하는 구문
*/

/*
    <CREATE>
    객체를 새로 생성하는 구문
    
    1. 테이블 생성
    - 테이블 : 행과 열로 구성되는 가장 기본적인 데이터베이스 객체
              모든 데이터들은 테이블을 통해서 저장된다.
              (DBMS용어 중 하나로, 데이터를 일종의 표 형태로 표현한 것)
              
    [표현식]
    CREATE TABLE 테이블명(
        컬럼명 자료형(크기),
        컬럼명 자료형(크기),
        컬럼명 자료형,
        ...
    )
    
    *자료형
    - 숫자(NUMBER)
    - 문자(CHAR(바이트크기) | VARCHAR2(바이트크기)) -> 반드시 크기를 지정해야 함
        - CHAR : 최대 2000바이트까지 지정가능 / 고정길이(고정된 글자수의 데이터가 담길 경우)
        - VARCHAR2 : 최대 4000바이트까지 지정가능 / 가변길이(몇글자의 데이터가 들어올지 확신할 수 없는 경우)
    - 날짜(DATE)
*/

--회원에 대한 데이터를 담기위한 테이블 MEMBER 생성
CREATE TABLE MEMBER(
    MEM_NO NUMBER,
    MEM_ID VARCHAR2(30), --user01, test1234
    MEM_PWD VARCHAR2(30),
    MEM_NAME VARCHAR2(21),
    GENDER CHAR(3),
    PHONE VARCHAR2(13),
    EMAIL VARCHAR2(50),
    CREATE_AT DATE
);

SELECT * FROM MEMBER;
-----------------------------------------------------------------------
/*
    2. 컬럼에 주석달기(컬럼에대한 간단한 설명)
    
    [표현법]
    COMMENT ON COLUMN 테이블명.컬럼명 IS '주석내용';
*/

COMMENT ON COLUMN MEMBER.MEM_NO IS '회원번호';
COMMENT ON COLUMN MEMBER.MEM_ID IS '회원아이디';
COMMENT ON COLUMN MEMBER.MEM_PWD IS '회원비밀번호';
COMMENT ON COLUMN MEMBER.MEM_NAME IS '회원이름';
COMMENT ON COLUMN MEMBER.GENDER IS '성별(남/여)';
COMMENT ON COLUMN MEMBER.PHONE IS '전화번호';
COMMENT ON COLUMN MEMBER.EMAIL IS '이메일';
COMMENT ON COLUMN MEMBER.CREATE_AT IS '회원가입일';

INSERT INTO MEMBER
VALUES(1, 'USER01', 'PASS01', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', '25/07/30');

SELECT * FROM MEMBER;

INSERT INTO MEMBER
VALUES(1, 'USER01', 'PASS01', NULL, NULL, '010-1111-2222', NULL, NULL);

-----------------------------------------------------------------
/*
    <제약조건>
    - 원하는 데이터값(유효한 형식의 값)만 유지하기 위해서 특정 컬럼에 설정하는 제약
    - 데이터 무결성 보장을 목적으로 함 -> 데이터베이스에 저장된 데이터가 정확하고 일관되며 신뢰할 수 있응 상태를 유지.

    종류 : NOT NULL, UNIQUE, CHECK, PRIMARY KEY, FOREIGN KEY...
*/


/*
    *NOT NULL
    해당 컬럼에 반드시 값이 존재해야만 하는 경우(즉, 절대 NULL이 들어오면 안되는 경우)
    삽입/수정시 NULL값을 허용하지 않도록 제한
    
    <제약조건 부여 방식>
    1. 컬럼레벨 제약조건부여
    CREATE TABLE MEMBER(
        NAME VARCHAR2(30) PRIMARY KEY
    )
    
    2. 테이블레벨 제약조건부여 
    CREATE TABLE MEMBER(
        NAME VARCHAR2(30),
        CONSTRAINT MEM_PK PRIMARY KEY(NAME)
    )
*/

DROP TABLE MEMBER;

CREATE TABLE MEMBER(
    MEM_NO NUMBER NOT NULL,
    MEM_ID VARCHAR2(30) NOT NULL, --user01, test1234
    MEM_PWD VARCHAR2(30) NOT NULL,
    MEM_NAME VARCHAR2(21) NOT NULL,
    GENDER CHAR(3),
    PHONE VARCHAR2(13),
    EMAIL VARCHAR2(50),
    CREATE_AT DATE
);

INSERT INTO MEMBER
VALUES(1, 'USER01', 'PASS01', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', '25/07/30');

SELECT * FROM MEMBER;

INSERT INTO MEMBER
VALUES(1, 'USER01', 'PASS01', '홍길순', NULL, NULL, NULL, NULL);

INSERT INTO MEMBER
VALUES(1, NULL, 'PASS01', '홍길순', NULL, NULL, NULL, NULL);
--아이디가 중복되었음에도 불구하고 잘 추가가 됨.

-------------------------------------------------------------------
/*
    *UNIQUE 제약조건
    해당 컬럼에 중복값이 들어가서는 안될 경우 사용한다.
    컬럼값에 중복값을 제한하는 제약조건.
    삽입/수정시 기존에 있는 데이터값 중 중복값이 있을 경우 오류를 발생시킴.
*/

DROP TABLE MEMBER;

CREATE TABLE MEMBER(
    MEM_NO NUMBER NOT NULL,
    MEM_ID VARCHAR2(30) NOT NULL UNIQUE, --user01, test1234
    MEM_PWD VARCHAR2(30) NOT NULL,
    MEM_NAME VARCHAR2(21) NOT NULL,
    GENDER CHAR(3),
    PHONE VARCHAR2(13),
    EMAIL VARCHAR2(50),
    CREATE_AT DATE,
    UNIQUE(EMAIL) --테이블레벨 부여방식
);

INSERT INTO MEMBER
VALUES(1, 'USER01', 'PASS01', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', '25/07/30');

SELECT * FROM MEMBER;

INSERT INTO MEMBER
VALUES(1, 'USER01', 'PASS01', '홍길순', NULL, NULL, NULL, NULL);
--UNIQUE제약조건에 위배되었으므로 INSERT 실패
--> 오류구문을 제약조건명으로 알려준다.
--> 쉽게 어떤 제약조건이 위반인지 알기 어렵다.
--> 제약조건 부여시 제약조건명을 지정해주면 오류구문에서 제약조건 확인이 쉽다.

------------------------------------------------------------------------------
/*
    제약조건 부여시 함께 제약조건명을 부여
    > 컬럼레벨방식
    CREATE TABLE 테이블명(
        컬럼명 자료형 CONSTRAINT 제약조건명 제약조건
    )
    
     > 테이블레벨방식
    CREATE TABLE 테이블명(
        컬럼명 자료형,
        CONSTRAINT 제약조건명 제약조건(컬럼명)
    )
*/

DROP TABLE MEMBER;

CREATE TABLE MEMBER(
    MEM_NO NUMBER CONSTRAINT MEMNO_NT NOT NULL,
    MEM_ID VARCHAR2(30) CONSTRAINT MEMID_NT NOT NULL CONSTRAINT MEMID_UQ UNIQUE, --user01, test1234
    MEM_PWD VARCHAR2(30) CONSTRAINT MEMPWD_NT NOT NULL,
    MEM_NAME VARCHAR2(21) CONSTRAINT MEMNAME_NT NOT NULL,
    GENDER CHAR(3),
    PHONE VARCHAR2(13),
    EMAIL VARCHAR2(50),
    CREATE_AT DATE,
    CONSTRAINT EMAIL_UQ UNIQUE(EMAIL) --테이블레벨 부여방식
);

INSERT INTO MEMBER
VALUES(1, 'USER01', 'PASS01', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', '25/07/30');

SELECT * FROM MEMBER;

INSERT INTO MEMBER
VALUES(2, 'USER02', 'PASS02', '홍길순', '왜', NULL, NULL, NULL);

-------------------------------------------------------------------
/*
    *CHECK(조건식)
    해당 컬럼에 들어올 수 있는 값에 대한 조건을 제시할 수 있음.
    데이터를 추가/수정시 조건식을 만족해야 정상적인 실행이 가능함.
*/

DROP TABLE MEMBER;

CREATE TABLE MEMBER(
    MEM_NO NUMBER CONSTRAINT MEMNO_NT NOT NULL,
    MEM_ID VARCHAR2(30) CONSTRAINT MEMID_NT NOT NULL CONSTRAINT MEMID_UQ UNIQUE, --user01, test1234
    MEM_PWD VARCHAR2(30) CONSTRAINT MEMPWD_NT NOT NULL,
    MEM_NAME VARCHAR2(21) CONSTRAINT MEMNAME_NT NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    PHONE VARCHAR2(13),
    EMAIL VARCHAR2(50),
    CREATE_AT DATE,
    CONSTRAINT EMAIL_UQ UNIQUE(EMAIL)
    -- CHECK(GENDER IN ('남', '여')) 테이블레벨 부여방식
);

INSERT INTO MEMBER
VALUES(1, 'USER01', 'PASS01', '홍길동', NULL, '010-1111-2222', 'aaa@naver.com', '25/07/30');

SELECT * FROM MEMBER;

INSERT INTO MEMBER
VALUES(1, 'USER02', 'PASS02', '홍길동', '여', '010-1111-2222', 'bbb@naver.com', '25/07/30');
--CHECK제약조건 때문에 '남', '여'이외에 성별입력시 예외가 발생한다.
--단, NULL은 값이 없다는 뜻이기 때문에 CHECK제약조건과는 무관하다.

----------------------------------------------------------------------------
/*
    RPIMARY KEY(기본키) 제약조건
    테이블에서 각 행(ROW)를 식별하기위해 사용될 컬럼에 부여하는 제약조건(식별자 역할)
    
    EX) 회원번호, 학번, 부서코드, 주민등록번호, 택배운송장번호...
    기본키 -> 중복금지(UNIQUE) + NULL금지(NOT NULL)
    식별, 무결, 연결
    
    한테이블에 오직 한개만 설정 가능
*/

DROP TABLE MEMBER;

CREATE TABLE MEMBER(
    MEM_NO NUMBER CONSTRAINT MEMNO_PK PRIMARY KEY ,
    MEM_ID VARCHAR2(30) CONSTRAINT MEMID_NT NOT NULL CONSTRAINT MEMID_UQ UNIQUE, --user01, test1234
    MEM_PWD VARCHAR2(30) CONSTRAINT MEMPWD_NT NOT NULL,
    MEM_NAME VARCHAR2(21) CONSTRAINT MEMNAME_NT NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    PHONE VARCHAR2(13),
    EMAIL VARCHAR2(50),
    CREATE_AT DATE,
    CONSTRAINT EMAIL_UQ UNIQUE(EMAIL)
    -- CONSTRAINT MEMNO_PK PRIMARY KEY(MEM_NO) 테이블레벨 부여방식
);

INSERT INTO MEMBER
VALUES(1, 'USER01', 'PASS01', '홍길동', NULL, '010-1111-2222', 'aaa@naver.com', '25/07/30');

INSERT INTO MEMBER
VALUES(1, 'USER02', 'PASS02', '홍길순', NULL, '010-1111-3333', 'aaa2@naver.com', '25/07/30');
--기본키에 중복된 값을 담으려고할 때(UNIQUE제약조건 위반)

INSERT INTO MEMBER
VALUES(NULL, 'USER02', 'PASS02', '홍길순', NULL, '010-1111-3333', 'aaa2@naver.com', '25/07/30');
--기본키에 NULL 값을 담으려고할 때(NOT NULL제약조건 위반)

INSERT INTO MEMBER
VALUES(2, 'USER02', 'PASS02', '홍길순', NULL, '010-1111-3333', 'aaa2@naver.com', '25/07/30');


INSERT INTO MEMBER
VALUES(3, 'USER03', 'PASS03', '홍길삼', NULL, '010-1111-3333', 'aaa3@naver.com', '25/07/30');


INSERT INTO MEMBER
VALUES(4, 'USER04', 'PASS04', '홍길사', NULL, '010-1111-3333', 'aaa4@naver.com', '25/07/30');

--복합키: 두개의 컬럼을 동시에 하나의 PRIMARY KEY로 지정하는 것
CREATE TABLE TB_LIKE(
    MEM_NO NUMBER,
    PRODUCT_NAME VARCHAR2(30),
    LIKE_DATE DATE,
    PRIMARY KEY(MEM_NO, PRODUCT_NAME)
);

--회원 4명(1,2,3,4번)
--상품 2개(자전거A, 자전거B)

INSERT INTO TB_LIKE VALUES(1, '자전거A', SYSDATE);
INSERT INTO TB_LIKE VALUES(1, '자전거B', SYSDATE);
INSERT INTO TB_LIKE VALUES(1, '자전거A', SYSDATE);

---------------------------------------------------------------
--회원 등급에 대한 데이터를 보관하는 테이블
CREATE TABLE MEM_GRADE(
    GRADE_CODE NUMBER PRIMARY KEY,
    GRADE_NAME VARCHAR2(30) NOT NULL
);

INSERT INTO MEM_GRADE VALUES(10, '일반회원');
INSERT INTO MEM_GRADE VALUES(20, '우수회원');
INSERT INTO MEM_GRADE VALUES(30, '특별회원');
INSERT INTO MEM_GRADE VALUES(40, 'VIP회원');

SELECT * FROM MEM_GRADE;

CREATE TABLE MEM(
    MEM_NO NUMBER PRIMARY KEY ,
    MEM_ID VARCHAR2(30) NOT NULL UNIQUE, --user01, test1234
    MEM_PWD VARCHAR2(30) NOT NULL,
    MEM_NAME VARCHAR2(21) NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    PHONE VARCHAR2(13),
    EMAIL VARCHAR2(50),
    CREATE_AT DATE,
    GRADE_ID NUMBER
);

INSERT INTO MEM
VALUES(1, 'USER1', 'PASS1', '홍길동', NULL, NULL, NULL, '25/01/01', NULL);

INSERT INTO MEM
VALUES(2, 'USER2', 'PASS2', '홍길순', NULL, NULL, NULL, '25/01/01', 10);

INSERT INTO MEM
VALUES(3, 'USER3', 'PASS3', '홍길삼', NULL, NULL, NULL, '25/01/01', 20);

INSERT INTO MEM
VALUES(4, 'USER4', 'PASS4', '홍길사', NULL, NULL, NULL, '25/01/01', 40);

INSERT INTO MEM
VALUES(5, 'USER5', 'PASS5', '홍길오', NULL, NULL, NULL, '25/01/01', 50);

/*
    FOREIGN_KEY(외래키) 제약조건
    다른 테이블의 기본키(PK)또는 고유키(UNIQUE)를 참조하도록 설정하는 제약조건
    -> 다른 테이블을 참조한다고 표현
    -> 대부분의 경우는 FOREIGN KEY를 통해서 테이블간의 관계가 활성화된다.
    
    >컬럼레벨방식
    컬럼명 자료형 REFERENCES 참조할테이블명[참조할컬럼명]
    
    >테이블레벨방식
    FOREIGN KEY(컬럼명) REFERENCES 참조할테이블명[참조할컬럼명]
*/

DROP TABLE MEM;

CREATE TABLE MEM(
    MEM_NO NUMBER PRIMARY KEY ,
    MEM_ID VARCHAR2(30) NOT NULL UNIQUE, --user01, test1234
    MEM_PWD VARCHAR2(30) NOT NULL,
    MEM_NAME VARCHAR2(21) NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    PHONE VARCHAR2(13),
    EMAIL VARCHAR2(50),
    CREATE_AT DATE,
    GRADE_ID NUMBER REFERENCES MEM_GRADE(GRADE_CODE)
);

INSERT INTO MEM
VALUES(1, 'USER1', 'PASS1', '홍길동', NULL, NULL, NULL, '25/01/01', NULL);

INSERT INTO MEM
VALUES(2, 'USER2', 'PASS2', '홍길순', NULL, NULL, NULL, '25/01/01', 10);

INSERT INTO MEM
VALUES(3, 'USER3', 'PASS3', '홍길삼', NULL, NULL, NULL, '25/01/01', 20);

INSERT INTO MEM
VALUES(4, 'USER4', 'PASS4', '홍길사', NULL, NULL, NULL, '25/01/01', 40);

--유효하지않은 회원등급번호는 정상적으로 INSERT할 수 없음.
INSERT INTO MEM
VALUES(5, 'USER5', 'PASS5', '홍길오', NULL, NULL, NULL, '25/01/01', 50);

SELECT * FROM MEM;
--MEM_GRADE(부모테이블) -|---<- MEM(자식테이블)
--부모테이블 : 외래키로 참조되는 테이블
--자식테이블 : 외래키를 통해 부모테이블을 참조하는 테이블
-- 1:N일 경우 보통 N쪽이 자식테이블

DELETE FROM MEM_GRADE
WHERE GRADE_CODE = 10;
--MEM테이블에서 10이라는 GRADE_ID가 있기때문에 부모테이블의 GRADE_CODE 10을 가진 데이터 삭제가 안됨.

DELETE FROM MEM_GRADE
WHERE GRADE_CODE = 30;
--MEM테이블에서 참조하고있지 않은 30의 GRADE_CODE를 가진 데이터는 삭제가 가능함.

--자식테이블에서 이미 사용하고 있는 값이 있을 겨우
--부모테이블로부터 무조건 삭제가 안되는 "삭제제한"옵션이 기본값으로 설정되어있음.

------------------------------------------------------------------------------------
/*
    자식테이블 생성시 외래키 제약조건 부여할 때 삭제옵션을 함께 지정가능.
    *삭제옵션 : 부모테이블의 데이터 삭제시 그 데이터를 참조하고있는 자식테이블의 데이터를 어떻게 처리할 것인가?
    
    - ON DELETE RESTRICTED(기본값) : 삭제제한옵션, 자식데이터로부터 쓰이는 부모데이터는 삭제가 안됨.
    - ON DELETE SET NULL : 부모데이터 삭제시 해당 데이터를 사용하고있는 자식데이터의 값을 NULL로 변경.
    - ON DELETE CASCADE : 부모데이터 삭제시 해당 데이터를 사용하고있는 자식데이터도 모두 삭제.
*/

DROP TABLE MEM;

CREATE TABLE MEM(
    MEM_NO NUMBER PRIMARY KEY ,
    MEM_ID VARCHAR2(30) NOT NULL UNIQUE, --user01, test1234
    MEM_PWD VARCHAR2(30) NOT NULL,
    MEM_NAME VARCHAR2(21) NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    PHONE VARCHAR2(13),
    EMAIL VARCHAR2(50),
    CREATE_AT DATE,
    GRADE_ID NUMBER REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE SET NULL
);


INSERT INTO MEM
VALUES(1, 'USER1', 'PASS1', '홍길동', NULL, NULL, NULL, '25/01/01', NULL);

INSERT INTO MEM
VALUES(2, 'USER2', 'PASS2', '홍길순', NULL, NULL, NULL, '25/01/01', 10);

INSERT INTO MEM
VALUES(3, 'USER3', 'PASS3', '홍길삼', NULL, NULL, NULL, '25/01/01', 20);

INSERT INTO MEM
VALUES(4, 'USER4', 'PASS4', '홍길사', NULL, NULL, NULL, '25/01/01', 40);

DELETE FROM MEM_GRADE
WHERE GRADE_CODE = 40;
-- MEM_GRADE의 PK 40데이터가 삭제되면 SET NULL옵션에 따라 
-- GRADE_CODE 40인 데이터를 참조하던 자식데이터틀은 전부 NULL을 가짐

DROP TABLE MEM;

CREATE TABLE MEM(
    MEM_NO NUMBER PRIMARY KEY ,
    MEM_ID VARCHAR2(30) NOT NULL UNIQUE, --user01, test1234
    MEM_PWD VARCHAR2(30) NOT NULL,
    MEM_NAME VARCHAR2(21) NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    PHONE VARCHAR2(13),
    EMAIL VARCHAR2(50),
    CREATE_AT DATE,
    GRADE_ID NUMBER REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE CASCADE
);


INSERT INTO MEM
VALUES(1, 'USER1', 'PASS1', '홍길동', NULL, NULL, NULL, '25/01/01', NULL);

INSERT INTO MEM
VALUES(2, 'USER2', 'PASS2', '홍길순', NULL, NULL, NULL, '25/01/01', 10);

INSERT INTO MEM
VALUES(3, 'USER3', 'PASS3', '홍길삼', NULL, NULL, NULL, '25/01/01', 20);

INSERT INTO MEM
VALUES(4, 'USER4', 'PASS4', '홍길사', NULL, NULL, NULL, '25/01/01', 40);

DELETE FROM MEM_GRADE
WHERE GRADE_CODE = 10;
-- MEM_GRADE의 PK 10데이터가 삭제되면 CASCADE옵션에 따라 
-- 자식데이터 중 GRADE_CODE 10을 참조하던 데이터는 모두 함께 삭제된다. 

--------------------------------------------------------------------------
/*
    <DEFAULT 기본값> *제약조건이 아니다.
    컬럼을 선정하지않고 INSERT시에 NULL이 아닌 기본값을 INSERT하고자한다.
    미리 값을 세팅해 둘 수 있음.
    
    컬럼명 자료형 DEFAULT 기본값
*/

DROP TABLE MEM;

CREATE TABLE MEM(
    MEM_NO NUMBER PRIMARY KEY ,
    MEM_ID VARCHAR2(30) NOT NULL UNIQUE, --user01, test1234
    MEM_AGE NUMBER,
    HOBBY VARCHAR2(20) DEFAULT '코딩',
    CREATE_AT DATE DEFAULT SYSDATE
);

--INSERT INTO 테이블명 VALUES(값1,값2...) -> 모든 컬럼값을 나열해야함.
INSERT INTO MEM VALUES(1, 'USER01', 20, '운동', '20/01/01');
INSERT INTO MEM VALUES(2, 'USER02', 24, NULL, NULL);
INSERT INTO MEM VALUES(3, 'USER03', 27, DEFAULT, DEFAULT);

--INSERT INTO 테이블명(컬럼1, 컬럼2) VALUES(값1, 값2);
INSERT INTO MEM(MEM_NO, MEM_ID) 
VALUES(5, 'USER05');
--> 선택되지 않은 컬럼에는 기본적으로 NULL이 적용됨
--> 단, 해당 컬럼에 DEFAULT값이 부여되어있다면 NULL이 아닌 DEFAULT값이 적용됨.

----------------------------------------------------------------------------------
--관리자계정으로 계정생성
CREATE USER C##KH IDENTIFIED BY KH;

GRANT CONNECT, RESOURCE TO C##KH;

ALTER USER C##KH DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;
--KH계정으로 전환
---------------------------------------------------------------------------------
--테이블을 복제할 수 있다. 여기서부터 KH계정으로 진행함.
CREATE TABLE EMPLOYEE_COPY
AS (SELECT * FROM EMPLOYEE);

--CREATE TABLE 테이블명 AS SELECT문
-- AS 뒤에오는 SELECT문의 결과를 기반으로 테이블을 생성하겠다.

DROP TABLE EMPLOYEE_COPY;

--테이블 구조만 복사
CREATE TABLE EMPLOYEE_COPY
AS (SELECT * FROM EMPLOYEE WHERE 1=0);
---------------------------------------------------------------
/*
    테이블이 생성된 후에 제약조건을 수정하는 방법
    ALTER TABLE 테이블명 변경할 내용
    
    제약조건 추가방법
    -PRIMARY KEY : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] PRIMARY KEY(컬럼명);
    -FOREIGN KEY : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] FOREIGN KEY(컬럼명) REFERENCES 참조할테이블명(컬럼명);
    -UNIQUE : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] UNIQUE(컬럼명);
    -CHECK : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] CHECK(컬럼에대한 조건식);
    
    위의 제약조건을 제거하려면
    -ALTER TABLE 테이블명 DROP CONTRAINT 제약조건이름;
    
    -NOT NULL : ALTER TABLE 테이블명 MODIFY 컬럼명 NOT NULL;
                ALTER TABLE 테이블명 MODIFY 컬럼명 NULL;
*/

--EMPLOYEE_COPY테이블에 PRIMARY KEY 제약조건 추가(EMP_ID)
ALTER TABLE EMPLOYEE_COPY ADD PRIMARY KEY(EMP_ID);

--EMPLOYEE테이블에 DEPT_CODE에 외래키제약조건을 추가(DEPT_ID를 참조)
ALTER TABLE EMPLOYEE ADD CONSTRAINT DEPT_FK FOREIGN KEY(DEPT_CODE) REFERENCES DEPARTMENT(DEPT_ID);
