package com.kh.thread.runable;

//스레드를 만드는 방법1. Runnable 인터페이스를 구현한다.
//run에 스레드에서 실행하고자하는 코드를 기술
public class MyRunable implements Runnable{

	@Override
	public void run() {
		int n1 = 20;
		int n2 = 30;
		
		//Thread.currentThread() -> 지금 실행중인 스레드정보 가져오기
		String name = Thread.currentThread().getName();
		System.out.println(name + " : " + (n1 + n2));
	}

}
