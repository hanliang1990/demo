package com.hanliang.demo.sync;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	
	public static void main(String[] args){
		
		final int count = 5;
		final CyclicBarrier barrier = new CyclicBarrier(count,new Runnable(){

			@Override
			public void run() {
				System.out.println("all work is finished");
			}
			
		});
		for(int i=0;i<count;i++){
			new Thread(new Worker(i,barrier)).start();
		}
	}
}
