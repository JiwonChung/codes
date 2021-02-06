package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManagerThread extends Thread {

    private Socket m_socket;
    private String m_ID;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    @Override
    public void run() {
        super.run();

        try {
            BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));

            String text;

            while (true) {
                text = tmpbuffer.readLine();

                if (text == null) {
                    System.out.println(m_ID + "이(가) 나갔습니다. ");
                    for (int i = 0; i < ChatServer.m_OutputList.size(); ++i) {
                        ChatServer.m_OutputList.get(i).println(m_ID + "이(가) 나갔습니다. ");
                        ChatServer.m_OutputList.get(i).flush();
                    }
                    break;
                }

                for (int i = 0; i < ChatServer.m_OutputList.size(); ++i) {
                    ChatServer.m_OutputList.get(i).println("Jiwon" + "이(가) 입장하였습니다. " + "\n");
                    ChatServer.m_OutputList.get(i).flush();
                }

                for (int i = 0; i < ChatServer.m_OutputList.size(); ++i) {
                    ChatServer.m_OutputList.get(i).println(ANSI_BLUE + m_ID + ">  " + text + ANSI_RESET);
                    ChatServer.m_OutputList.get(i).flush();
                }
            }
            ChatServer.m_OutputList.remove(new PrintWriter(m_socket.getOutputStream()));
            m_socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ClientManagerThread(Socket s) {
        m_socket = s;
    }
}
