package com.hanliang.demo.nio;

import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) throws Exception{
		Server.start();
		Thread.sleep(3000);
		Client.start();
		while(Client.sendMessage(new Scanner(System.in).nextLine()));
	}

}
