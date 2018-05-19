package com.chris.nettylecture.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NIOTest1 {

	public static void main(String[] args) {
		
		IntBuffer intBuffer = IntBuffer.allocate(10);
		
		for(int i=0; i < intBuffer.capacity(); i++){
			//推荐使用SecureRandom，比它的父类更随机
			int randomNumber = new SecureRandom().nextInt(30);
			intBuffer.put(randomNumber);
		}
		
		intBuffer.flip();
		while(intBuffer.hasRemaining()){
			System.out.println(intBuffer.get());
		}
	}

}
