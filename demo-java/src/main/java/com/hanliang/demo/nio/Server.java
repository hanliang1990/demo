package com.hanliang.demo.nio;

public class Server {
	
	private static int default_port = 12345;
	
	private static ServerHandle serverHandle;
	
	public static void start(){
		start(default_port);
	}
	
	public static void start(int port){
		if(serverHandle != null){
			serverHandle.stop();
		}
		serverHandle = new ServerHandle(port);
		new Thread(serverHandle,"Server").start();
	}
	
	public static void main(String[] args){
		start();
	}
}
