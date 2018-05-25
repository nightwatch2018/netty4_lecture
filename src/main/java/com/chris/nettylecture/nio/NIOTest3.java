  package com.chris.nettylecture.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;

public class NIOTest3 {

	public static void main(String[] args) throws Exception {
		
		FileOutputStream fos = new FileOutputStream("NIOTest3.txt");
		FileChannel channel = fos.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(512);
		byte[] message = "Hello NIO".getBytes();
		
		for(int i = 0; i < message.length; i++){
			buffer.put(message[i]);
			
		}
		buffer.flip();
		channel.write(buffer);
		fos.close();
		
		
	}

}
