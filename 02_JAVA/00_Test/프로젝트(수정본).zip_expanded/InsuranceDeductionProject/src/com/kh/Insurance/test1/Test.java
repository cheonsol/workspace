package com.kh.Insurance.test1;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Test {

	public static void main(String[] args) {
        // Properties
		// Properties클래스는 Map계열의 컬렉션으로, key-value형식으로 데이터를 저장/읽기할 수 있음.
		// 특히 외부파일(.properties / .xml)을 통해 데이터를 쉽게 관리하고 불러올 수 있음.
		// DB연결정보, 프로그램 설정정보(port, 환경변수...), sql쿼리문등을 코드와 분리해서 관리할 때 주로 사용.

		
		//파일로 출력
		Properties prop = new Properties();
		prop.setProperty("driver", "oracle.jdbc.driver.OracleDriver"); //데이터 생성
		prop.setProperty("url", "jdbc:oracle:thin:@localhost:1521:xe"); //데이터 조회
		prop.setProperty("username", "C##JH"); //데이터 수정
		prop.setProperty("password", "JH"); //데이터 삭제
		
		
		Properties queryProp = new Properties();
        queryProp.setProperty("insertMember", "INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL,?,? ?, ?, ?, ?, ?, ?,?, SYSDATE, ?)");
        queryProp.setProperty("insertCompany", "INSERT INTO COMPANY VALUES(COMPANY_SEQ.NEXTVAL, ?, ?, ?)");
        queryProp.setProperty("insertDriveWeight", "INSERT INTO DRIVE_WEIGHT VALUES(?, ?, ?, ?, ?, ?)");
        queryProp.setProperty("insertAccidentWeight", "INSERT INTO ACCIDENT_WEIGHT VALUES(?, ?, ?, ?, ?, ?)");
        queryProp.setProperty("insertCar", "INSERT INTO CAR VALUES(?, ?)");
        queryProp.setProperty("selectMemberList", "SELECT * FROM MEMBER");
        queryProp.setProperty("selectCompanyList", "SELECT * FROM COMPANY");
        queryProp.setProperty("selectDriveWeightList", "SELECT * FROM DRIVE_WEIGHT");
        queryProp.setProperty("selectAccidentWeightList", "SELECT * FROM ACCIDENT_WEIGHT");
        queryProp.setProperty("selectCarList", "SELECT * FROM CAR");
        queryProp.setProperty("updateMember", "UPDATE MEMBER SET USER_PASS=? ADDRESS=?, PHONE=?, DRIVE_CAREER=?, USER_CAR=?,ACCIDENT_COUNT=? WHERE USER_ID=?");
        queryProp.setProperty("updateCompany", "UPDATE COMPANY SET DEDUCTION_FEE=?, COMPANY_ID=?");
        queryProp.setProperty("updateDriveWeight", "UPDATE DRIVE_WEIGHT SET EXPERIENCE_MIN=? , EXPERIENCE_MAX=? , DW_WEIGHT_SCORE=?  WHERE DW_ID=?");
        queryProp.setProperty("updateAccidentWeight", "UPDATE ACCIDENT_WEIGHT SET ACCIDENT_MIN=? , ACCIDENT_MAX=? , AW_WEIGHT_SCORE=?  WHERE AW_ID=?");
        queryProp.setProperty("updateCar", "UPDATE CAR SET CAR_NAME=? , CAR_ID=?");
        queryProp.setProperty("deleteMember", "DELETE FROM MEMBER WHERE ID=? AND USER_PASS = ? AND CANCEL_DATE IS NOT NULL");
        queryProp.setProperty("deleteCompany", "DELETE FROM COMPANY WHERE COMPANY_ID=?");
        queryProp.setProperty("deleteCar", "DELETE FROM CAR WHERE CAR_ID=?");
        queryProp.setProperty("updateJoinMember", "UPDATE MEMBER SET USER_PASS=? ADDRESS=?, PHONE=?, DRIVE_CAREER=?, USER_CAR=?,ACCIDENT_COUNT=? WHERE USER_ID=?");
        queryProp.setProperty("deleteJoinMember", "DELETE FROM MEMBER WHERE ID=? AND USER_PASS=?");
        queryProp.setProperty("selectMemberById", "SELETE * FROM MEMBER WHERE ID = ?");
        queryProp.setProperty("selectCompanyIdByName", "SELECT * FROM COMPANY WHERE COMPANY_NAME = ?");
        queryProp.setProperty("selectCompanyById", "SELECT * FROM COMPANY WHERE COMPANY_ID = ?");
        queryProp.setProperty("selectAccidentWeightById", "SELECT * FROM ACCIDENT_WEIGHT WHERE COMPANY_ID =? AND USER_CAR = ? AND ACCIDENT_COUNT =? ");
        queryProp.setProperty("selectLogMember", "SELECT * FROM MEMBER WHERE ID = ? AND USER_PASS = ?");
        queryProp.setProperty("selectDriveWeightById", "SELECT * FROM DRIVE_WEIGHT WHERE COMPANY_ID =? AND USER_CAR = ? AND DRIVE_CAREER =? ");
        

		
		try {
			//prop.store() -> .properties파일로 객체의 정보를 저장하겠다.
			//.properties파일 -> 단순 텍스트 파일(key-value형식)
			prop.store(new FileOutputStream("resources/driver.properties"), "properties test");
			
			//prop.storeToXML() .xml파일로 객체의 정보를 저장하겠다.
			//.xml 파일 -> xml형식 문서(태그구조)
			// -> dtd : xml 문서의 구조와 규칙을 정의한 문서
			queryProp.storeToXML(new FileOutputStream("resources/query.xml"),null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	}



