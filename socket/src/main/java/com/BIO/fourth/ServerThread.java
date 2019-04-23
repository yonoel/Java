package com.BIO.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
    public Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String string = br.readLine();
                for (Socket item : SocketService.socketList) {
                    PrintWriter pw = new PrintWriter(item.getOutputStream());
                    pw.println("用户的信息:" + socket.getPort());
                    pw.flush();
                }
            }
        } catch (Exception e) {
        }
    }
}
