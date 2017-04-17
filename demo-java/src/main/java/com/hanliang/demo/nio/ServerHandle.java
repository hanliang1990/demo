package com.hanliang.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerHandle implements Runnable{
	
	private Selector select;
	
	private ServerSocketChannel serverSocketChannel;
	
	private volatile boolean start;
	
	public ServerHandle(int port){
		
		try {
			//创建选择器  
			select = Selector.open();
			//打开监听通道  
			serverSocketChannel = ServerSocketChannel.open();
			//如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式  
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
			serverSocketChannel.register(select, SelectionKey.OP_ACCEPT);
			start = true;
			System.out.println("服务器已启动");
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
		while(start){
			try {
				//无论是否有读写事件发生，selector每隔1s被唤醒一次
				select.select(1000);
				//阻塞,只有当至少一个注册的事件发生的时候才会继续.  
				//select.select();
				Set<SelectionKey> keys = select.selectedKeys();
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
		if(select != null){
			try {
				select.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void handleInput(SelectionKey key) throws IOException{
		if(key.isValid()){
			if(key.isAcceptable()){
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				//通过ServerSocketChannel的accept创建SocketChannel实例  
                //完成该操作意味着完成TCP三次握手，TCP物理链路正式建立  
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(select,SelectionKey.OP_READ);
			}
			
			if(key.isReadable()){
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer bb = ByteBuffer.allocate(1024);
				int len = sc.read(bb);
				if(len > 0){
					bb.flip();
					byte[] b = new byte[bb.remaining()];
					bb.get(b);
					String content = new String(b,"UTF-8");
					System.out.println(content);
					doResponse(sc);
				}else if(len < 0){
					key.cancel();
					sc.close();
				}
			}
		}
	}
	
	public void doResponse(SocketChannel sc) throws IOException{
		byte[] b = "response".getBytes();
		ByteBuffer bb = ByteBuffer.allocate(b.length);
		bb.put(b);
		bb.flip();
		sc.write(bb);
	}

}
