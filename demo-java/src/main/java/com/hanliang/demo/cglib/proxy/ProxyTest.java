package com.hanliang.demo.cglib.proxy;

import net.sf.cglib.proxy.Enhancer;

public class ProxyTest {
	
	public static void main(String[] args){
		HelloWorld helloWorld = new HelloWorld();
		CglibProxy cp = new CglibProxy();
		Enhancer en = new Enhancer();
		en.setSuperclass(helloWorld.getClass());
		en.setCallback(cp);
		HelloWorld proxy = (HelloWorld) en.create();
		proxy.sayHello();
	}

}
