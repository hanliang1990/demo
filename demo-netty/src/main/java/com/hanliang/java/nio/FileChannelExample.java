package com.hanliang.java.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelExample {
	
	public static void main(String[] args) throws Exception{
		RandomAccessFile raf = new RandomAccessFile("E://1.txt","rw");
		FileChannel inChannel = raf.getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
			System.out.println("Read " + bytesRead);
			buf.flip();
			while(buf.hasRemaining()){
				System.out.print((char) buf.get());
			}
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		raf.close();
	}

}
