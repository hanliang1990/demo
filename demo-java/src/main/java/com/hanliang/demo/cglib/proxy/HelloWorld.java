package com.hanliang.demo.cglib.proxy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HelloWorld {
	
	public void sayHello(){
		System.out.println("Hello World!");
	}
	
	public static void main(String[] args){
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			String p = it.next();
			if("b".equals(p)){
				list.remove("c");
			}
		}
		for(String t:list){
			System.out.println(t);
		}
	}

}
