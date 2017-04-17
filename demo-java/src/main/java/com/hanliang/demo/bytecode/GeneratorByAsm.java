package com.hanliang.demo.bytecode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class GeneratorByAsm {
	
	public static void main(String[] args) throws IOException{
		ClassWriter cw = new ClassWriter(0);
		//类头部信息
		cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC, "Example", null, "java/lang/Object", null);
		
		//创建构造函数
		MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		mv.visitVarInsn(Opcodes.AALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(1, 1);
		mv.visitEnd();
		
		//定义code方法
		MethodVisitor mv2 = cw.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V", null, null);
		mv2.visitCode();
		mv2.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream");
		mv2.visitLdcInsn("I'm a Programmer,just coding...");
		mv2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
		mv2.visitInsn(Opcodes.RETURN);
		mv2.visitMaxs(2, 2);
		mv2.visitEnd();
		cw.visitEnd();
		
		byte[] data = cw.toByteArray();
		
		
		File file = new File("D://Example.class");
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(data);
		fout.close();
		
		
	}

}
