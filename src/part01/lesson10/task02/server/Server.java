package part01.lesson10.task02.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    private static final int sPort = 8000;
    private static Map<Socket, Socket> connMap = new HashMap<>();
    private static Map<String, ObjectOutputStream> userMap = new HashMap<>();
    private static List<Handler> handlerList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Сервер запущен");
        ServerSocket listener = new ServerSocket(sPort);
        int clientNum = 1;
        try {
            while (true) {
                Handler handler = new Handler(listener.accept(), clientNum);
                handler.start();
                handlerList.add(handler);
                System.out.println("Клиент под номером " + clientNum + " подключен!");
                clientNum++;
            }
        } finally {
            listener.close();
        }
    }

    /**
     * Класс обработчика потоков для прослушивания запросов от клиентов
     */
    private static class Handler extends Thread {
        private String username;
        private String message;
        private String receiver;
        private Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        private ObjectOutputStream recConn;
        private int no;
        private boolean isRegistered = false;
        private String msgTokens[];

        public Handler(Socket connection, int no) {
            this.socket = connection;
            this.no = no;
        }

        public void run() {
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();
                in = new ObjectInputStream(socket.getInputStream());
                try {
                    do {
                        username = (String) in.readObject();
                        System.out.println("Получен запрос на регистрацию : " + username);
                        isRegistered = userMap.containsKey(username);
                        if (!isRegistered) {
                            userMap.put(username, out);
                        }
                        sendMessage(!isRegistered);
                    } while (isRegistered);

                    while (true) {
                        message = (String) in.readObject();
                        msgTokens = message.split(" ");
                        if (msgTokens[0].charAt(0) == '@') {
                            message = message.replaceFirst(msgTokens[0] + " ", "");
                            receiver = msgTokens[0].replaceFirst("@", "");
                            recConn = userMap.get(receiver);
                            if (recConn == null) {
                                sendMessage("Пользователь " + receiver + " не существует");
                            } else {
                                System.out.println("Личное сообщение от " + username + " для " + receiver);
                                sendMessage(recConn, "Личное сообщение от " + username + " : " + message);
                            }
                        } else {
                            for (String entry : userMap.keySet()) {
                                String user = entry;
                                if (!(user.equalsIgnoreCase(username))) {
                                    System.out.println("Сообщение от " + username + " to " + user);
                                    recConn = userMap.get(user);
                                    sendMessage(recConn, "Сообщение от " + username + " : " + message);
                                }
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                downService();
            } finally {
                downService();
                System.out.println("Клиент " + username + " вышел из чата");
            }
        }

        /**
         * Метод подтверждения регистрации клиента
         *
         * @param status
         */
        public void sendMessage(boolean status) {
            try {
                out.writeObject(status);
                out.flush();
                if (!isRegistered) {
                    System.out.println("Пользователь " + username + " зарегистрирован");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Метод для для уведомления о несуществующем пользователе при попытке отправить личное сообщение
         *
         * @param status
         */
        public void sendMessage(String status) {
            try {
                out.writeObject(status);
                out.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        /**
         * Метод отправки сообщения
         *
         * @param oos
         * @param msg
         */
        public void sendMessage(ObjectOutputStream oos, String msg) {
            try {
                oos.writeObject(msg);
                oos.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        /**
         * Метод для удаления клиента из чата при вводе клиентом "quit"
         */
        private void downService() {
            try {
                if (!socket.isClosed()) {
                    for (Handler handler : handlerList) {
                        if (handler.equals(this)) {
                            handler.interrupt();
                            handlerList.remove(this);
                        }
                    }
                    userMap.remove(username);
                    connMap.remove(socket);
                    socket.close();
                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}