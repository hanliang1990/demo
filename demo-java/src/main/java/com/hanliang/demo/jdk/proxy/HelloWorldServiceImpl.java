package com.hanliang.demo.jdk.proxy;

public class HelloWorldServiceImpl implements HelloWorldService{

	@Override
	public String sayHello() {
		System.out.println("Hello");
		return "World";
	}

}
