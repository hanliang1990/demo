package com.hanliang.demo.io;

import java.io.IOException;

public class Test {
	
	public static void main(String[] args){
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					ServerDemo.start(12345);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				ClientDemo.connect();
			}
			
		}).start();
		
	}

}
