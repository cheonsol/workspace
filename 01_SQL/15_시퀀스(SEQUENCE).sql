/*
    <시퀀스 SEQUENCE>
    자동으로 숫자를 발생시켜주는 역할을 하는 객체.
    주로 기본키로 사용되는 회원번호, 사원번호, 게시글번호, 택배운송장번호...등에 활용됨.
    시퀀스를 사용하면 중복 없는 숫자값을 편리하게 관리할 수 있음.
*/

/*
    1. 시퀀스 객체 생성
    
    [표현식]
    CREATE SEQUENCE 시퀀스명
    [START WITH 시작값] -> 처음 발생시킬 시작값을 지정(기본값 1)
    [INCREMENT BY 증가값] -> 증가값을 지정할 수 있음(기본값 1)
    [MAXVALUE 최대값] -> 최대값 지정(기본값 매우큼)
    [MINVALUE 최소값] -> 최소값 지정(기본값 1)
    [CYCLE | NOCYCLE] -> 순환여부(NOCYCLE)
    [CACHE | NOCACHE] -> 캐시메모리 사용 여부
    
    *캐시메모리 : 미리 일정량의 값들을 생성해서 메모리에 준비 -> 속도 향상
    예: CACHE 20 -> 시퀀스를 미리 20개 만들어두고 필요할 때 꺼내 씀
*/

CREATE SEQUENCE SEQ_TEST;

--현재 계정이 소유한 시퀀스들의 목록 확인
SELECT * FROM USER_SEQUENCES;

CREATE SEQUENCE SEQ_EMPNO
START WITH 300
INCREMENT BY 5
MAXVALUE 310
NOCYCLE
NOCACHE;

/*
    2. 시퀀스 사용
    시퀀스명.NEXTVAL : 다음 숫자 발생(INCREMENT 만큼 증가)
    시퀀스명.CURRVAL : 가장 최근 생성된 값
    CURRVAL는 최초 사용시 오류 발생 -> NEXTVAL를 한번이라도 호출해야 사용가능
*/

SELECT * FROM USER_SEQUENCES;

SELECT SEQ_EMPNO.CURRVAL FROM DUAL; --에러발생

SELECT SEQ_EMPNO.NEXTVAL FROM DUAL; --300
SELECT SEQ_EMPNO.CURRVAL FROM DUAL; --300
SELECT SEQ_EMPNO.NEXTVAL FROM DUAL; --305
SELECT SEQ_EMPNO.NEXTVAL FROM DUAL; --310

SELECT SEQ_EMPNO.NEXTVAL FROM DUAL; --MAXVALUE 310 -> 에러발생

/*
    3. 시퀀스 수정
    
    ALTER SEQUENCE 시퀀스명
    [INCREMENT BY 증가값] -> 증가값을 지정할 수 있음(기본값 1)
    [MAXVALUE 최대값] -> 최대값 지정(기본값 매우큼)
    [MINVALUE 최소값] -> 최소값 지정(기본값 1)
    [CYCLE | NOCYCLE] -> 순환여부(NOCYCLE)
    [CACHE 바이트 수| NOCACHE] -> 캐시메모리 사용 여부
    
    START WITH는 변경불가
*/
ALTER SEQUENCE SEQ_EMPNO
INCREMENT BY 10
MAXVALUE 400;

SELECT * FROM USER_SEQUENCES;

INSERT INTO EMPLOYEE(
    EMP_ID,
    EMP_NAME,
    EMP_NO,
    JOB_CODE
) VALUES(
    SEQ_EMPNO.NEXTVAL, 
    '최지구', 
    '701011-1234567',
    'J7'
);

INSERT INTO EMPLOYEE(
    EMP_ID,
    EMP_NAME,
    EMP_NO,
    JOB_CODE
) VALUES(
    SEQ_EMPNO.NEXTVAL, 
    '최지열', 
    '701011-1234567',
    'J6'
);

/*
    4. 시퀀스 삭제
    DROP SEQUENCE 시퀀스명
*/

DROP SEQUENCE SEQ_TEST;






