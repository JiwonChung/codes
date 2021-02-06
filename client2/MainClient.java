import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient {

    public static void main(String[] args) {

        try {
            Socket c_socket = new Socket("192.168.56.1", 9999);

            ReceiveThread receiveThread = new ReceiveThread(c_socket);
            SendThread sendThread = new SendThread(c_socket);

            receiveThread.start();
            sendThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
