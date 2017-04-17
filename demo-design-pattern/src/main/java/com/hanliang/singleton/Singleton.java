package com.hanliang.singleton;

public class Singleton {
	
	private static Singleton singleton;
	
	private Singleton(){
		
	}
	
	public static Singleton getSingleton1(){
		if(singleton == null){
			singleton = new Singleton();
		}
		return singleton;
	}
	
	/**
	 * 
	 * synchronized关键字锁住的是这个对象，这样的用法，在性能上会有所下降，因为每次调用getInstance()，
	 * 都要对对象上锁，事实上，只有在第一次创建对象的时候需要加锁，之后就不需要了
	 * @return
	 */
	public static synchronized Singleton getSingleton2(){
		if(singleton == null){
			singleton = new Singleton();
		}
		return singleton;
	}
	
	public static Singleton getSingleton3(){
		if(singleton == null){
			synchronized (singleton) {
				if(singleton == null){
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
	
	private static class SingletonFactory {  
        private static Singleton instance = new Singleton();  
    }
	
	public static Singleton getSingleton4(){
		return SingletonFactory.instance;
	}
	
	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */  
	public Object readResolve() {  
        return singleton;  
    }  
}
