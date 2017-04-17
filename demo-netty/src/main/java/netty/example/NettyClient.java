package netty.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	
	private static String host = "127.0.0.1";
	private static int port = 8009;
	
	public static void main(String[] args) throws Exception {
		
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new NettyClientHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
            Thread.sleep(5000);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
	@Test
	public void test(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -3);
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		System.out.println(cal.getTimeInMillis());
		System.out.println(test1(8));
		
		
	}
	
	
	public int test1(int n){
		int count = 0;
		while(n>0){
			n = n&(n-1);
				count++;
		}
		return count;
	}
}
