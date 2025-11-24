/*
    *서브쿼리
    - 하나의 SQL문 안에 포함된 또 다른 SELECT문
    - 메인 SQL문을 위해 보조 역할을 하는 쿼리
*/

-- 서브쿼리 예시1) 노옹철 사원과 같은 부서에 속한 사원들 전체 조회
SELECT *
FROM EMPLOYEE
WHERE DEPT_CODE = ('노옹철사원의부서코드');

--노옹철사원의 부서코드?
SELECT DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '노옹철';

--위에 두 쿼리를 하나의 쿼리로 변경
SELECT *
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE
                    FROM EMPLOYEE
                    WHERE EMP_NAME = '노옹철');

--서브쿼리 예시2)
--전 직원의 평균급여보다 더 많은 급여를 받는 사원들의 사번, 이름, 직급코드, 급여 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE,SALARY
FROM EMPLOYEE
WHERE SALARY > (SELECT AVG(SALARY)
                    FROM EMPLOYEE);

--전직원의 평균급여
SELECT AVG(SALARY)
FROM EMPLOYEE;

/*
    *서브쿼리 구문
    서브쿼리를 수행한 결과값이 몇행, 몇열로 나오느냐에 따라서 분류
    -- 단일행 서브쿼리 : 서브쿼리의 조회 결과값이 오로지 한개일 때
    -- 다중행 서브쿼리 : 서브쿼리의 조회 결과값이 여러행일 때(여러행 한열)
    -- 다중열 서브쿼리 : 서브쿼리의 조회 결과값이 한 행이지만 컬럼이 여러개일 때
    -- 다중행 다중열 서브쿼리 : 서브쿼리의 조회 결과값이 여러행 여러컬럼일 떄
    
    >> 서브쿼리의 결과값에 따라서 서브쿼리 앞쪽에 연산자가 달라진다.
*/       

/*
    1. 단일행 서브쿼리
    서브쿼리 결과로 값이 딱 1개일 때(한행 한열)
    일반적으로 비교연자와 함께 사용
    
    = != > <= ...
*/

--1) 전 직원의 평균급여보다 급여를 더 적게받는 사원들의 사원명, 직급코드, 급여 조회
SELECT EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY < (SELECT AVG(SALARY) FROM EMPLOYEE);

--2) 최저급여를 받는 사원의 사번, 이름, 급여, 입사일 조회
SELECT EMP_ID, EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE SALARY = (SELECT MIN(SALARY) FROM EMPLOYEE);

--3) 노옹철 사원의 급여보다 급여를 많이받는 사원들의 사번, 이름, 부서명, 급여를 조회
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY
FROM EMPLOYEE
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE SALARY > (SELECT SALARY
                FROM EMPLOYEE
                WHERE EMP_NAME = '노옹철');

--4) 부서별 급여합이 가장 큰 부서의 부서코드, 급여합
SELECT DEPT_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING SUM(SALARY) = (SELECT MAX(SUM(SALARY))
                        FROM EMPLOYEE
                        GROUP BY DEPT_CODE);

--5) '전지연'사원과 같은 부서의 사람들의 사번, 사원명, 전화번호, 입사일, 부서명 조회
--단, 전지연사원은 제외
SELECT EMP_ID, EMP_NAME, PHONE, HIRE_DATE, DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE DEPT_CODE = (SELECT DEPT_CODE
                    FROM EMPLOYEE
                    WHERE EMP_NAME = '전지연')
  AND EMP_NAME != '전지연';
  
----------------------------------------------------------------------------
/*
    2. 다중행 서브쿼리
    서브쿼리를 수행한 결과값이 여러행일 때(컬럼은 한개)
    
    비교대상 IN(값~) : 여러개의 결과값 중 한개라도 일치하는 값이 있는가?
    비교대상 >, < ANY(값~) : 여러개의 결과값 중 한개라로 비교연산을 만족하는가?
    비교대상 >, < ALL(값~) : 여러개의 결과값 모두가 비교연산을 만족하는가?
    위 3가지 연산자와 함께 사용하는 경우가 많음.
*/

--1) 유재식 또는 윤은해 사원과 같은 직급인 사원들의 사번, 사원명, 직급코드, 급여조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE JOB_CODE = 'J3' OR JOB_CODE = 'J7';

