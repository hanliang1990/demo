package com.hanliang.factory;

/**
 * 
 * 普通工厂方法
 * @author liang
 * 工厂方法模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，
 * 必须对工厂类进行修改，这违背了闭包原则，所以，从设计角度考虑，有一定的问题
 *
 */
public class SendFactory {
	
	public Sender produce(String type){
		if("mail".equals(type)){
			return new MailSender();
		}else if("sns".equals(type)){
			return new SnsSender();
		}else{
			System.out.println("参数错误");
			return null;
		}
	}
}
