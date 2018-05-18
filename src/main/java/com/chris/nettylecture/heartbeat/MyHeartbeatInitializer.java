package com.chris.nettylecture.heartbeat;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class MyHeartbeatInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pl = ch.pipeline();
		//空闲检测
		pl.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));
		pl.addLast(new MyHeartbeatHandler());
	}
}
