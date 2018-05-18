package com.chris.nettylecture.websocket;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class MyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		System.out.println("msg :" + msg.text());
		
		ctx.channel().writeAndFlush(new TextWebSocketFrame("server time: " + LocalDateTime.now()));
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerAdded...." + ctx.channel().id().asLongText());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerRemoved...." + ctx.channel().id().asLongText());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
