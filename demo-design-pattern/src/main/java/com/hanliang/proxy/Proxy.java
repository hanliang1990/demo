package com.hanliang.proxy;

/**
 * 
 * 和代理模式很像？
 * @author liang
 *
 */
public class Proxy implements Sourceable{
	
	private Sourceable sourceable;
	
	public Proxy(Sourceable sourceable){
		this.sourceable = sourceable;
	}

	@Override
	public void method() {
		System.out.println("before decorator");
		sourceable.method();
		System.out.println("after decorator");
	}

}
