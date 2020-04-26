package com.icc.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author K
 * @Date 2020/4/25 13:39
 * @Version 1.0
 */
public class BIOServer {
    public static void main(String[] args) throws Exception{
        //创建线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 10, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            poolExecutor.execute(() ->{
                handler(socket);
            });
        }
    }

    public static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
