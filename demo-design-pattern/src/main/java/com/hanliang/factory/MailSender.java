package com.hanliang.factory;

public class MailSender implements Sender{

	@Override
	public void send(String msg) {
		System.out.println("send by mail "+msg);
	}

}
