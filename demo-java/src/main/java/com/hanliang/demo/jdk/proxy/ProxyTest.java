package com.hanliang.demo.jdk.proxy;

public class ProxyTest {
	
	public static void main(String[] args){
		
		HelloWorldServiceImpl helloWorldServiceImpl = new HelloWorldServiceImpl();
		DynamicProxy dynamicProxy  = new DynamicProxy(helloWorldServiceImpl);
		HelloWorldService helloWorldService = (HelloWorldService) dynamicProxy.getProxy();
		helloWorldService.sayHello();
	}

}
