package com.chris.nettylecture.nio;


import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest9 {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:/圣思园/2.精通并发与Netty/46_字符集编解码全方位解析.mp4", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        //mappedByteBuffer.put(0, (byte)'a');
        //mappedByteBuffer.put(3, (byte)'b');

        randomAccessFile.close();
    }
}
