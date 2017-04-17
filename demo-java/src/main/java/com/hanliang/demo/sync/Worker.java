package com.hanliang.demo.sync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable{
	
	private int id;
	private CyclicBarrier barrier;
	
	public Worker(int id,CyclicBarrier barrier){
		this.id = id;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			System.out.println(id+" start to work");
			Thread.sleep((long) (Math.random()*10000));
			System.out.println(id+" finished to work");
			this.barrier.await();
			System.out.println("do next");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
