package com.hanliang.observer;

public class ObserverA implements Observer{

	@Override
	public void update() {
		System.out.println("A received update");
	}

}
