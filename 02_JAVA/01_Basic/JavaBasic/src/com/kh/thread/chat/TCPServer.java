package com.kh.thread.chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(3000);
			System.out.println("클라이언트 요청을 기다립니다.");
			Socket socket = server.accept();
			InetAddress clientIp = socket.getInetAddress();
			System.out.println(clientIp.getHostAddress() + "가 연결을 요첨함...");
			
			ServerReceive sr = new ServerReceive(socket);
			sr.start();
			
			ServerSend ss = new ServerSend(socket);
			ss.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
