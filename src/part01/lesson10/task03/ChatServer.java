package part01.lesson10.task03;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

/**
 * Класс сервера
 */
public class ChatServer extends Thread {

    public final static int PORT = 7331;
    private final static int BUFFER = 1024;
    private final static String MULTICAST_GROUP_IP = "224.0.0.3";
    private final static int MULTICAST_GROUP_PORT = 8888;
    private DatagramSocket socket;
    private MulticastSocket multicastSocket;

    public ChatServer() throws IOException {
        socket = new DatagramSocket(PORT);
    }

    public void run() {
        byte[] buf = new byte[BUFFER];
        while (true) {
            try {
                Arrays.fill(buf, (byte) 0);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String content = new String(buf, "UTF-8");
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                String id = clientAddress.toString() + "," + clientPort;
                System.out.println(id + " : " + content);
                byte[] data = (id + " : " + content).getBytes();
                InetAddress group = InetAddress.getByName(MULTICAST_GROUP_IP);
                multicastSocket = new MulticastSocket();
                multicastSocket.joinGroup(group);
                packet = new DatagramPacket(data, data.length, group, MULTICAST_GROUP_PORT);
                multicastSocket.send(packet);
                multicastSocket.disconnect();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String args[]) throws Exception {
        ChatServer s = new ChatServer();
        s.start();
    }
}
