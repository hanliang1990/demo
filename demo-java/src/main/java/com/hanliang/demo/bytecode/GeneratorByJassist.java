package com.hanliang.demo.bytecode;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class GeneratorByJassist {
	
	public static void main(String[] args) throws CannotCompileException, IOException{
		
		ClassPool cp = ClassPool.getDefault();
		CtClass cc = cp.makeClass("com.hanliang.asm.JassistExample");
		CtMethod cm = CtMethod.make("public void coude(){}", cc);
		cm.insertBefore("System.out.println(\"Hello World!\");");
		cc.addMethod(cm);
		cc.writeFile("D://JassistExample");
		
	}

}
