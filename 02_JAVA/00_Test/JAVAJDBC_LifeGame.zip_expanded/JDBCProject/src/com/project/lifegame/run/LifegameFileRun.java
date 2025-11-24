package com.project.lifegame.run;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class LifegameFileRun {
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("C", "CREATE");  // CREATE 데이터 생성
		prop.setProperty("R", "READ");    // READ   데이터 조회
		prop.setProperty("U", "UPDATE");  // UPDATE 데이터 수정
		prop.setProperty("D", "DELETE");  // DELETE 데이터 삭제
		
		try {
			//prop.store() -> properties 파일로 객체의 정보를 저장하겠다
			//.properties -> 단순 텍스트 파일 (Key-Value 형식)
			prop.store(new FileOutputStream("resources/driver.properties"), "properties test");// 첫번째는 어디에 저장할 것인지. 
			//prop.storeToXML() .xml 파일로 객체의 정보를 저장하겠다.
			//.xml 파일 -> xml 형식 문서 (<태그> 구조)
			// -> dtd : xml 문서의 구조와 규칙을 정의한 문서
			prop.storeToXML(new FileOutputStream("resources/query.xml"), null);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
