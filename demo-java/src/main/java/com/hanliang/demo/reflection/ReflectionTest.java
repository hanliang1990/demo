package com.hanliang.demo.reflection;

public class ReflectionTest {
	
	public static void main(String[] args) throws Exception{


		Class<?> cl = Class.forName("com.hanliang.demo.reflection.Example");
		Class<?> su = cl.getSuperclass();
		System.out.println(su);
		
	}

}
