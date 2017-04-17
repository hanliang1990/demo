package netty.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	
	private int port;
	
	public NettyServer(int port){
		this.port = port;
	}
	
	public static void main(String[] args){
		
		NettyServer ns = new NettyServer(8009);
		try {
			ns.server();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void server() throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
	        /**
	         * ServerBootstrap 是一个启动NIO服务的辅助启动类
	         * 你可以在这个服务中直接使用Channel
	         */
	        ServerBootstrap b = new ServerBootstrap();
	        /**
	         * 这一步是必须的，如果没有设置group将会报java.lang.IllegalStateException: group not set异常
	         */
	        b = b.group(bossGroup, workerGroup);
	        /***
	         * ServerSocketChannel以NIO的selector为基础进行实现的，用来接收新的连接
	         * 这里告诉Channel如何获取新的连接.
	         */
	        b = b.channel(NioServerSocketChannel.class);
            /***
             * 这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。
             * ChannelInitializer是一个特殊的处理类，
             * 他的目的是帮助使用者配置一个新的Channel。
             * 也许你想通过增加一些处理类比如NettyServerHandler来配置一个新的Channel
             * 或者其对应的ChannelPipeline来实现你的网络程序。
             * 当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，
             * 然后提取这些匿名类到最顶层的类上。
             */
            b = b.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                   //ch.pipeline().addLast(new DiscardServerHandler());//demo1.discard
                   //ch.pipeline().addLast(new ResponseServerHandler());//demo2.echo
                   ch.pipeline().addLast(new ResponseServerHandler());//demo3.time
                }
            });
            /***
             * 你可以设置这里指定的通道实现的配置参数。
             * 我们正在写一个TCP/IP的服务端，
             * 因此我们被允许设置socket的参数选项比如tcpNoDelay和keepAlive。
             * 请参考ChannelOption和详细的ChannelConfig实现的接口文档以此可以对ChannelOptions的有一个大概的认识。
             */
            b = b.option(ChannelOption.SO_BACKLOG, 128);
            /***
             * option()是提供给NioServerSocketChannel用来接收进来的连接。
             * childOption()是提供给由父管道ServerChannel接收到的连接，
             * 在这个例子中也是NioServerSocketChannel。
             */
            b = b.childOption(ChannelOption.SO_KEEPALIVE, true);
            /***
             * 绑定端口并启动去接收进来的连接
             */
            ChannelFuture f = b.bind(port).sync();
            /**
             * 这里会一直等待，直到socket被关闭
             */
            f.channel().closeFuture().sync();
        } finally {
            /***
             * 优雅关闭
             */
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
	}
	
}
