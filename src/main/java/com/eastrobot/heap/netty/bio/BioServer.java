/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.netty.bio;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/19 14:49
 */
public class BioServer {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            new Thread(()->{
                while (true){
                    byte[] bytes = new byte[1024];
                    try {
                        socket.getInputStream().read(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
