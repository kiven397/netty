package com.icc.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @Author K
 * @Date 2020/4/25 17:41
 * @Version 1.0
 */
public class TransferFromTest {
    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream("d:\\a.jpg");
        FileOutputStream outputStream = new FileOutputStream("d:\\b.jpg");
        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();
        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());
        inputStreamChannel.close();
        outputStreamChannel.close();
        inputStream.close();
        outputStream.close();
    }
}
