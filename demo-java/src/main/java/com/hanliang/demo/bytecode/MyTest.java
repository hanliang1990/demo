package com.hanliang.demo.bytecode;

import java.io.File;
import java.io.FileInputStream;

public class MyTest{
	
	public static void main(String[] args) throws Exception, IllegalAccessException{
		File file = new File(".");
		System.out.println(file.getCanonicalFile());
//		FileInputStream input = new FileInputStream(file.getCanonicalFile()+"\\target\\classes\\com\\hanliang\\asm\\Programmer.class");
		FileInputStream input = new FileInputStream("D://Example.class");
		byte[] result = new byte[1024];
		
		int count = input.read(result);
		MyClassLoader loader = new MyClassLoader();
		Class<?> clazz = loader.defuneMyClass(result, 0, count);
		System.out.println(clazz.getCanonicalName());
		Object o = clazz.newInstance();
		try{
			clazz.getMethod("code", null).invoke(o, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		input.close();
	}

}
