package com.hanliang.decorator;

public class Source implements Sourceable{

	@Override
	public void method() {
		System.out.println("this is origin method");
	}

}
