package com.zj.nio.net;

import java.io.IOException;
import java.net.Socket;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2022-08-14
 */
public class SocketClientTest {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8081)) {
            System.out.println(socket);
            socket.getOutputStream().write("world".getBytes());
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
