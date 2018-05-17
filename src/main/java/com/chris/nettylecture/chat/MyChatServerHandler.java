package com.chris.nettylecture.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 实现广播的机制
 * 
 * 一个服务器端，N个客户端，第一个客户端与服务器端建立好连接，紧接着，第二个客户端也与服务器端建立好连接后，
 * 
 * 第一个需求在服务器端控制台与之前已连接的客户端均发送XXX已上线，并且失去连接后发送XXX已下线。
 * 
 * 第二个需求就是每当一个客户端向服务器端发送消息，都需要广播至其它在线客户端。
 * 。
 * 
 * @author Administrator
 *
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String>{
	
	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
		//获取发送消息的客户端
		Channel channel = ctx.channel();
		channelGroup.forEach(ch -> {
			if(channel != ch){
				ch.writeAndFlush(channel.remoteAddress() + " : " + msg + "\n");
			}else{
				ch.writeAndFlush("[ Me ] " + msg + "\n");
			}
		});
	}
	
	/**
	 * 连接建立
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("[ Server ] - " + channel.remoteAddress() + " join\n");
		channelGroup.add(channel);
	}
	
	/**
	 * 连接中断
	 * 无需手工将已断掉的channel从channelGroup移除，channelGroup已自动执行了这个操作
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("[ Server ] - " + channel.remoteAddress() + " leave\n");
	}
	
	/**
	 * 连接活动状态
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("[ Server ] - " + channel.remoteAddress() + " is online\n");
	}
	
	/**
	 * 连接无效状态
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("[ Server ] - " + channel.remoteAddress() + " is offline\n");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
