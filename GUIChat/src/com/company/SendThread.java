package com.company;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendThread extends Thread {
    private Socket m_Socket;

    @Override
    public void run() {
        super.run();

        try {

            PrintWriter writer = new PrintWriter(m_Socket.getOutputStream());
            String sendString;

            while (true) {
                sendString = GUI.textField.getText();

                if (sendString.equals("exit")) {
                    break;
                }

                writer.println(sendString);
                writer.flush();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public SendThread(Socket s) {
        m_Socket = s;
    }
}
