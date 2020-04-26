package com.icc.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author K
 * @Date 2020/4/25 22:11
 * @Version 1.0
 */
public class NIOServerTest {
    public static void main(String[] args) throws Exception {
        //创建连接通道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        //设置监听的端口
        socketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //创建Selector选择器
        Selector selector = Selector.open();
        //将连接通道绑定到Selector上
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            //阻塞1秒 如果依然没有时间驱动 直接continue
            if (selector.select(1000) == 0) {
                System.out.println("暂时无连接等待了1秒");
                continue;
            }
            //如果返回大于0
            // selector.selectedKeys()返回有事件驱动的通道的key的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                //如果当前事件是连接 则为当前连接开辟通道
                if (key.isAcceptable()) {
                    SocketChannel channel = socketChannel.accept();
                    System.out.println("有客户端进行了连接");
                    channel.configureBlocking(false);
                    //将当前通道绑定在selector上
                    channel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel)key.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("form 客户端:"+new String(buffer.array()));
                }
                keyIterator.remove();
            }
        }

    }
}
