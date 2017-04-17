package com.hanliang.demo.concurrent;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
	
	
	private static final int THREAD_NUM = 10;
	
	private static class Worker implements Runnable{
		
		private CyclicBarrier cyclicBarrier;
		
		public Worker(CyclicBarrier cyclicBarrier){
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName()+"waiting ...");
				cyclicBarrier.await();
				System.out.println(Thread.currentThread().getName()+"keep working ...");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static void main(String[] args){
		CyclicBarrier cb = new CyclicBarrier(THREAD_NUM, new Runnable() {
			 //当所有线程到达barrier时执行
			 @Override
			 public void run() {
				 System.out.println("waiting for condition....");
			}
		});
		for(int i=0;i<10;i++){
			new Thread(new Worker(cb)).start();
		}
	}

}
