package com.hanliang.demo.nio;

public class Client {
	
	private static String DEFAULT_IP = "127.0.0.1";
	private static int DEFAULT_PORT = 12345;
	private static ClientHandle clientHandle;
	
	public static void start(){
		start(DEFAULT_IP,DEFAULT_PORT);
	}
	
	public static void start(String ip,int port){
		if(clientHandle != null){
			clientHandle.stop();
		}
		clientHandle = new ClientHandle(ip,port);
		new Thread(clientHandle,"client").start();;
	}
	
	public static boolean sendMessage(String message){
		if("q".equals(message)){
			return false;
		}
		clientHandle.sendMessage(message);
		return true;
	}

}
