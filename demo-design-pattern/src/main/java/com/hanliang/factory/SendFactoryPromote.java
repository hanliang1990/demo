package com.hanliang.factory;

/**
 * 
 * 多个工厂方法模式
 * @author liang
 *
 */
public class SendFactoryPromote {
	
	public Sender produceMail(){
		return new MailSender();
	}
	
	public Sender produceSns(){
		return new SnsSender();
	}
}
