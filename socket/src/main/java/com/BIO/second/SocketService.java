package com.BIO.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这个服务器端向客户端输出
 */
public class SocketService {
    //搭建服务器端
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(80);
        System.out.println("服务器启动成功");
        // accept是阻塞的
        Socket socket=server.accept();
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader out=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        while(true){
            System.out.println("客户端说："+in.readLine());
            String str = out.readLine();
            pw.println(str);
            pw.flush();
            System.out.println("服务器说："+str);
        }
    }

}
