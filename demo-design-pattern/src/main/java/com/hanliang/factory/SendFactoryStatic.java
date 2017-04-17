package com.hanliang.factory;

public class SendFactoryStatic {
	
	public static Sender produceMail(){
		return new MailSender();
	}
	
	public static Sender produceSns(){
		return new SnsSender();
	}

}
