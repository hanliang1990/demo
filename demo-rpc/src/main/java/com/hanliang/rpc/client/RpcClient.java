package com.hanliang.rpc.client;

import com.hanliang.rpc.common.RpcDecode;
import com.hanliang.rpc.common.RpcEncode;
import com.hanliang.rpc.common.RpcRequest;
import com.hanliang.rpc.common.RpcResponse;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


public class RpcClient extends SimpleChannelInboundHandler<RpcResponse> {


	private String host;
	private int port;

	private RpcResponse response;

	private final Object obj = new Object();

	public RpcClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
		this.response = response;
		synchronized (obj) {
			obj.notifyAll(); // 收到响应，唤醒线程
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

	public RpcResponse send(RpcRequest request) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel channel) throws Exception {
					channel.pipeline().addLast(new RpcEncode(RpcRequest.class)) // 将 RPC 请求进行编码（为了发送请求）
							.addLast(new RpcDecode(RpcResponse.class)) // 将 RPC 响应进行解码（为了处理响应）
							.addLast(RpcClient.this); // 使用 RpcClient 发送 RPC 请求
				}
			}).option(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().writeAndFlush(request).sync();

			synchronized (obj) {
				obj.wait(); // 未收到响应，使线程等待
			}

			if (response != null) {
				future.channel().closeFuture().sync();
			}
			return response;
		} finally {
			group.shutdownGracefully();
		}
	}
}