--단일행 서브쿼리 여러개를 통해 구현
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE JOB_CODE = (SELECT JOB_CODE
                    FROM EMPLOYEE
                    WHERE EMP_NAME = '유재식')
OR JOB_CODE = (SELECT JOB_CODE
                    FROM EMPLOYEE
                    WHERE EMP_NAME = '윤은해');
                    
--IN절을 통해 다중행 서브쿼리로 처리할 수 있음
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE JOB_CODE IN (SELECT JOB_CODE
                    FROM EMPLOYEE
                    WHERE EMP_NAME IN ('유재식', '윤은해'));
                    
--2) 대리직급임에도 과장직급의 최소급여보다 더 많이 받는 사원들의 사번, 이름, 직급, 급여 조회
--2_1) 대리직급들의 급여를 포함한 정보(단일행)
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME = '대리'
 AND SALARY > (SELECT MIN(SALARY)
                FROM EMPLOYEE
                JOIN JOB USING(JOB_CODE)
                WHERE JOB_NAME = '과장');
                
--2_2) 과장들의 급여
SELECT SALARY
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME = '과장';

SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME = '대리'
 AND SALARY > ANY (SELECT SALARY
                    FROM EMPLOYEE
                    JOIN JOB USING(JOB_CODE)
                    WHERE JOB_NAME = '과장');

----------------------------------------------------------------------------------
/*
    3. 다중열 서브쿼리
    결과값은 한 행이지만 나열된 컬럼수가 여러개일 경우(두개 이상의 컬럼)
*/

--1) 하이유 사원과 같은 부서코드, 같은 직급코드에 해당하는 사원들 조회
-- 사원명, 부서코드, 직급코드, 입사일
SELECT EMP_NAME, DEPT_CODE, JOB_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE
                    FROM EMPLOYEE
                    WHERE EMP_NAME = '하이유')
  AND JOB_CODE = (SELECT JOB_CODE
                    FROM EMPLOYEE
                    WHERE EMP_NAME = '하이유');

--다중열 서브쿼리
--튜플비교 :  두개 이상의 열(컬럼)을 조합해서 비교하는 방식
SELECT EMP_NAME, DEPT_CODE, JOB_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE (DEPT_CODE, JOB_CODE) = (SELECT DEPT_CODE, JOB_CODE
                                FROM EMPLOYEE
                                WHERE EMP_NAME = '하이유');

--박나라 사원과 같은 직급코드, 같은 사수를 가지고 있는 사원들의 사번, 사원명, 직급코드, 사수번호를 조회
--단, 박나라 제외
SELECT EMP_ID, EMP_NAME, JOB_CODE, MANAGER_ID
FROM EMPLOYEE
WHERE (JOB_CODE, MANAGER_ID) = (SELECT JOB_CODE, MANAGER_ID
                                FROM EMPLOYEE
                                WHERE EMP_NAME = '박나라')
AND EMP_NAME != '박나라';

-------------------------------------------------------------------------
/*
    4. 다중행 다중열 서브쿼리
    서브쿼리의 조회 결과값이 여러행 여러열일 경우
*/
--1) 각 직급별 최소급여를 받는 사원조회(사번, 사원명, 직급코드, 급여)
--> 사원의 사번, 사원명, 직급코드, 급여
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE;

--> 각 직급별 최소급여
SELECT JOB_CODE, MIN(SALARY)
FROM EMPLOYEE
GROUP BY JOB_CODE;

--각 직급별 최소급여를 받는 사원조회(사번, 사원명, 직급코드, 급여)
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE (JOB_CODE = 'J1' AND SALARY = 8000000)
   OR (JOB_CODE = 'J2' AND SALARY = 3700000)
   OR (JOB_CODE = 'J4' AND SALARY = 1550000)
   OR (JOB_CODE = 'J3' AND SALARY = 3400000)
   OR (JOB_CODE = 'J7' AND SALARY = 1380000)
   OR (JOB_CODE = 'J5' AND SALARY = 2200000)
   OR (JOB_CODE = 'J6' AND SALARY = 2000000);

