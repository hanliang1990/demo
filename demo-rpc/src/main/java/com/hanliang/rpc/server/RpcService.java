package com.hanliang.rpc.server;


import java.util.HashMap;
import java.util.Map;

import com.hanliang.rpc.common.RpcDecode;
import com.hanliang.rpc.common.RpcEncode;
import com.hanliang.rpc.common.RpcRequest;
import com.hanliang.rpc.common.RpcResponse;
import com.hanliang.rpc.service.HelloServiceImpl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class RpcService {

	private String IpAddress;
	
	private int port;

	private Map<String, Object> handlerMap = new HashMap<String,Object>(); // 存放接口名与服务对象之间的映射关系


	public RpcService(String IpAddress,int port) {
		this.IpAddress = IpAddress;
		this.port = port;
	}
	
	public static void main(String[] args) throws Exception{
		
		RpcService server = new RpcService("127.0.0.1",9001);
		server.init();
		server.service();
		
	}

	public void init(){
        // 获取所有带有RpcService注解的SpringBean
//        Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcService.class);
//		if (MapUtils.isNotEmpty(serviceBeanMap)) {
//			for (Object serviceBean : serviceBeanMap.values()) {
//				String interfaceName = serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
//				handlerMap.put(interfaceName, serviceBean);
//			}
//		}
		handlerMap.put("com.hanliang.service.HelloService", new HelloServiceImpl());
	}

	public void service() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel channel) throws Exception {
							channel.pipeline().addLast(new RpcDecode(RpcRequest.class)) // 将
																							// RPC
																							// 请求进行解码（为了处理请求）
									.addLast(new RpcEncode(RpcResponse.class)) // 将
																				// RPC
																				// 响应进行编码（为了返回响应）
									.addLast(new RpcHandler(handlerMap)); // 处理
																			// RPC
																			// 请求
						}
					}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture future = bootstrap.bind(IpAddress, port).sync();
			
			System.out.println("server started........");
			
			future.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}
