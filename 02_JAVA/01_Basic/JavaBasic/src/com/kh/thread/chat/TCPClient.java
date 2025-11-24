package com.kh.thread.chat;

import java.io.IOException;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) {
		String serverIP = "192.168.10.51";
		int port = 3000;

		try {
			//1) 서버로 연결 요청(서버의 ip와 port로 연결을 요청)
			Socket socket = new Socket(serverIP, port);
			
			if(socket != null) {
				ClientReceive cr = new ClientReceive(socket);
				cr.start();
				
				ClientSend cs = new ClientSend(socket);
				cs.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
