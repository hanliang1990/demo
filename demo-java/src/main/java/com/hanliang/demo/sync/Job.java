package com.hanliang.demo.sync;

import java.util.concurrent.CountDownLatch;

public class Job implements Runnable{
	
	private int id;
	
	private CountDownLatch latch;
	
	public Job(int id,CountDownLatch latch){
		this.id = id;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			System.out.println(this.id + " start do job");
			Thread.sleep((long) (Math.random()*10000));
			System.out.println(this.id + " finish do job");
			latch.countDown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