--다중행 다중열 서브쿼리 구성
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE (JOB_CODE, SALARY) IN (SELECT JOB_CODE, MIN(SALARY)
                                FROM EMPLOYEE
                                GROUP BY JOB_CODE);
                                
--각 부서별 최고급여를 받는 사원들의 사번, 사원명, 부서코드, 급여
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE (DEPT_CODE, SALARY) IN (SELECT DEPT_CODE, MAX(SALARY)
                                FROM EMPLOYEE
                                GROUP BY DEPT_CODE);

--각 부서별 급여 합계가 전체 급여 총 합의 20%보다 많은 부서의 부서명, 부서별 급여 합계 조회
SELECT DEPT_TITLE, SUM(SALARY)
FROM EMPLOYEE
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
GROUP BY DEPT_TITLE
HAVING SUM(SALARY) > (SELECT SUM(SALARY) * 0.2
                        FROM EMPLOYEE);
                        
----------------------------------------------------------------------
/*
    5. 인라인 뷰
    FROM절에서 서브쿼리를 작성한 것
    서브쿼리에 수행 결과를 마치 테이블처럼 사용.
*/

SELECT *
FROM EMPLOYEE;

--사원들의 사번, 이름, 보너스포함 연봉, 부서코드 조회
--단, 보너스포함 연봉이 NULL이면 안됨
--단, 보너스포함 연봉이 3000만원 이상인 사원들만 조회
SELECT EMP_ID, EMP_NAME,
        (SALARY + (SALARY * NVL(BONUS,0))) * 12 AS "연봉", DEPT_CODE
FROM EMPLOYEE
WHERE (SALARY + (SALARY * NVL(BONUS,0))) * 12 >= 30000000;
        
--연봉이 가장 높은 순으로 5명만 조회
SELECT ROWNUM, EMP_ID, EMP_NAME,
        (SALARY + (SALARY * NVL(BONUS,0))) * 12 AS "연봉", DEPT_CODE
FROM EMPLOYEE
WHERE (SALARY + (SALARY * NVL(BONUS,0))) * 12 >= 30000000
ORDER BY 연봉 DESC;
--LIMIT 5; ORACLE 지원하지 않는 문법

--ROWNUM : 오라클에서 기본적으로 제공해주는 컬럼, 조회된 수서대로 1부터 순번을 부여해줍니다.
--          단, ROWNUM은 이미 FROM절에서 결정이됨.
--> 인라이뷰를 통해서 TOP-N분석 : 상위 몇개만 조회
-- 전 직원 중 급여가 가장 높은 직원 5명
SELECT ROWNUM, EMP_NAME, SALARY
FROM EMPLOYEE
ORDER BY SALARY DESC;

--> ORDER BY절이 수행된 결과를 가지고 ROWNUM부여 : 상위 5명
SELECT ROWNUM, R, EMP_NAME, SALARY
FROM (SELECT ROWNUM R, EMP_NAME, SALARY
        FROM EMPLOYEE
        ORDER BY SALARY DESC)
WHERE ROWNUM <= 5;

--가장 최근에 입사한 사원 5명 조회(사원명, 급여, 입사일)
SELECT *
FROM (SELECT EMP_NAME, SALARY, HIRE_DATE
        FROM EMPLOYEE
        ORDER BY HIRE_DATE DESC)
WHERE ROWNUM <= 5;

--각 부서별 평균급여가 높은 3개의 부서 조회
SELECT DEPT_CODE, SALARY_AVG
FROM (SELECT DEPT_CODE, AVG(SALARY) AS SALARY_AVG
        FROM EMPLOYEE
        GROUP BY DEPT_CODE
        ORDER BY AVG(SALARY) DESC)
WHERE ROWNUM <= 3;

--부서별 평균급여가 270만원을 초과하는 부서들에 대해서
--부서코드, 부서별 총 급여합, 부서별 평균급여, 부서별 사원수 조회
SELECT DEPT_CODE, SUM(SALARY), AVG(SALARY), COUNT(*)
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING AVG(SALARY) > 2700000;

SELECT *
FROM (SELECT DEPT_CODE, SUM(SALARY), AVG(SALARY) AS SALARY_AVG, COUNT(*)
        FROM EMPLOYEE
        GROUP BY DEPT_CODE)
WHERE SALARY_AVG > 2700000;


                    
                    
                    