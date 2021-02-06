//package com.company;
//
//import java.io.IOException;
//import java.net.Socket;
//
//public class ChatClient {
//
//    public static String UserID = "Jiwon";
//
//    public static void main(String[] args) {
//        new GUI();
//        try {
//            Socket c_socket = new Socket("192.168.56.1", 9999);
//
//            ReceiveThread receiveThread = new ReceiveThread(c_socket);
//
//            SendThread sendThread = new SendThread(c_socket);
//
//
//            receiveThread.start();
//            sendThread.start();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
