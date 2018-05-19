package com.chris.nettylecture.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest2 {

	public static void main(String[] args) throws Exception {
		
		FileInputStream fis = new FileInputStream("NIOTest2.txt");
		FileChannel channel = fis.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(512);
		channel.read(buffer);
		buffer.flip();
		
		while(buffer.remaining() > 0){
			byte b = buffer.get();
			System.out.println((char)b);
		}
		fis.close();
		
		
	}

}
