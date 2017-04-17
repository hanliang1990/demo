package com.hanliang.abstractfactory;

public class SenderMailFactory implements Provider{

	@Override
	public Sender produce() {
		return new MailSender();
	}

}
