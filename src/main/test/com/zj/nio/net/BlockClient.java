package com.zj.nio.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2022-08-14
 */
public class BlockClient {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8081));
        System.out.println("waiting...");
    }
}
