package com.kh.thread.runable;

public class Run {
	//스레드 : 프로그램 내에서 실행의 흐름을 가지고있는 최소단위
	//		 main메서드 실생시 메인 스레드가 실행되는 것
	public static void main(String[] args) {
		
		//Runnable을 구현한 객체를 생성 후 스레드의 생성자에게 전달
		Runnable t1run = new MyRunable();
		Thread t1 = new Thread(t1run);
		
		Task t2 = new Task();
		
		t1.start(); // 스레드를 실행시키는 메서드
		t2.start();
		
		System.out.println("END : " + Thread.currentThread().getName());
	}

}
