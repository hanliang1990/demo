package com.hanliang.java.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelExample {
	
	public static void main(String[] args) throws Exception{
		
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(8009));
		while(true){
		    SocketChannel socketChannel =
		            serverSocketChannel.accept();
		    if(socketChannel != null){
		    	String hello = "Hello World";
		    	ByteBuffer buf = ByteBuffer.allocate(1024);
		    	buf.put(hello.getBytes());
		    	buf.flip();
				while(buf.hasRemaining()) {
					socketChannel.write(buf);
				}
		    }
		}
	}

}
