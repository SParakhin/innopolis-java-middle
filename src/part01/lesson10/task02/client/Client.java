package part01.lesson10.task02.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String username, message;
    boolean registered = false;
    int tries = 0;

    final Runnable send = new Runnable() {
        public void run() {
            try {
                InetAddress address = InetAddress.getByName("localhost");
                socket = new Socket(address.getHostAddress(), 8000);
                System.out.println("Установлено соединение с сервером на порту 8000");
                out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();
                in = new ObjectInputStream(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                do {
                    if (tries == 0) {
                        System.out.print("Введите имя пользователя: ");
                    } else if (tries > 0) {
                        System.out.print("Указанное имя занято : ");
                    }
                    username = bufferedReader.readLine();
                    sendToServer(username);
                    registered = (boolean) in.readObject();
                    if (registered == true) {
                        //show the message to the user
                        System.out.println("Вы зарегистрированы : " + username);
                    } else {
                        tries++;
                    }
                } while (registered != true);

                System.out.println("Добро пожаловать в чат");
                System.out.println("Формат отправки личного сообщения @UserName Message");

                Thread incoming = new Thread(receive);
                incoming.start();

                while (true) {
                    message = bufferedReader.readLine();
                    if (message.contains("quit")) {
                        downService();
                        break;
                    } else {
                        sendToServer(message);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    Runnable receive = new Runnable() {
        public void run() {
            try {
                while (true) {
                    message = String.valueOf(in.readObject());
                    System.out.println(message);
                }
            } catch (SocketException ignored) {
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    /**
     * Метод отправки сообщения
     *
     * @param str
     */
    void sendToServer(String str) {
        try {
            out.reset();
            out.writeObject(str);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод завершения сеанса пользователя
     */
    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                System.out.println("Вы вышли из чата");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void startThreads() {
        Thread outcoming = new Thread(send);
        outcoming.start();
    }

    public static void main(String args[]) {
        Client newClient = new Client();
        newClient.startThreads();
    }
}