package com.BIO.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                String string = bufferedReader.readLine();
                System.out.println(string);
            }
        }catch (Exception e){}
    }
}
