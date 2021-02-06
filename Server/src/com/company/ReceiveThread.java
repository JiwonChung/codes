package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLOutput;

public class ReceiveThread extends Thread{
    private Socket m_Socket;

    @Override
    public void run() {
        super.run();

        try {
            BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(m_Socket.getInputStream()));

            String receiveString;

            while (true) {
                receiveString = tmpbuf.readLine();

                if (receiveString == null) {
                    System.out.println("상대방과의 연결이 끊겼습니다. ");
                    break;
                } else {
                    System.out.println("상대방: " + receiveString);
                }
            }
            tmpbuf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ReceiveThread(Socket s) {
        this.m_Socket = s;
    }
}
