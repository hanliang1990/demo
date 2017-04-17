package com.hanliang.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ClientHandle implements Runnable{
	
	private int port;
	private String ip;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean start;
	
	public ClientHandle(String ip,int port){
		this.ip = ip;
		this.port = port;
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			start = true;
			System.out.println("客户端已启动");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop(){
		start = false;
	}
	
	@Override
	public void run() {
		try {
			connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(start){
			try {
				selector.select(1000);
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey  key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					try{
						handleInput(key);
					}catch(Exception e){
						if(key != null){
							key.cancel();
							if(key.channel() != null){
								key.channel().close();
							}
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(selector != null){
			try {
				selector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void handleInput(SelectionKey key) throws IOException{
		if(key.isValid()){
			SocketChannel sc = (SocketChannel) key.channel();
			if(key.isConnectable()){
				if(sc.finishConnect()){}
				else{
					System.exit(1);
				}
			}
			
			if(key.isReadable()){
				ByteBuffer bb = ByteBuffer.allocate(1024);
				int len = sc.read(bb);
				if(len > 0){
					bb.flip();
					byte[] b = new byte[bb.remaining()];
					bb.get(b);
					String content = new String(b,"UNF-8");
					System.out.println("客户端收到消息: "+content);
				}else if(len < 0){
					key.cancel();
					sc.close();
				}
			}
		}
	}
	
	public void doResponse(SocketChannel sc,String message) throws IOException{
		byte[] b = message.getBytes();
		ByteBuffer bb = ByteBuffer.allocate(b.length);
		bb.put(b);
		bb.flip();
		sc.write(bb);
	}
	
	public void connect() throws IOException{
		if(socketChannel.connect(new InetSocketAddress(ip,port)));
		else socketChannel.register(selector, SelectionKey.OP_CONNECT);
	}

	public void sendMessage(String message){
		try {
			socketChannel.register(selector, SelectionKey.OP_READ);
			doResponse(socketChannel,message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
