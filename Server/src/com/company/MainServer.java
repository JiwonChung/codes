package com.company;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) {

        try {
            ServerSocket s_socket = new ServerSocket(9999);
            Socket c_socket = s_socket.accept();


            ReceiveThread receiveThread = new ReceiveThread(c_socket);

            SendThread sendThread = new SendThread(c_socket);


            receiveThread.start();
            sendThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
