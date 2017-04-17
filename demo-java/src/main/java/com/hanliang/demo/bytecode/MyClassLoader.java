package com.hanliang.demo.bytecode;

public class MyClassLoader extends ClassLoader{
	
	public Class<?> defuneMyClass(byte[] b,int off,int len){
		return super.defineClass(null, b, off, len, null);
	}

}
