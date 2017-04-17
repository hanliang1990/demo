package com.hanliang.netty.protobuf.demo;

import com.googlecode.protobuf.netty.NettyRpcProto;
import com.googlecode.protobuf.netty.NettyRpcProto.RpcRequest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<NettyRpcProto.RpcRequest>{

	@Override
	protected void channelRead0(ChannelHandlerContext paramChannelHandlerContext, RpcRequest paramI) throws Exception {
		System.out.println("客户端收到响应："+paramI.getId()+paramI.getMethodName());
	}

}
