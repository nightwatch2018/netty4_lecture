package com.chris.nettylecture.hellonetty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel>{
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pl = ch.pipeline();
		pl.addLast("httpServerCodec", new HttpServerCodec());
		pl.addLast("testHttpServerHandler", new TestHttpServerHandler());
		
	}
}
