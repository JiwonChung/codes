package Client;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            Socket inputSocket = new Socket("172.26.47.214", 9999); 
            InputStream inputStream = inputSocket.getInputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(System.out);
            int c;
            while ((c = inputStream.read()) != -1) {
                bufferedOutputStream.write((char)c);
            }
            bufferedOutputStream.close();
            inputStream.close();             
            inputSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
