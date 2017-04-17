package com.hanliang.abstractfactory;

public class SenderSnsFactory implements Provider{

	@Override
	public Sender produce() {
		return new SnsSender();
	}

}
