package com.hanliang.demo.sync;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	
	public static void main(String[] args){
		
		CountDownLatch latch = new CountDownLatch(5);
		for(int i=0;i<5;i++){
			new Thread(new Job(i,latch)).start();;
		}
		try {
			latch.await();
			System.out.println("do the next");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
