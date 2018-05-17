package com.chris.nettylecture.socket;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<String>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

		System.out.println(ctx.channel().remoteAddress() + "," + msg);
		ctx.writeAndFlush("from client : " + LocalDateTime.now());
		
	}
	
	/**
	 * 利用这个回调方法来打破客户端与服务器端都启动后双方无法接收消息的情况
	 * 
	 * 实际运行结果就是服务器端和客户端无限打印接收到的消息
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush("from client's greeting!");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}

