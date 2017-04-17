package com.hanliang.abstractfactory;

public class SnsSender implements Sender{

	@Override
	public void send(String msg) {

		System.out.println("send by sns "+msg);
	}

}
