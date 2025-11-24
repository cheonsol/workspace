package com.kh.io2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

public class ServeStream {
	/**
	 * 보조스트림
	 * 외부매체와 직접 연결되지않고, 기반스트림을 감싸서 기능/성능을 높여줌
	 * ex) new BufferedWriter(new FileWriter(경로));
	 * 	   FileWriter : 파일을 직접적으로 연결해서 2바이트단위로 출력할 수 있는 기반 스트림
	 * 	   BufferedWriter : 편의성과 속도향상에 도움을 주는 보조스트림(기반스트림없이 사용 불가)
	 */
	//프로그램 -> 파일(출력)
	public void fileSave() {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("1_buffer.txt"));){
			/*
			//1. 기반스트림 생성
			FileWriter fw = new FileWriter("1_buffer.txt");
			
			//2. 보조스트림 생성시 기반스트림 객체를 전달하여 생성
			BufferedWriter bw = new BufferedWriter(fw);
			
			fw.close();
			*/
			
			bw.write("안녕하세요\n");
			bw.write("반갑습니다.");
			bw.newLine();
			bw.write("끝");
			bw.flush(); //버퍼 강제 비움
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void fileSaveWithStream() {
		FileOutputStream fo = null;
		
		try {
			//1. 기반스트림 생성
			fo = new FileOutputStream("1_buffer.txt");
			
			//2. 기반스트림 -> OutputStream을 Writer로 사용할 수 있게 보조
			OutputStreamWriter osw = new OutputStreamWriter(fo, "UTF-8");
			
			//3. 보조스트림 생성시 기반스트림 객체를 전달하여 생성
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write("안녕하세요\n");
			bw.write("반갑습니다.");
			bw.newLine();
			bw.write("끝");
			bw.flush(); //버퍼 강제 비움
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//외부매체(파일) -> 프로그램 (입력)
	public void fileRead() {
		 //FileReader : 파일을 직접적으로 연결해서 2바이트단위로 입력받을 수 있는 기반 스트림
		 //BufferedReader : 한줄씩 읽어오는 기능이 가능한 보조스트림
		
		try(BufferedReader br = new BufferedReader(new FileReader("1_buffer.txt"))){
			/*
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine()); //파일에 모든 내용을 읽어온 후에는 null이 반환됨.
			*/
			String text = null;
			while((text = br.readLine()) != null) {
				System.out.println(text);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/*
	 * 직렬화
	 * 객체는 메모리안에서 존재하는 인스턴스 개념이기 때문에 그대로 전송할 수 없음, 직렬화 후 이진데이터로 외부에 전송 가능.
	 * - 객체(Object)를 바이트형태로 변환하는 과정.
	 * - 파일에 저장하거나 네트워크로 전송할 수 있도록 함.
	 * - 자바에서는 클래스를 직렬화하기위해 직렬화 대상클래스에게는 implements Serializable 해야 함.
	 * 
	 * 역직렬화
	 * 저장해 두거나 전송했던 데이터를 다시 원래 객체처럼 사용하기위해 역직렬화를 진행함.
	 * - 저장된 바이트 데이터를 다시 객체로 복원하는 과정
	 * */
	
	//프로그램 -> 파일(출력) : 객체(직렬화)
	public void ObjectSave() {
		Product p1 = new Product("아이폰16", 1000000);
		Product p2 = new Product("아이폰16Pro", 1200000);
		Product p3 = new Product("아이폰16mini", 800000);
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("product1.dat"));){
			oos.writeObject(p1); //직렬화 후 파일에 객체를 저장.
			oos.writeObject(p2);
			oos.writeObject(p3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//파일 -> 프로그램(입력) : 객체바이트코드(역직렬화)
	public void ObjectRead() {
			
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("product1.dat"));){
			while(true) {
				Object obj = ois.readObject();
				if(obj instanceof Product) {
					Product p1 = (Product)obj;
					System.out.println(p1);
				}
			}
		} catch (IOException e) {
			System.out.println("에러 : 객체 읽기 중 I/O 에러발생");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("에러 : 직렬화 대상 클래스를 찾을 수 없습니다.");
			e.printStackTrace();
		}
	}
}
