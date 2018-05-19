package com.chris.nettylecture.hellonetty;

import java.net.URI;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		System.out.println("msg class : " + msg.getClass() + "," +  ctx.hashCode());
		if(msg instanceof HttpRequest){
			
			HttpRequest request = (HttpRequest)msg;
			System.out.println("request method name: " + request.method().name() + "," +  ctx.hashCode());
			
			URI uri = new URI(request.uri());
			if("/favicon.ico".equals(uri.getPath())){
				System.out.println("request favicon.ico...." + ctx.hashCode());
				ctx.channel().close();
				return;
			}
			 //1.封装响应报文对象
			ByteBuf content = Unpooled.copiedBuffer("Hello Netty",CharsetUtil.UTF_8);
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
					HttpResponseStatus.OK,content);
			//2.设置响应报文Header信息
			response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
			//3.向客户端返回响应报文
			ctx.writeAndFlush(response);
			//4.关闭与客户端的连接
			//可以判断客户端是HTTP1.1协议或keep-alived超时时间，可以选择手动关闭与客户端的连接
			ctx.channel().close();
		}
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive....................." + ctx.hashCode());
		super.channelActive(ctx);
	}
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelRegistered....................." + ctx.hashCode());
		super.channelRegistered(ctx);
	}
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelUnregistered....................." + ctx.hashCode());
		super.channelUnregistered(ctx);
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerAdded....................." + ctx.hashCode());
		super.handlerAdded(ctx);
	}
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerRemoved....................." + ctx.hashCode());
		super.handlerRemoved(ctx);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelInactive....................." + ctx.hashCode());
		super.channelInactive(ctx);
	}
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("channelRead....................." + ctx.hashCode());
		super.channelRead(ctx, msg);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete....................." + ctx.hashCode());
		super.channelReadComplete(ctx);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelWritabilityChanged....................." + ctx.hashCode());
		super.channelWritabilityChanged(ctx);
	}
		
}
