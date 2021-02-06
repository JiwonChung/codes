package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread{
    private Socket m_Socket;

    @Override
    public void run() {
        super.run();
        try {
            BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(m_Socket.getInputStream()));

            String receivedString;

            while (true) {
                receivedString = tmpbuf.readLine();
                System.out.println("상대방 : " + receivedString);

            }
        } catch (IOException e){}
    }

    public ReceiveThread (Socket s) {
        m_Socket = s;
    }
}
