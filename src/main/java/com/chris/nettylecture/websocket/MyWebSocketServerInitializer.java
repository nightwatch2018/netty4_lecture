package com.chris.nettylecture.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyWebSocketServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {

		ChannelPipeline pl = ch.pipeline();
		
		pl.addLast(new HttpServerCodec());
		pl.addLast(new ChunkedWriteHandler());
		//Netty对于请求采取分块（段）的方式，请求长度为1000，会切分成 N个段，每一段都会完整的走一遍流程
		//将这10个段聚合到一起形成完整的HTTP请求或是响应
		pl.addLast(new HttpObjectAggregator(8192));
		
		pl.addLast(new WebSocketServerProtocolHandler("/ws"));
		
		pl.addLast(new MyWebSocketServerHandler());
		
	}
}
