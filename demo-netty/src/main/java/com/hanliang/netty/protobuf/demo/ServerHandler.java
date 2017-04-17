package com.hanliang.netty.protobuf.demo;

import com.google.protobuf.ByteString;
import com.googlecode.protobuf.netty.NettyRpcProto;
import com.googlecode.protobuf.netty.NettyRpcProto.RpcRequest;
import com.googlecode.protobuf.netty.NettyRpcProto.RpcRequest.Builder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<NettyRpcProto.RpcRequest>{

	@Override
	protected void channelRead0(ChannelHandlerContext handlerContext, RpcRequest paramI) throws Exception {
		System.out.println("服务器收到请求："+paramI.getId()+paramI.getMethodName());
		
		Builder builder = NettyRpcProto.RpcRequest.newBuilder();
		builder.setId(22).setMethodName("test").setServiceName("test").setIsBlockingService(false);
		builder.setRequestMessage(ByteString.copyFrom("test".getBytes()));
		handlerContext.writeAndFlush(builder.build());
		
	}

}
