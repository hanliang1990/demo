package com.hanliang.java.nio;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelExample {
	
	public static void main(String[] args) throws Exception{
		
		DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(9999));
		//接收数据
//		ByteBuffer buf = ByteBuffer.allocate(48);
//		buf.clear();
//		channel.receive(buf);
		
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		int bytesSent = channel.send(buf, new InetSocketAddress("jenkov.com", 80));
		
	}
	

}
