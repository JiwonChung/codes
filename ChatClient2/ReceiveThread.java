import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread {
    private Socket m_Socket;

    @Override
    public void run() {
        try {
            BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(m_Socket.getInputStream()));

            String receivedString;

            String[] splitString;

            while (true) {
                receivedString = tmpbuf.readLine();

                splitString = receivedString.split(">");

                if (splitString.length >= 2 && splitString[0].equals(ChatClient.UserID)) {
                    continue;
                }
                System.out.println(receivedString);
            }
        } catch (IOException e) {}
    }

    public ReceiveThread (Socket s) {
        m_Socket = s;
    }
}
