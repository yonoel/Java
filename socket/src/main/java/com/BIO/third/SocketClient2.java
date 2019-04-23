package com.BIO.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient2 {
    public static void main(String args[]) throws Exception {
        Socket socket = new Socket("192.168.10.2", 5208);
        System.out.println("小二连接成功");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        while (true) {
            pw.println("小二说：" + br.readLine());
            pw.flush();
        }
    }
}
