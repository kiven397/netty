package com.icc.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author K
 * @Date 2020/4/25 15:42
 * @Version 1.0
 */
public class NioServer {
    public static void main(String[] args) throws Exception{
//        从本地读取文本
//        FileInputStream fileInputStream = new FileInputStream("d:\\file.txt");
//        FileChannel channel = fileInputStream.getChannel();
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        channel.read(buffer);
//        System.out.println(new String(buffer.array()));
//        将信息写入文本
//        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file.txt");
//        FileChannel channel = fileOutputStream.getChannel();
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        String str = "hello.icc";
//        buffer.put(str.getBytes());
//        buffer.flip();
//        channel.write(buffer);
//        fileOutputStream.close();

//        String str = "com.icc 1.txt";
//        FileOutputStream fileOutputStream = new FileOutputStream("d:\\1.txt");
//        FileChannel channel = fileOutputStream.getChannel();
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        buffer.put(str.getBytes());
//        buffer.flip();
//        channel.write(buffer);

        FileInputStream inputStream = new FileInputStream("d:\\1.txt");
        FileChannel channel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\2.txt");
        FileChannel channel1 = fileOutputStream.getChannel();
        while (true) {
            buffer.clear();
            int read = channel.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip();
            channel1.write(buffer);
        }



        inputStream.close();
        fileOutputStream.close();
    }
}
