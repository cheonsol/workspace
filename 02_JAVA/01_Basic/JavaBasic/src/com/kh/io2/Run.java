package com.kh.io2;

public class Run {
	/*
	 * 자바 스트림 개념
	 * - 프로그램과 외부매체(파일, 네트워크, 콘솔...)간의 데이터 전달 통로
	 * - 데이터를 순차적으로 흘려보내는 파이프같은 구조.
	 * 
	 * 스트림의 특징
	 * 1. 단방향 : 입력이면 입력만, 출력이면 출력만 가능 -> 동시에 원하면 각각 만들어줘야한다.
	 * 2. 순차적 처리(FIFO) : 먼저 보낸 데이터가 먼저 나온다.
	 * 3. 시간 지연 가능성 : 외부매체와 연결되므로 입출력속도가 상대적으로 느릴 수 있음.
	 * 
	 * 스트림의 구분
	 * > 데이터 단위에 따른 구분(바이트스트림(1Byte), 문자스트림(2Byte))
	 *   바이트스트림(InputStream, OutputStream): 이미지, 동영상, 바이너리 데이터등...
	 *   문자스트림(Reader, Writer): 텍스트 데이터(문자/문자열)...
	 * 
	 * > 외부 매체 연결 방식에 따른 구분
	 *   기반스트림 : 외부매체와 직접 연결되는 스트림(필수, 단독사용가능)
	 *   보조스트림 : 기반스트림 감싸 성능 향상이나 기능추가 제공(단독사용불가, 반드시 기반스트림과 함께 사용)
	 * */

	public static void main(String[] args) {
		//new ServeStream().fileSave();
		//new ServeStream().fileRead();
		//new ServeStream().ObjectSave();
		new ServeStream().ObjectRead();
	}

}
