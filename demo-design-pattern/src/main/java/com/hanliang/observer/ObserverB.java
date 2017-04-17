package com.hanliang.observer;

public class ObserverB implements Observer{

	@Override
	public void update() {
		System.out.println("B received update");
	}

}
