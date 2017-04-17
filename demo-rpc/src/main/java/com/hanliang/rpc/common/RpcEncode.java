package com.hanliang.rpc.common;

import java.util.Arrays;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncode extends MessageToByteEncoder<Object>{
	
	private Class<?> genericClass;
	
	public RpcEncode(Class<?> genericClass){
		this.genericClass = genericClass;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
		if (genericClass.isInstance(in)) {
            byte[] data = SerializationUtils.serializer(in);
            System.out.println("加密之后。。。。。");
            System.out.println(Arrays.toString(data));
            out.writeInt(data.length);
            out.writeBytes(data);
        }
	}

}
