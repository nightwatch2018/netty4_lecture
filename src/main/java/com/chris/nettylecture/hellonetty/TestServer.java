package com.chris.nettylecture.hellonetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 所有Netty程序的流程都是下面这样的
 * @author Administrator
 *
 */
public class TestServer {
	
	public static void main(String[] args)throws Exception {
		// 1.实例化两个EventLoopGroup对象
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			//2.实例化ServerBootstrap，用来配置服务器端的相关组件
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
				.childHandler(new TestServerInitializer());
			//3.绑定监听端口
			ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			//优雅关闭两个EventLoopGroup对象。
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
