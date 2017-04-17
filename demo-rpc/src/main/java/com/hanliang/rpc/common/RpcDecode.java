package com.hanliang.rpc.common;

import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class RpcDecode extends ByteToMessageDecoder{
	
	private Class<?> genericClass;

	public RpcDecode(Class<?> genericClass) {
		this.genericClass = genericClass;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 4) {
			return;
		}
		in.markReaderIndex();
		int dataLength = in.readInt();
		if (dataLength < 0) {
			ctx.close();
		}
		if (in.readableBytes() < dataLength) {
			in.resetReaderIndex();
		}
		byte[] data = new byte[dataLength];
		in.readBytes(data);
		System.out.println("接收到的数据");
		System.out.println(Arrays.toString(data));
		Object obj = SerializationUtils.deserializer(data, genericClass);
		out.add(obj);
	}

}
