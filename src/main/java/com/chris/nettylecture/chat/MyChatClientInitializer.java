package com.chris.nettylecture.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyChatClientInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		
		ChannelPipeline pl = ch.pipeline();
		pl.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
		pl.addLast(new StringDecoder(CharsetUtil.UTF_8));
		pl.addLast(new StringEncoder(CharsetUtil.UTF_8));
		pl.addLast(new MyChatClientHandler());
		
		
	}
}
