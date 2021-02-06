import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendThread extends Thread {
    private Socket m_Socket;

    @Override
    public void run() {
        super.run();

        try {
            BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(System.in));

            PrintWriter writer = new PrintWriter(m_Socket.getOutputStream());

            String sendString;

            System.out.println("Please enter ID > ");
            ChatClient.UserID = tmpbuf.readLine();

            writer.println("IDhighkrs12345" + ChatClient.UserID);
            writer.flush();

            while (true) {
                sendString = tmpbuf.readLine();

                if (sendString.equals("exit")) {
                    break;
                }

                writer.println(sendString);
                writer.flush();

            }
            writer.close();
            tmpbuf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public SendThread(Socket s) {
        m_Socket = s;
    }
}
