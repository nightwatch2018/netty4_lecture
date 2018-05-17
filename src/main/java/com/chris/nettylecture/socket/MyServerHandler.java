package com.chris.nettylecture.socket;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<String>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("-----------------------------------------------------");
		System.out.println(ctx.channel().remoteAddress() + "," + msg);
		//返回给客户端消息的时机就是在read0方法读取完消息后
		ctx.channel().writeAndFlush("from server:" + UUID.randomUUID());
		System.out.println("-----------------------------------------------------");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
