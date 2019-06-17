package part01.lesson10.task03.client2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class ChatClient {

    public static void main(String args[]) throws Exception {
        String host = "localhost";
        DatagramSocket socket = new DatagramSocket();
        MulticastSocket multicastSocket = new MulticastSocket(8888);
        MessageReceiver r = new MessageReceiver(multicastSocket);
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
     *
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
    private MulticastSocket multicastSocket;
    private byte buf[];
    private final static String MULTICAST_GROUP_IP = "224.0.0.3";

    MessageReceiver(MulticastSocket s) {
        multicastSocket = s;
        buf = new byte[1024];
    }

    public void run() {
        try {
            multicastSocket.joinGroup(InetAddress.getByName(MULTICAST_GROUP_IP));
        } catch (IOException e) {
            e.printStackTrace();
        }
        receivePackets(buf, multicastSocket);
        return;
    }

    private static void receivePackets(byte[] buf, MulticastSocket multicastSocket) {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                multicastSocket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}