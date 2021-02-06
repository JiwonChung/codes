package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    public static ArrayList<PrintWriter> m_OutputList;
    public static void main(String[] args) {
	// write your code here
        m_OutputList = new ArrayList<PrintWriter>();

        try {
            ServerSocket s_socket = new ServerSocket(9999);

            while (true) {
                Socket c_socket = s_socket.accept();
                ClientManagerThread c_thread = new ClientManagerThread(c_socket);

                m_OutputList.add(new PrintWriter(c_socket.getOutputStream()));

                c_thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
