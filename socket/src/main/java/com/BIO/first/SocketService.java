package com.BIO.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这个服务器没有向客户端输出
 */
public class SocketService {
    //搭建服务器端
    public static void main(String[] args) throws IOException {
        //创建服务器
        ServerSocket server=new ServerSocket(5209);
        System.out.println("服务器启动成功");
        //等待客户端连接后，接收客户端socket -->阻塞
        Socket socket=server.accept();
        //获取客户端socket的输入流
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while(true){
            //等待客户端socket的不为空输入流-->也会阻塞
            String str = in.readLine();
            if (str == null) {
                break;
            }
            System.out.println("客户端说：" + str);
        }
        in.close(); //关闭Socket输入流
        socket.close(); //关闭Socket
        server.close(); //关闭ServerSocket
    }

}
