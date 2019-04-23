package com.BIO.third;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程的状态 Socket实现多个客户端向服务器端通信 服务器没有响应回去
 */
public class SocketService {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5208);
        System.out.println("server has bootstraped");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("有人上线了" + socket.getInetAddress() + ":" + socket.getPort());
            new Thread(new ServerThread(socket)).start();
        }
    }
}
