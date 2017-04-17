package com.hanliang.netty.protobuf.demo;

import com.googlecode.protobuf.netty.NettyRpcProto;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class Server {
	
	public static void main(String[] args){
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		ServerBootstrap sb = new ServerBootstrap();
		sb.group(boosGroup,workGroup);
		sb.channel(NioServerSocketChannel.class);
		sb.childHandler(new ChannelInitializer<Channel>(){

			@Override
			protected void initChannel(Channel ch) throws Exception {
				
				ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());//解码
				ch.pipeline().addLast(new ProtobufDecoder(NettyRpcProto.RpcRequest.getDefaultInstance()));
				
				ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());//在数据前加上一个int型数据表示传输数据的大小
				ch.pipeline().addLast(new ProtobufEncoder());
				
				ch.pipeline().addLast("handler", new ServerHandler());
			}
		});
		sb.childOption(ChannelOption.SO_KEEPALIVE, true);
		ChannelFuture channelFuture;
		try {
			channelFuture = sb.bind(8888).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			boosGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
		
	}

}
