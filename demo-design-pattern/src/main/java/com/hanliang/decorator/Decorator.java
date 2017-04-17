package com.hanliang.decorator;

/**
 * 
 *	1、需要扩展一个类的功能。

	2、动态的为一个对象增加功能，而且还能动态撤销。（继承不能做到这一点，继承的功能是静态的，不能动态增删。）

	缺点：产生过多相似的对象，不易排错！
 * @author liang
 *
 */
public class Decorator implements Sourceable{
	
	private Sourceable sourceable;
	
	public Decorator(Sourceable sourceable){
		this.sourceable = sourceable;
	}

	@Override
	public void method() {
		System.out.println("before decorator");
		sourceable.method();
		System.out.println("after decorator");
		
	}

}
