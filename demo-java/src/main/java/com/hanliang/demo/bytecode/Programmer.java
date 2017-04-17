package com.hanliang.demo.bytecode;

import java.beans.MethodDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Programmer{
	
	public void code(){
		System.out.println("Hello World!");
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception, SecurityException{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("1", "2");
		Method[] methods = map.getClass().getDeclaredMethods();
		Field filed = map.getClass().getDeclaredField("threshold");
		System.out.println(Integer.highestOneBit(15));
		filed.setAccessible(true);
		System.out.println(filed.get(map));
		for(int i=0;i<methods.length;i++){
//			System.out.println(methods[i].getName());
			if("capacity".equals(methods[i].getName())){
				System.out.println(1);
				methods[i].setAccessible(true);
				System.out.println(methods[i].invoke(map, null));
			}
		}
	}
	 private static int roundUpToPowerOf2(int number) {
	        // assert number >= 0 : "number must be non-negative";
	        return number >= 1000000000
	                ? 1000000000
	                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
	    }
}
