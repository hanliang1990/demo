package com.hanliang.demo.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
	
	private static int DEFAULT_PORT = 12345;
	
	private static ServerSocket serverSocket;
	
	public static void main(String[] args){
		ServerDemo  sd = new ServerDemo();
		try {
			sd.start(12345);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void start(int port) throws IOException{
		if(serverSocket != null)return;
		try{
			serverSocket = new ServerSocket(port);
			System.out.println("服务端已经启动");
			Socket socket;
			while(true){
				socket = serverSocket.accept();
				System.out.println("收到客户端连接请求："+socket.getInetAddress());
				new Thread(new ServerHandler(socket)).start();
			}
		}finally{
			if(serverSocket != null){
				serverSocket.close();
				serverSocket = null;
			}
		}
		
	}

}
