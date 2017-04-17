package com.hanliang.netty.protobuf.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.google.protobuf.ByteString;
import com.googlecode.protobuf.netty.NettyRpcProto;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class Client {
	public static void main(String[] args) {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup);
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());//解码
					ch.pipeline().addLast(new ProtobufDecoder(NettyRpcProto.RpcRequest.getDefaultInstance()));
					ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());//在数据前加上一个int型数据表示传输数据的大小
					ch.pipeline().addLast(new ProtobufEncoder());
					ch.pipeline().addLast(new ClientHandler());
				};
			});
	
			Channel ch = bootstrap.connect("127.0.0.1", 8888).sync().channel();
	
			// 控制台输入
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			for (;;) {
				String line = in.readLine();
				if (line == null || "".equals(line)) {
					continue;
				}
				NettyRpcProto.RpcRequest.Builder builder = NettyRpcProto.RpcRequest.newBuilder();
				builder.setId(222);
				builder.setServiceName("test");
				builder.setIsBlockingService(false);
				builder.setRequestMessage(ByteString.copyFrom("test".getBytes()));
				builder.setMethodName(line);
				ch.writeAndFlush(builder.build());
				ch.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}
