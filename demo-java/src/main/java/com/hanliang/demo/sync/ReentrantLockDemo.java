package com.hanliang.demo.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
	
	static class BoundedBuffer{
		final ReentrantLock lock = new ReentrantLock();
		final Condition takeLock = lock.newCondition();
		final Condition putLock = lock.newCondition();
		
		final Object[] items = new Object[100];
		int put,take,count;
		
		public void put(Object item){
			lock.lock();
			System.out.println(count);
			try {
				while(count == items.length){ //数据元素满了，put操作等待
					System.out.println("等待消费者");
					putLock.await();
				}
				items[count++] = item;
				takeLock.signal();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				System.out.println("put释放锁");
				lock.unlock();
			}
		}
		
		public void take(){
			lock.lock();
			System.out.println(count);
			try{
				while(count == 0){
					System.out.println("等待生产者");
					takeLock.await();
				}
				--count;
				putLock.signal();
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				System.out.println("take释放锁");
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args){
		final BoundedBuffer bb = new BoundedBuffer();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<100;i++){
					bb.put(Integer.valueOf(i));
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<100;i++){
					bb.take();
				}
			}
		});
		t1.start();
		t2.start();
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
