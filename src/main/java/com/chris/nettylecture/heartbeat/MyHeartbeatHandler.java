package com.chris.nettylecture.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyHeartbeatHandler extends ChannelInboundHandlerAdapter{

	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof IdleStateEvent){
			
			IdleStateEvent idleStateEvent = (IdleStateEvent)evt;
			String eventType = "";
			
			switch (idleStateEvent.state()){
				case READER_IDLE: 
					eventType = "reader idle";
					break;
				case WRITER_IDLE:
					eventType = "writer idle";
					break;
				case ALL_IDLE:
					eventType = "reader and writer idle";
					break;
			}
			
			System.out.println(ctx.channel().remoteAddress() + ", timeout event :" + eventType);
			ctx.channel().close();
		}
	}

}
