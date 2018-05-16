package com.chris.nettylecture.firstexample;

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
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap sbs = new ServerBootstrap();
			sbs.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
				.childHandler(new TestServerInitializer());
			
			ChannelFuture channelFuture = sbs.bind(8899).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
