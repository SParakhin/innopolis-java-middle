package part01.lesson10.task01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ChatClient {

    public static void main(String args[]) throws Exception {
        String host = "localhost";
        DatagramSocket socket = new DatagramSocket();
        MessageReceiver r = new MessageReceiver(socket);
        MessageSender s = new MessageSender(socket, host);
        Thread rt = new Thread(r);
        Thread st = new Thread(s);
        rt.start();
        st.start();
    }
}

/**
 * Класс отправки пакетов
 */
class MessageSender implements Runnable {
    public final static int PORT = 7331;
    private DatagramSocket sock;
    private String hostname;

    MessageSender(DatagramSocket s, String h) {
        sock = s;
        hostname = h;
    }

    /**
     * Метод отправки пакетов
     * @param s
     * @throws Exception
     */
    private void sendMessage(String s) throws Exception {
        byte buf[] = s.getBytes();
        InetAddress address = InetAddress.getByName(hostname);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        sock.send(packet);
    }

    public void run() {
        boolean connected = false;
        do {
            try {
                sendMessage("Вошёл в чат");
                connected = true;
            } catch (Exception e) {

            }
        } while (!connected);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while (!in.ready())
                sendMessage(in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * Класс приема пакетов
 */
class MessageReceiver implements Runnable {
    private DatagramSocket sock;
    private byte buf[];

    MessageReceiver(DatagramSocket s) {
        sock = s;
        buf = new byte[1024];
    }

    public void run() {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                sock.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
