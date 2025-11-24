package com.kh.thread.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerSend extends Thread{
	private Socket socket;

	public ServerSend(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try (Scanner sc = new Scanner(System.in)){
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			
			while(true) {	
				System.out.print("클라이언트로 보낼 내용 : ");
				String sendMessage = sc.nextLine();
				
				pw.println(sendMessage); //클라이언트 스트림에 출력
				pw.flush(); //스트림에 있는 데이터 강제로 전부 내보내기
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
