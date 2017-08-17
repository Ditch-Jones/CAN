package com.example.joshi.can.Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    String str;
    public static void main (String [] args) throws Exception{
        Server server = new Server();
        server.run();
    }
    @Override
    public void run(){
        try{
            System.out.println("Server is started");
            ServerSocket ss = new ServerSocket(9999);

            System.out.println("Server is waiting for request");
            Socket s = ss.accept();

            System.out.println("Cliet Connected");

            BufferedReader br = new BufferedReader (new InputStreamReader(s.getInputStream()));
            str = br.readLine();

            System.out.println("Client Data: " + str);

            ss.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getStr(){
        return str;
    }
}
