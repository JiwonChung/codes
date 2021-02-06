package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("tranna keep it cool");
            Socket clientSocket = serverSocket.accept();
            System.out.println("She accepted");
            OutputStream outputStream = clientSocket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            
            String string = bufferedReader.readLine();
            bufferedOutputStream.write(string.getBytes());

            bufferedOutputStream.close();
            clientSocket.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}