package com.BIO.fourth;



import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Socket实现客户端与客户端通信
 */
public class SocketService {
    public static List<Socket> socketList = new ArrayList<>();

    public static void main(String[] args) throws  Exception{
        ServerSocket serverSocket = new ServerSocket(5208);
        System.out.println("启动服务器");
        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("有人上线:"+socket.getPort());
            socketList.add(socket);
            new Thread(new ServerThread(socket)).start();
        }
    }
}
