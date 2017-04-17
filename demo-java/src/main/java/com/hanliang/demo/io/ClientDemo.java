package com.hanliang.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientDemo {
	
	private static int Default_port = 12345;
	private static String ip = "127.0.0.1";
	
	public static void main(String[] args){
		connect();
	}
	
	public static void connect(){
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			socket = new Socket(ip,Default_port);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(),true);
			pw.println("hahah");
				System.out.println(br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pw.close();
				br.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
